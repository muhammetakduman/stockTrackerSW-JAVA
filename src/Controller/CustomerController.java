package Controller;

import Core.Helper;
import Dao.CustomerDao;
import Entity.Customer;

import java.util.ArrayList;

public class CustomerController {

    private  final CustomerDao customerDao = new CustomerDao();


    /// all Customer service
    public ArrayList<Customer> findAll(){
        return this.customerDao.findAll();
    }


    /// db save
    public boolean save(Customer customer){
        return this.customerDao.save(customer);
    }


    /// selector by ıd
    public Customer getById(int id){
        return this.customerDao.getById(id);
    }

    /// update this Customer
    public boolean update(Customer customer){
        if (this.getById(customer.getId()) == null){
            Helper.showMsg(customer.getId() + "ID kayıtlı müşteri bulunamadı !!");
            return false;
        }
        return this.customerDao.update(customer);
    }

    /// this method refree id and delete item
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg( id + "ID kayıtlı müşteri bulunmadı");
            return false;
        }
        return this.customerDao.delete(id);
    }


    /// filter
    public ArrayList<Customer> filter(String name , Customer.TYPE type){
        String query = "SELECT * FROM customer";
        ArrayList<String > whereList = new ArrayList<>();
        if (name.length()>0){
            whereList.add("name LIKE '%" + name + "%'");
        }
        if (type != null){
            whereList.add("type = '" + type + "'");
        }
        if ( whereList.size()>0){
            String whereQuery = String.join(" AND " ,whereList);
            query += " WHERE " + whereQuery;
        }
        return this.customerDao.query(query);

    }
}
