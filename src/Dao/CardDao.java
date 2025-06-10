package Dao;

import Core.Database;
import Entity.Card;
import Entity.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CardDao {

    private Connection connection;
    private ProductDao productDao;
    private CustomerDao customerDao;

    public CardDao(){
        this.connection = Database.getInstance();
        this.productDao = new ProductDao();
        this.customerDao = new CustomerDao();
    }


    /// findallMethod
    public ArrayList<Card> findAll(){
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM card");
            while (rs.next()){
                cards.add(this.match(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cards;

    }



    /// save method
    public boolean save(Card card) {
        String query2 = "INSERT INTO cart "+
                "(" +
                "customer_id," +
                "product_id," +
                "price," +
                "date," +
                "note "+
                ")" +
                " VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query2);
            pr.setInt(1, card.getCustomerId());
            pr.setInt(2,card.getProductId());
            pr.setInt(3, card.getPrice());
            pr.setDate(4, Date.valueOf(card.getDate()));
            pr.setString(4, card.getNote());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /// match method
    private Card match(ResultSet rs) throws SQLException {
        Card card = new Card();
        card.setId(rs.getInt("id"));
        card.setPrice(rs.getInt("price"));
        card.setCustomerId(rs.getInt("customer_id"));
        card.setProductId(rs.getInt("product_id"));
        card.setNote(rs.getString("note"));
        card.setDate(LocalDate.parse(rs.getString("date")));
        card.setCustomer(this.customerDao.getById(card.getCustomerId()));
        card.setProduct(this.productDao.getById(card.getProductId()));

        return card;
    }




}
