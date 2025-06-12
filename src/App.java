
import Controller.UserController;
import Core.Helper;
import Entity.User;
import View.DashboardUi;
import View.LoginUi;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        new LoginUi();
    }
}