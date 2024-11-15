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
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError = new UserError();
        String url = ERROR;
        try {
            boolean checkValidation = true;
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password =  request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String email = "";
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser!=null){
                email = loginUser.getEmail();
            }else{
                email = request.getParameter("email");
            }
            //User id validation
            if(userID.length() < 2 || userID.length() > 10){
                checkValidation = false;
                userError.setUserIdError("User ID must be in [2-10]");
            }
            //Full name validation
            if(fullName.length() < 5 || fullName.length() > 150){
                checkValidation = false;
                userError.setFullNameError("Full Name must be in [5-150]");
            }
            //Email validation
            for(UserDTO user : listUser){
                if(email.equals(user.getEmail())){
                    checkValidation = false;
                    userError.setEmailError("Email exist");
                    break;
                }
            }
            //Confirm password validation
            if(!password.equals(confirm)){
                checkValidation = false;
                userError.setConfirmError("Please enter the same password");
            }
            //Check Validation
            if(checkValidation){
                UserDTO user = new UserDTO(userID, fullName, password, roleID, email);
                boolean check = dao.insertUser(user);
                if(check){
                    url = SUCCESS;
                }else{
                    userError.setError("Unknown Error");
                    request.setAttribute("USER_ERROR", userError);
                }
            }else{
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateUserController: "+e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserIdError("User ID exist");
                request.setAttribute("USER_ERROR", userError);
            }
        } finally{
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
