package View;

import Controller.UserController;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;

public class RegisterUi extends JFrame {
    private JPanel container;
    private JTextField fld_name;
    private JTextField fld_email;
    private JPasswordField fld_password;
    private JButton btn_register;
    private JButton btn_cancel;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JLabel lbl_email;
    private JLabel lbl_name;
    private JLabel lbl_password;

    private final UserController userController = new UserController();

    public RegisterUi() {
        Helper.setTheme();
        Helper.optionPaneDialogTR();

        setTitle("Kayıt Ol");
        setContentPane(container);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        btn_register.addActionListener(e -> handleRegister());
        if (btn_cancel != null) {
            btn_cancel.addActionListener(e -> dispose());
        }
    }

    private void handleRegister() {
        String name = fld_name.getText().trim();
        String email = fld_email.getText().trim();
        String password = String.valueOf(fld_password.getPassword()).trim();

        if (Helper.isFieldListEmpty(new JTextField[]{fld_name, fld_email}) || password.isEmpty()) {
            Helper.showMsg("fill");
            return;
        }

        if (!Helper.isEmailValid(email)) {
            Helper.showMsg("Geçerli bir e-posta giriniz.");
            return;
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        if (userController.save(newUser)) {
            Helper.showMsg("done");
            dispose();
        } else {
            Helper.showMsg("Bu e-posta ile zaten bir kullanıcı var.");
        }
    }
}
