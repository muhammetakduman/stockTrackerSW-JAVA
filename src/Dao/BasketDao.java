package Dao;

import Core.Database;
import Entity.Basket;
import Entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BasketDao {
    private Connection connection;
    private ProductDao productDao;

    public BasketDao(){
        this.connection = Database.getInstance();
        this.productDao = new ProductDao();
    }

    public boolean save(Basket basket){

        String query = " INSERT INTO basket " +
                "(" +
                "product_id" +
                ")" +
                " VALUES (?)";

        try {
            PreparedStatement pr  = this.connection.prepareStatement(query);
            pr.setInt(1,basket.getProductId());
            pr.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }



    public ArrayList<Basket> findAll(){
        ArrayList<Basket> baskets = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM basket");
            while (rs.next()){
                baskets.add(this.match(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return baskets;
    }




    private Basket match (ResultSet rs) throws SQLException {
        Basket basket = new Basket();
        basket.setId(rs.getInt("id"));
        basket.setProductId(rs.getInt("product_id"));
        basket.setProduct(this.productDao.getById(rs.getInt("product_id")));
        return basket;
    }

}
