
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import entity.Player;
import java.sql.*;

/**
 *
 * @author ngotu
 */
public class DatabaseManager {

    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/latbai_db";
    private static final String URL_USER = "root";
    private static final String URL_PASS = "Anh2210anh";
    
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DatabaseManager() throws SQLException {
        conn = DriverManager.getConnection(URL_JDBC, URL_USER, URL_PASS);
    }

    public static Player getPlayer(String username) throws SQLException {
        String sql = "SELECT * FROM tblPlayer WHERE USERNAME = ?";
        Player player = new Player();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            player = new Player();
            player.setUsername(rs.getString("username"));
        }
        return player; 
    }
    
    public static void main(String[] args) throws SQLException {
        DatabaseManager db = new DatabaseManager();
        try {
            System.out.println("Ket noi db thanh cong");
            System.out.println(db.getPlayer("kienpt").getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
