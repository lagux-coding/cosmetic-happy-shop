/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO {
    
    private static final String ADD_ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID, productID, price, quantity) VALUES(?, ?, ?, ?)";
    private static final String GET_LIST_ORDER_DETAIL = "SELECT orderID, productID, price, quantity FROM tblOrderDetail";
    private static final String GET_LIST_ORDER_DETAIL_BY_ORDER_ID = "SELECT orderID, productID, price, quantity FROM tblOrderDetail WHERE orderID = ?";
    
    public boolean addOrderDetail(OrderDetailDTO orderDetail) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER_DETAIL);
                ptm.setString(1, orderDetail.getOrderID());
                ptm.setString(2, orderDetail.getProductID());
                ptm.setDouble(3, orderDetail.getPrice());
                ptm.setInt(4, orderDetail.getQuantity());
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

    public List<OrderDetailDTO> getListOrderDetail() throws ClassNotFoundException, SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_ORDER_DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String productID = rs.getString("productID");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new OrderDetailDTO(orderID, productID, price, quantity));
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

    public List<OrderDetailDTO> getListOrderDetail(String orderID) throws ClassNotFoundException, SQLException {
                List<OrderDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_ORDER_DETAIL_BY_ORDER_ID);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new OrderDetailDTO(orderID, productID, price, quantity));
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
