
import Controller.UserController;
import Core.Helper;
import Entity.User;
import View.DashboardUi;
import View.LoginUi;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        //LoginUi loginUI  = new LoginUi();
        UserController userController = new UserController();
        User user = userController.findByLogin("akdumanmuhammet34@gmail.com","123123");
        DashboardUi dashboardUi = new DashboardUi(user);


    }
}