package View;

import Controller.CustomerController;
import Controller.ProductController;
import Core.Helper;
import Entity.Customer;
import Entity.Product;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DashboardUi extends JFrame {

    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fid_f_customer_name;
    private JButton btn_customer_filter;
    private JButton btn_customer_filter_reset;
    private JButton btn_customer_new;
    private JComboBox<Customer.TYPE> cmb_f_customer_type;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private JPanel pnl_product;
    private JScrollPane scrl_product;
    private JTable tbl_product;
    private JPanel pnl_product_filter;
    private JTextField fld_f_product_name;
    private JTextField fld_f_product_code;
    private JComboBox cmb_f_product_stock;
    private JButton btn_product_filter;
    private JButton btn_product_filter_reset;
    private JButton btn_product_new;
    private JLabel lbl_f_product_name;
    private JLabel lbl_f_product_code;
    private JLabel lbl_f_prodcut_stock;
    private User user;
    private CustomerController customerController;
    private ProductController productController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private DefaultTableModel tmdl_product = new DefaultTableModel();
    private final JPopupMenu popup_customer = new JPopupMenu();
    private final JPopupMenu popup_product = new JPopupMenu();

    public DashboardUi(User user) {

        this.user = user;
        this.customerController = new CustomerController();
        this.productController = new ProductController();

        if (user == null) {
            Helper.showMsg("error");
            dispose();
        }

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(1000, 500);

        this.setContentPane(container); // this.add(container); yerine bu daha doğrudur
        this.setVisible(true);

        this.lbl_welcome.setText("Hoşgeldiniz :" + this.user.getName());

        this.btn_logout.addActionListener(e -> {
            dispose();
            new LoginUi();
        });

        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();

        this.cmb_f_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_f_customer_type.setSelectedItem(null);

        //productTable
        loadProductTable(null);
        loadProductPopupMenu();
        loadProductButtonEvent();
    }

    private void loadProductButtonEvent() {
        this.btn_product_new.addActionListener(e -> {
            ProductUi productUi = new ProductUi(new Product());
            productUi.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null);

                }
            });
        });
    }

    private void loadProductPopupMenu(){
        this.tbl_product.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int selectedRow = tbl_product.rowAtPoint(e.getPoint());
                tbl_product.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        this.popup_product.add("Güncelle").addActionListener(e->{
          int selectId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(),0).toString());
          ProductUi productUi = new ProductUi(this.productController.getById(selectId));
          productUi.addWindowListener(new WindowAdapter() {
              @Override
              public void windowClosed(WindowEvent e) {
                  loadProductTable(null);
              }
          });
        });
        this.popup_product.add("Sil").addActionListener(e->{
            int selectId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(),0).toString());
            if (Helper.confirm("sure")){
                if (this.productController.delete(selectId)){
                    Helper.showMsg("done");
                    loadProductTable(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_product.setComponentPopupMenu(this.popup_product);
    }

    private void loadProductTable(ArrayList<Product> products) {
        Object[] columnProduct = {"ID", "Ürün ADI", "Ürün Kodu", "Fiyat", "Stock"};

        if (products == null) {
            products = this.productController.findAll();
        }

        //tablo sıfırlama
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_product.getModel();
        clearModel.setRowCount(0);

        this.tmdl_product.setColumnIdentifiers(columnProduct);
        for (Product product : products) {
            Object[] rowObject = {
                    product.getId(),
                    product.getName(),
                    product.getCode(),
                    product.getPrice(),
                    product.getStock(),
                };
            this.tmdl_product.addRow(rowObject);
        }

        /// TABLE CONFİGURATİON
        this.tbl_product.setModel(tmdl_product);
        this.tbl_product.getTableHeader().setReorderingAllowed(false);
        this.tbl_product.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_product.setEnabled(false);
    }


    private void loadCustomerButtonEvent() {
        this.btn_customer_new.addActionListener(e -> {
            CustomerUi customerUi = new CustomerUi(new Customer());
            customerUi.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });

        });

        this.btn_customer_filter.addActionListener(e -> {
            ArrayList<Customer> filteredCustomers = this.customerController.filter(
                    this.fid_f_customer_name.getText(),
                    (Customer.TYPE) this.cmb_f_customer_type.getSelectedItem()
            );
            loadCustomerTable(filteredCustomers);

        });
        this.btn_customer_filter_reset.addActionListener(e -> {
            loadCustomerTable(null);
            this.fid_f_customer_name.setText(null);
            this.cmb_f_customer_type.setSelectedItem(null);

        });
    }

    private void loadCustomerPopupMenu() {
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        this.popup_customer.add("Güncelle").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString());
            Customer editedCustomer = this.customerController.getById(selectId);
            CustomerUi customerUi = new CustomerUi(this.customerController.getById(selectId));
            customerUi.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });
        });
        this.popup_customer.add("Sil").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.customerController.delete(selectId)) {
                    Helper.showMsg("done");
                    loadCustomerTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);

    }

    private void loadCustomerTable(ArrayList<Customer> customers) {
        Object[] columnCustomer = {"ID", "Müşteri Adı", "Tipi", "Telefon", "E-posta", "Adres"};

        if (customers == null) {
            customers = this.customerController.findAll();
        }

        //tablo sıfırlama
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);

        this.tmdl_customer.setColumnIdentifiers(columnCustomer);
        for (Customer customer : customers) {
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
