package View;

import Controller.CustomerController;
import Core.Helper;
import Entity.Customer;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private final JPopupMenu popup_customer = new JPopupMenu();

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

        loadCustomerTable(null);
        loadCustomerPopupMenu();

    }

    private void loadCustomerPopupMenu() {
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                tbl_customer.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        this.popup_customer.add("Güncelle").addActionListener(e->{
            int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(),0).toString());
            System.out.println(selectId);
        });
        this.popup_customer.add("Sil").addActionListener(e->{
            System.out.println("Sil e tıklandı");
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);

    }

    private void loadCustomerTable(ArrayList<Customer>customers) {
        Object[] columnCustomer = {"ID" , "Müşteri Adı", "Tipi" , "Telefon", "E-posta" , "Adres"};

        if (customers==null){
            customers=this.customerController.findAll();
        }

        //tablo sıfırlama
        DefaultTableModel  clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);

        this.tmdl_customer.setColumnIdentifiers(columnCustomer);
        for(Customer customer : customers) {
            Object[] rowObject = {
                    customer.getId(),
                    customer.getName(),
                    customer.getType(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getAddress()};
            this.tmdl_customer.addRow(rowObject);
        }

        /// TABLE CONFİGURATİON
        this.tbl_customer.setModel(tmdl_customer);
        this.tbl_customer.getTableHeader().setReorderingAllowed(false);
        this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_customer.setEnabled(false);
    }
}
