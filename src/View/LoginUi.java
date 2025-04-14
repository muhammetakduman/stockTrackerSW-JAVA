package View;

import Core.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUi extends JFrame {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_email;
    private JButton btn_login;
    private JLabel lbl_email;
    private JLabel lbl_password;
    private JPasswordField fld_password;

    public LoginUi(){
        this.add(container);
        this.setTitle("Customer Relation System ");
        this.setSize(400,400);

        //ekranı ortala

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width  - this.getSize().width ) /2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height  - this.getSize().height )  /2;
        this.setLocation(x,y);
        this.setVisible(true);

        btn_login.addActionListener(e -> {
            JTextField[] checkList = {this.fld_password,this.fld_email};
            if (!Helper.isEmailValid(this.fld_email.getText())){
                Helper.showMsg("Geçerli bir e-posta giriniz:");
            } else if (Helper.isFieldListEmpty(checkList)){
                Helper.showMsg("fiil");

            }else{
                Helper.showMsg("done");
            }
        });
    }
}
