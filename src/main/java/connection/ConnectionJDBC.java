package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to DB
 */
public class ConnectionJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";


    private Connection connection;

    public ConnectionJDBC() {
        try {


            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
