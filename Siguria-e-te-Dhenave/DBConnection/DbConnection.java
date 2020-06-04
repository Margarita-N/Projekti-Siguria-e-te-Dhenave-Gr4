package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection connection;
    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/siguri?autoReconnect=true&useSSL=false", "root", "riseandshine");

            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return connection;
        }
    }

            }
