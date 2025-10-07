package server.DAO;

import entity.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class PlayerDAO extends DAO {

    public PlayerDAO() throws SQLException {
        super();
    }

    public Player getDetailPlayer(String username) throws SQLException {
        String sql = "SELECT * FROM tblPlayer WHERE username = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        ResultSet rs = stm.executeQuery();

        Player player = null;
        if (rs.next()) {
            player = new Player(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("totalScore"),
                    rs.getBoolean("isOnline")
            );
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

    public List<Player> getAllPlayers() throws SQLException {
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

    public static void main(String[] args) {
        try {
            PlayerDAO dao = new PlayerDAO();
            Player p = dao.getDetailPlayer("kien");
            if (p != null) {
                System.out.println("Người chơi: " + p.getUsername());
            } else {
                System.out.println("Không tìm thấy người chơi!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
