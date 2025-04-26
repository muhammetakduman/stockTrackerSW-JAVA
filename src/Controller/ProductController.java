package Controller;

import Dao.ProductDao;
import Entity.Product;

import java.util.ArrayList;

public class ProductController {
    private final ProductDao productDao = new ProductDao();

    public ArrayList<Product> findAll(){
        return this.productDao.findAll();
    }
}
