/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import entity.Player;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author ngotu
 */
public class DatabaseManager {

    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/latbai_db";
    private static final String URL_USER = "root";
    private static final String URL_PASS = "baodang123";

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

    public void updatePlayerStatus(int playerId, String status) throws SQLException {
        String query = "UPDATE tblPlayer SET status = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, status);
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
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("isOnline"),
                    rs.getInt("totalScore")
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
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("isOnline"),
                    rs.getInt("totalScore")
            );

            boolean isOffline = !rs.getBoolean("isOnline");
            return new Pair<>(authenticatePlayer, isOffline);
        }

        return new Pair<>(null, null);
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
