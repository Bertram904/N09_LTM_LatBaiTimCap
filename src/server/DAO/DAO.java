
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.DAO;

import entity.Player;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ngotu
 */
public class DAO {

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

    public DAO() throws SQLException {
        conn = DriverManager.getConnection(URL_JDBC, URL_USER, URL_PASS);
    }

//    public Player getPlayer() throws SQLException {
//        String sql = "SELECT * FROM tblplayer WHERE USERNAME = ?";
//        Player player = new Player();
//        PreparedStatement stm = conn.prepareStatement(sql);
//        stm.setString(1, "");
//        ResultSet rs = stm.executeQuery();
//        if (rs.next()) {
//            player = new Player();
//            player.setUsername(rs.getString("username"));
//        }
//        return player; 
//    }
    

    public static void main(String[] args) throws SQLException {
        DAO db = new DAO();
        try {
            System.out.println("Ket noi db thanh cong");
            //System.out.println(db.getPlayer("kienpt").getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
