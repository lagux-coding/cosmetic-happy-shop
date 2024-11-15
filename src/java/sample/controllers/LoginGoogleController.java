/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.google.GoogleAccount;
import sample.google.GoogleHandler;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String AD = "AD";
    private static final String US = "US";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String USER_PAGE = "user.jsp";
    private static final String REGISTER = "createUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            GoogleHandler handler = new GoogleHandler();
            String accessToken = handler.getToken(code);
            GoogleAccount acc = handler.getUserInfo(accessToken);
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser();
            boolean checkExist = false;
            String emailGoogle = acc.getEmail();
            if (listUser.size() > 0) {
                for (UserDTO user : listUser) {
                    String emailDB = user.getEmail();
                    if (emailGoogle.equals(emailDB)) {
                        checkExist = true;
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_USER", user);
                        if (AD.equals(user.getRoleID())) {
                            url = ADMIN_PAGE;
                        } else if (US.equals(user.getRoleID())) {
                            url = USER_PAGE;
                        }
                        break;
                    }
                }
            }
            if(!checkExist){
                HttpSession session = request.getSession();
                UserDTO user = new  UserDTO("", "", "", "", emailGoogle);
                session.setAttribute("LOGIN_USER", user);
                url = REGISTER;
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
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
