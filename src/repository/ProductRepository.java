package repository;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRepository {
    // three variables
    private final static String connectUrl = "jdbc:postgresql://localhost:5432/postgres";
    private final static String username = "postgres";
    private final static String password = "123";

    private final static String SELECT_PRODUCT = "select * from product_tb";
    private final static String INSERT_PRODUCT = """
            insert into product_tb (productid, productname, madein, price, company, madeat, expiredat)
            values (?,?,?,?,?,?,?)
            """;
    private final static String UPDATE_PRODUCT = """
            UPDATE product_tb set
            productname =?, madein=?, price=?, company=?, madeat=?, expiredat=?
            where productid=?
            """;
    private final static String DELETE_PRODUCT = """
            delete from product_tb where productid=?
            """;
    public static int deleteProduct(int deleteId){
        try(Connection connection = DriverManager.getConnection(connectUrl,username,password);
        PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT);
        ){
            ps.setInt(1, deleteId);
            return ps.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public static int updateProduct(Product product, int updateID) {
        try (Connection connection = DriverManager.getConnection(connectUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT);
        ) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getMadeIn());
            ps.setFloat(3, product.getPrice());
            ps.setString(4, product.getCompany());
            ps.setDate(5, product.getMadeAt());
            ps.setDate(6, product.getExpiredAt());
            ps.setInt(7, updateID);
            return ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int insertProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(connectUrl, username, password);
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT);
        ) {
            ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getMadeIn());
            ps.setFloat(4, product.getPrice());
            ps.setString(5, product.getCompany());
            ps.setDate(6, product.getMadeAt());
            ps.setDate(7, product.getExpiredAt());
            return ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectUrl, username, password);
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(SELECT_PRODUCT);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productid"));
                product.setProductName(rs.getString("productname"));
                product.setPrice(rs.getFloat("price"));
                product.setCompany(rs.getString("company"));
                product.setMadeIn(rs.getString("madein"));
                product.setMadeAt(rs.getDate("madeat"));
                product.setExpiredAt(rs.getDate("expiredat"));
                allProducts.add(product);

            }
            return allProducts;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
