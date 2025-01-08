package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connect = null;
    private static Util instance = null;

    private Util() {
        try {
            if (connect == null) {
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            }
        } catch (SQLException e) {
            System.out.println("Нету соединения");
        }
    }

    public Connection getConnect() {
        return connect;
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
}
