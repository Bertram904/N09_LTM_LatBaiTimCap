
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.DAO;

import entity.Player;
import java.sql.*;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
>>>>>>> 416203c66961665611e1360afaefebc1aca31bfa

/**
 *
 * @author ngotu
 */
public class DAO {

    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/latbai_db";
    private static final String URL_USER = "root";
<<<<<<< HEAD
    private static final String URL_PASS = "Anh2210anh";
    
=======
    private static final String URL_PASS = "baodang123";

>>>>>>> 416203c66961665611e1360afaefebc1aca31bfa
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

<<<<<<< HEAD
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
    
=======
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

    public void updatePlayerStatus(int playerId, boolean isOnline) throws SQLException {
        String query = "UPDATE tblPlayer SET isOnline = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setBoolean(1, isOnline);
        stmt.setInt(2, playerId);
        stmt.executeUpdate();
    }

    public List<Player> getPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM tblPlayer";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            players.add(new Player(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("totalScore"),
                    rs.getBoolean("isOnline")
            ));
        }

        return players;
    }

    public Pair<Player, Boolean> authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM tblPlayer WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Player authenticatePlayer = new Player(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("totalScore"),
                    rs.getBoolean("isOnline")
            );

            boolean isOffline = !rs.getBoolean("isOnline");
            return new Pair<>(authenticatePlayer, isOffline);
        }

        return new Pair<>(null, null);
    }
>>>>>>> 416203c66961665611e1360afaefebc1aca31bfa

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
