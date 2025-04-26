package Dao;

import Core.Database;
import Entity.Customer;
import Entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
    //db constructor
    private Connection connection;

    public ProductDao() {
        this.connection = Database.getInstance();

    }

    /// findallMethod
    public ArrayList<Product> findAll(){
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM product");
            while (rs.next()){
                products.add(this.match(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;

    }


    /// match method
    private Product match(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCode(rs.getString("code"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        return product;
    }
}
