/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String CREATE_PAGE = "Create_Page";
    private static final String CREATE_PAGE_VIEW = "createUser.jsp";

    private static final String CREATE_USER = "Create";
    private static final String CREATE_USER_CONTROLLER = "CreateUserController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String SEARCH_USER = "Search";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";

    private static final String UPDATE_USER = "Update";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";

    private static final String DELETE_USER = "Delete";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";

    private static final String PRODUCT_PAGE = "Product_Page";
    private static final String PRODUCT_PAGE_VIEW = "productManagement.jsp";

    private static final String SEARCH_PRODUCT = "Search_Product";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";

    private static final String UPDATE_PRODUCT = "Update_Product";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";

    private static final String REMOVE_PRODUCT = "Remove_Product";
    private static final String REMOVE_PRODUCT_CONTROLLER = "RemoveProductController";

    private static final String ADD_PRODUCT_PAGE = "Add_Product_Page";
    private static final String ADD_PRODUCT_VIEW = "addProduct.jsp";

    private static final String ADD_PRODUCT = "Add_Product";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";

    private static final String SHOPPING = "Shopping";
    private static final String SHOPPING_CONTROLLER = "ShoppingController";
    
    private static final String ADD_TO_CART = "Add_To_Cart";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    
    private static final String VIEW_CART = "View_Cart";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    
    private static final String REMOVE_ITEM = "Remove_Item";
    private static final String REMOVE_ITEM_CONTROLLER = "RemoveItemController";
    
    private static final String UPDATE_ITEM = "Update_Item";
    private static final String UPDATE_ITEM_CONTROLLER = "UpdateItemController";
    
    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";
    
    private static final String VIEW_ORDER = "View_Order";
    private static final String VIEW_ORDER_CONTROLLER = "ViewOrderController";
    
    private static final String VIEW_ORDER_DETAIL = "View_Order_Detail";
    private static final String VIEW_ORDER_DETAIL_CONTROLLER = "ViewOrderDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (CREATE_PAGE.equals(action)) {
                url = CREATE_PAGE_VIEW;
            } else if (CREATE_USER.equals(action)) {
                url = CREATE_USER_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (SEARCH_USER.equals(action)) {
                url = SEARCH_USER_CONTROLLER;
            } else if (UPDATE_USER.equals(action)) {
                url = UPDATE_USER_CONTROLLER;
            } else if (DELETE_USER.equals(action)) {
                url = DELETE_USER_CONTROLLER;
            } else if (PRODUCT_PAGE.equals(action)) {
                url = PRODUCT_PAGE_VIEW;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (REMOVE_PRODUCT.equals(action)) {
                url = REMOVE_PRODUCT_CONTROLLER;
            } else if (ADD_PRODUCT_PAGE.equals(action)) {
                url = ADD_PRODUCT_VIEW;
            }  else if (ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (SHOPPING.equals(action)) {
                url = SHOPPING_CONTROLLER;
            }else if (ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            }else if (VIEW_CART.equals(action)) {
                url = VIEW_CART_PAGE;
            }else if (REMOVE_ITEM.equals(action)) {
                url = REMOVE_ITEM_CONTROLLER;
            }else if (UPDATE_ITEM.equals(action)) {
                url = UPDATE_ITEM_CONTROLLER;
            }else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            }else if (VIEW_ORDER.equals(action)) {
                url = VIEW_ORDER_CONTROLLER;
            }else if (VIEW_ORDER_DETAIL.equals(action)) {
                url = VIEW_ORDER_DETAIL_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
