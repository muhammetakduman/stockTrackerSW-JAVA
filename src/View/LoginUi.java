package View;

import Controller.UserController;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;

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
    private JButton btn_register;
    private final UserController userController;

    public LoginUi() {
        Helper.setTheme();
        Helper.optionPaneDialogTR();
        this.userController = new UserController();

        this.setTitle("Customer Relation System");
        this.setContentPane(container);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ekranı ortala
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);

        // Giriş butonu
        this.btn_login.addActionListener(e -> {
            String email = fld_email.getText().trim();
            String password = new String(fld_password.getPassword()).trim();
            JTextField[] checkList = {fld_email};

            if (!Helper.isEmailValid(email)) {
                Helper.showMsg("Geçerli bir e-posta giriniz:");
                return;
            }

            if (Helper.isFieldListEmpty(checkList) || password.isEmpty()) {
                Helper.showMsg("fill");
                return;
            }

            User user = userController.findByLogin(email, password);
            if (user == null) {
                Helper.showMsg("Kullanıcı bulunamadı.");
            } else {
                System.out.println(user);
                this.dispose();
                new DashboardUi(user);
            }
        });

        // Kayıt Ol butonu
        btn_register.addActionListener(e -> new RegisterUi());
    }
}
