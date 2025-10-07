package server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/latbai_db";
    private static final String URL_USER = "root";
    private static final String URL_PASS = "baodang123";

    protected static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DAO() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL_JDBC, URL_USER, URL_PASS);
        }
    }
}