package View;

import Controller.BasketController;
import Controller.CardController;
import Core.Helper;
import Entity.Basket;
import Entity.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardUi extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_customer_name;
    private JTextField fld_card_date;
    private JTextArea tarea_card_note;
    private JButton btn_card;
    private Customer customer;
    private BasketController basketController;
    private CardController cardController;


    public  CardUi(Customer customer){
        this.customer = customer;
        this.basketController = new BasketController();
        this.cardController = new CardController();

        this.add(container);
        this.setTitle("Sipariş Oluştur");
        this.setSize(300, 500);

        //ekranı ortala
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        if (customer.getId() == 0){
            Helper.showMsg("Lütfen geçerli bir müşteri seçiniz.");
            dispose();
        }
        ArrayList<Basket> baskets = this.basketController.findAll();
        if (baskets.size() ==0){
            Helper.showMsg("Lütfen Sepete Ürün ekleyiniz.");
            dispose();
        }
        this.lbl_customer_name.setText("Müşteri :" + this.customer.getName());

    }
}
