package Core;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    //singleton desing pattern

    private static Database instance = null;
    private  Connection connection = null;
    private final String DB_URL = "jdbc:mysql://localhost:3306/customermanage";
    private final String DB_USER = "root";
    private final String DB_PASSWROD = "";

    private Database(){
        try {
            this.connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWROD

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public static Connection getInstance(){
        try {
            if (instance == null  || instance.getConnection().isClosed()){
                instance = new Database();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance.getConnection();
    }
}
