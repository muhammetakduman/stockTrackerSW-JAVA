package View;

import Controller.ProductController;
import Core.Helper;
import Entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductUi extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JTextField fld_product_name;
    private JTextField fld_product_code;
    private JTextField fld_product_price;
    private JTextField fld_product_stock;
    private JButton btn_product;
    private JLabel lbl_product_name;
    private JLabel lbl_product_code;
    private JLabel lbl_product_price;
    private JLabel lbl_product_stock;
    private Product product;
    private ProductController productController;

    public ProductUi(Product product){
        this.product = product;
        this.productController = new ProductController();

        this.add(container);
        this.setTitle("Ürün Ekle/Düzenle");
        this.setSize(300, 500);

        //ekranı ortala
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        if(this.product.getId() == 0){
            this.lbl_title.setText("Ürün Ekle");;
        } else {
            this.lbl_title.setText("Ürün Düzenle");
            this.fld_product_name.setText(this.product.getName());
            this.fld_product_code.setText(this.product.getCode());
            this.fld_product_price.setText(String.valueOf(this.product.getPrice()));
            this.fld_product_stock.setText(String.valueOf(this.product.getStock()));
        }

        btn_product.addActionListener(e -> {
            JTextField[] checkList = {
                    this.fld_product_name,
                    this.fld_product_code,
                    this.fld_product_price,
                    this.fld_product_stock

            };
            if (Helper.isFieldListEmpty(checkList)){
                Helper.showMsg("fiil");
            } else {
                this.product.setName(this.fld_product_name.getText());
                this.product.setCode(this.fld_product_code.getText());
                this.product.setPrice(Integer.parseInt(this.fld_product_price.getText()));
                this.product.setStock(Integer.parseInt(this.fld_product_stock.getText()));

                boolean result ;
                if (this.product.getId() == 0){
                    result = this.productController.save(this.product);
                } else {
                    result = this.productController.update(this.product);
                }
                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }

            }

        });
    }
}
