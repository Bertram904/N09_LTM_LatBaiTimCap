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
    
    public static Player getPlayer() throws SQLException {
        String sql = "SELECT * FROM tblPlayer WHERE USERNAME = 'a'";
        Player player = new Player();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
                player = new Player();
                player.setUsername(rs.getString("username"));
        }
        player.setUsername(rs.getString("username"));
        return player;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getPlayer().getUsername());
    } 
}
