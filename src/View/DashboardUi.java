package View;

import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;

public class DashboardUi extends JFrame {
    private JPanel panel1;
    private JPanel container;
    private User user;

    public DashboardUi(User user) {
        this.user = user;
        if (user == null) {
            Helper.showMsg("error");
            dispose();
            ;
        }

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);

        this.add(container);
        this.setTitle("Müşteri Yönetim System");
        this.setSize(1000, 500);

        System.out.println("giriş yaptı  :" + this.user.toString());
    }
}
