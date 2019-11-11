package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class productDao {
    private Connection getDBConnection() {
        Connection conn = null;
        try {
            conn = DBDao.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ArrayList<Product> getProductList() {
        Connection conn = getDBConnection();
        ArrayList<Product> productList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from tbl_product");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product tmpProduct = new Product();
                tmpProduct.setId(rs.getInt(1));
                tmpProduct.setName(rs.getString(2));
                tmpProduct.setPrice(rs.getDouble(3));
                tmpProduct.setQuantity(rs.getInt(4));
                tmpProduct.setDescription(rs.getString(5));
                productList.add(tmpProduct);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void AddProduct(Product product) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into tbl_product(name,price,quantity,description) values (?,?,?,?)");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from tbl_product where id =?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        Connection conn = getDBConnection();
        Product selectedProduct = new Product();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from tbl_product where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                selectedProduct.setId(rs.getInt(1));
                selectedProduct.setName(rs.getString(2));
                selectedProduct.setPrice(rs.getDouble(3));
                selectedProduct.setQuantity(rs.getInt(4));
                selectedProduct.setDescription(rs.getString(5));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedProduct;
    }

    public void updateProductInfo(Product product) {
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("update tbl_product set name = ?,price=?,quantity=?,description=? where id=?");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
