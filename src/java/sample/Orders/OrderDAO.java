/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    private static final String GET_LIST_ORDER = "SELECT orderID, userID, orderDate, total FROM tblOrder";
    private static final String ADD_ORDER = "INSERT INTO tblOrder(orderID, userID, orderDate, total) VALUES(?, ?, ?, ?)";
    private static final String GET_LIST_ORDER_BY_USER_ID = "SELECT orderID, userID, orderDate, total FROM tblOrder WHERE userID = ?";
    
    public List<OrderDTO> getListOrder() throws ClassNotFoundException, SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_ORDER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    Date orderDate = rs.getDate("orderDate");
                    double total = rs.getDouble("total");
                    list.add(new OrderDTO(orderID, userID, orderDate, total));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean addOrder(OrderDTO order) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER);
                ptm.setString(1, order.getOrderID());
                ptm.setString(2, order.getUserID());
                ptm.setDate(3, (java.sql.Date) order.getOrderDate());
                ptm.setDouble(4, order.getTotal());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<OrderDTO> getListOrder(String userID) throws ClassNotFoundException, SQLException {
          List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_ORDER_BY_USER_ID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    double total = rs.getDouble("total");
                    list.add(new OrderDTO(orderID, userID, orderDate, total));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

}
