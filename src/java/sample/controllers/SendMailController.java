/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.Orders.OrderDTO;
import sample.cosmetics.Cart;
import sample.cosmetics.CosmeticDTO;
import sample.users.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SendMailController", urlPatterns = {"/SendMailController"})
public class SendMailController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        // SMTP server configuration
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "namnguyen8644@gmail.com"; // Email
        String password = "xmne tipn jltb przr"; // Email password

        // Properties configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a session
        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            HttpSession session = request.getSession();
            double total = 0;
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                if (!user.getEmail().equals("")) {
                    Cart cart = (Cart) session.getAttribute("CART");
                    OrderDTO order = (OrderDTO) session.getAttribute("ORDER");

                    // Generate the email body with cart items
                    String emailBody = "<!DOCTYPE html>"
                            + "<html lang=\"en\">"
                            + "<head></head>"
                            + "<body>"
                            + "<h3 style=\"color: blue;\">Your order has been processed</h3>"
                            + "<div>Full Name: " + user.getFullName() + "</div>"
                            + "<div>Email: " + user.getEmail() + "</div>"
                            + "<div>Order ID: " + order.getOrderID() + "</div>"
                            + "<div>Ordered Date: " + order.getOrderDate() + "</div>"
                            + "<h4>Order Details:</h4>"
                            + "<ul>";

                    for (CosmeticDTO item : cart.getCart().values()) {
                        total += item.getProductPrice() * item.getProductQuantity();
                        emailBody += "<li>"
                                + item.getProductName()
                                + " - Quantity: " + item.getProductQuantity()
                                + " - Price: " + item.getProductPrice()
                                + "</li>";
                    }

                    emailBody += "</ul>"
                            + "<h4>Total: </h4><h2>" + total + "$</h2>"
                            + "<h3 style=\"color: blue;\">Thank you!!!!</h3>"
                            + "</body>"
                            + "</html>";

                    // Create the message
                    Message message = new MimeMessage(mailSession);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                    message.setSubject("Confirmation of Purchase");
                    message.setContent(emailBody.toString(), "text/html");

                    // Send the email
                    Transport.send(message);

                    // If successful, set success URL
                    session.removeAttribute("CART");
                    request.setAttribute("EMAIL_MESSAGE", "Email with your order will send to you! Thank You!");
                    url = SUCCESS;
                } else {
                    session.removeAttribute("CART");
                    request.setAttribute("EMAIL_MESSAGE", "You are not update email yet. Therefore, we can not send confirm mail to you!");
                }
            }

        } catch (Exception e) {
            log("Error at SendMailController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
