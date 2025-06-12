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

    public boolean save(User user) {
        return this.userDao.save(user);
    }

    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

}
