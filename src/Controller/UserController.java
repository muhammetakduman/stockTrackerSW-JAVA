package Controller;

import Core.Helper;
import Dao.UserDao;
import Entity.User;


public class UserController {
    private final UserDao userDao = new UserDao();

    public User findByLogin(String email,String password){
        Helper.isEmailValid(email);
        return this.userDao.findByLogin(email, password);

    }
}
