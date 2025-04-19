package View;

import Controller.CustomerController;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import java.awt.*;

public class DashboardUi extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fid_f_customer_name;
    private JButton btn_customer_filter;
    private JButton btn_customer_filter_reset;
    private JButton btn_customer_new;
    private JComboBox cmb_customer_type;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private User user;
    private  CustomerController customerController;

    public DashboardUi(User user) {
        this.user = user;
        this.customerController = new CustomerController();

        if (user == null) {
            Helper.showMsg("error");
            dispose();

        }

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);

        this.add(container);
        this.setTitle("Müşteri Yönetim System");
        this.setSize(1000, 500);

        this.lbl_welcome.setText("Hoşgeldiniz :" + this.user.getName());

        //btn onclikl olduğunda reacta atıf yapalım :)
        this.btn_logout.addActionListener(e -> {
            dispose();
            LoginUi loginUi = new LoginUi();
        });
        System.out.println(this.customerController.findAll());
    }
}
