/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cosmetics;

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
public class CosmeticDAO {

    private static final String SEARCH_PRODUCT = "SELECT productID, productName, price, quantity FROM tblProducts WHERE productName LIKE ?";
    private static final String GET_COSMETIC_LIST = "SELECT productID, productName, price, quantity FROM tblProducts";
    private static final String UPDATE_PRODUCT = "UPDATE tblProducts SET productName = ?, price = ?, quantity = ? WHERE productID = ?";
    private static final String REMOVE_PRODUCT = "DELETE tblProducts WHERE productID = ?";
    private static final String ADD_PRODUCT = "INSERT INTO tblProducts(productID, productName, price, quantity) VALUES(?, ?, ?, ?)";
    private static final String GET_QUANTITY = "SELECT quantity FROM tblProducts WHERE productID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";

    public List<CosmeticDTO> getListCosmetic(String search) throws SQLException, ClassNotFoundException {
        List<CosmeticDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("price");
                    int productQuantity = rs.getInt("quantity");
                    list.add(new CosmeticDTO(productID, productName, productPrice, productQuantity));
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

    public List<CosmeticDTO> getListCosmetic() throws ClassNotFoundException, SQLException {
        List<CosmeticDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_COSMETIC_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("price");
                    int productQuantity = rs.getInt("quantity");
                    list.add(new CosmeticDTO(productID, productName, productPrice, productQuantity));
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

    public boolean updateProduct(CosmeticDTO cosmetic) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, cosmetic.getProductName());
                ptm.setDouble(2, cosmetic.getProductPrice());
                ptm.setInt(3, cosmetic.getProductQuantity());
                ptm.setString(4, cosmetic.getProductID());
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

    public boolean removeProduct(String productID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REMOVE_PRODUCT);
                ptm.setString(1, productID);
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

    public boolean addProduct(CosmeticDTO cosmetic) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setString(1, cosmetic.getProductID());
                ptm.setString(2, cosmetic.getProductName());
                ptm.setDouble(3, cosmetic.getProductPrice());
                ptm.setInt(4, cosmetic.getProductQuantity());
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

    public int getCosmeticQuantity(String id) throws ClassNotFoundException, SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

   public boolean updateQuantity(String id, int availableQuantity) throws ClassNotFoundException, SQLException {
    boolean check = false;
    Connection conn = null;
    PreparedStatement ptm = null;
    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            ptm = conn.prepareStatement(UPDATE_QUANTITY);
            ptm.setInt(1, availableQuantity);
            ptm.setString(2, id);
            check = ptm.executeUpdate() > 0;
        }
    }finally {
        if (ptm != null) {
            ptm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    return check;
}


}
