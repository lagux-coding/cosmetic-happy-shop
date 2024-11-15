/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cosmetics.Cart;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RemoveItemController", urlPatterns = {"/RemoveItemController"})
public class RemoveItemController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            HttpSession session = request.getSession(false);
            if(session!=null){
                Cart cart = (Cart) session.getAttribute("CART");
                if(cart!=null){
                    if(cart.getCart().containsKey(id)){
                        boolean checkRemove = cart.remove(id);
                        if(checkRemove){
                            if(cart.getCart().size() == 0){
                                cart = null;
                            }
                            session.setAttribute("CART", cart);
                            request.setAttribute("MESSAGE", "Remove successfully!!!");
                            url = SUCCESS;
                        }
                    }else{
                        request.setAttribute("MESSAGE", "This item not exist");
                    }
                }else{
                    request.setAttribute("MESSAGE", "No item in cart");
                }
            }else{
                request.setAttribute("MESSAGE", "No session");
            }
        } catch (Exception e) {
            log("Error at RemoveItemController: "+ e.toString());
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
