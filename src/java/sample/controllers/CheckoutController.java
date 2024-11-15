/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.OrderDetails.OrderDetailDAO;
import sample.OrderDetails.OrderDetailDTO;
import sample.Orders.OrderDAO;
import sample.Orders.OrderDTO;
import sample.cosmetics.Cart;
import sample.cosmetics.CosmeticDAO;
import sample.cosmetics.CosmeticDTO;
import sample.users.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "SendMailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    List<String> errorMessage = new ArrayList<>();
                    CosmeticDAO cosmeticDao = new CosmeticDAO();
                    boolean check = true;
                    double total = 0;
                    int quantity = 0;
                    int availableQuantity = 0;
                    String id = "";
                    for (CosmeticDTO cosmetic : cart.getCart().values()) {
                        id = cosmetic.getProductID();
                        quantity = cosmetic.getProductQuantity();
                        availableQuantity = cosmeticDao.getCosmeticQuantity(id);
                        total += cosmetic.getProductPrice() * quantity;
                        if (quantity > availableQuantity) {
                            errorMessage.add(cosmetic.getProductName() + " quantity in stock: " + availableQuantity);
                            check = false;
                        }
                    }
                    if (check) {
                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                        OrderDAO orderDao = new OrderDAO();
                        List<OrderDTO> listOrder = orderDao.getListOrder();
                        String orderID = "A" + (listOrder.size() + 1);
                        String userID = loginUser.getUserID();
                        Date date = new Date();
                        java.sql.Date orderDate = new java.sql.Date(date.getTime());
                        OrderDTO order = new OrderDTO(orderID, userID, orderDate, total);
                        session.setAttribute("ORDER", order);
                        boolean checkAddOrder = orderDao.addOrder(order);
                        if (checkAddOrder) {
                            boolean checkAddOrderDetail = false;
                            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
                            for (CosmeticDTO cosmetic : cart.getCart().values()) {
                                OrderDetailDTO orderDetail = new OrderDetailDTO(orderID, cosmetic.getProductID(), cosmetic.getProductPrice(), cosmetic.getProductQuantity());
                                checkAddOrderDetail = orderDetailDao.addOrderDetail(orderDetail);
                            }

                            if (checkAddOrderDetail) {
                                List<OrderDetailDTO> listOrderDetail = orderDetailDao.getListOrderDetail();
                                session.setAttribute("LIST_ORDER", listOrder);
                                session.setAttribute("LIST_ORDER_DETAIL", listOrderDetail);
                                for (CosmeticDTO cosmetic : cart.getCart().values()) {
                                    id = cosmetic.getProductID();
                                    quantity = cosmetic.getProductQuantity();
                                    availableQuantity = cosmeticDao.getCosmeticQuantity(id);
                                    availableQuantity -= quantity;
                                    boolean checkUpdate = cosmeticDao.updateQuantity(id, availableQuantity);
                                    if (checkUpdate) {
                                        request.setAttribute("MESSAGE", "Checkout successfully!!!");
                                        url = SUCCESS;
                                    }
                                }
                            }
                        }
                    } else {
                        request.setAttribute("CHECKOUT_MESSAGE", errorMessage);
                    }
                } else {
                    request.setAttribute("MESSAGE", "No item in your cart");
                }
            } else {
                request.setAttribute("MESSAGE", "No session");
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
            
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
