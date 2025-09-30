package server;

import constants.MessageType;
import entity.Message;
import entity.Player;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import javafx.util.Pair;
import server.DAO.DAO;

public class ClientHandler implements Runnable {

    private Socket socket;
    private GameServer server;
    private DAO dbManager;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Player player;
    private GameRoom gameRoom;
    private volatile boolean isRunning = true;

    public ClientHandler(Socket socket, GameServer server, DAO dbManager) {
        this.socket = socket;
        this.server = server;
        this.dbManager = dbManager;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                Message message = (Message) in.readObject();
                if (message != null) {
                    handleMessage(message);
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Kết nối với " + (player != null ? player.getUsername() : "client") + " bị ngắt.");
            isRunning = false;
//            if (gameRoom != null) {
//                try {
//                    gameRoom.handlePlayerDisconnect(this);
//                } catch (IOException | SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
        } finally {
            try {
                if (player != null) {
                    dbManager.updatePlayerStatus(player.getId(), false);
                    server.broadcast(new Message(MessageType.STATUS_UPDATE, player.getUsername() + " đã offline."));
                    server.removeClient(this);
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMessage(Message message) throws IOException, SQLException {
        switch (message.getType()) {
            case MessageType.LOGIN:
                handleLogin(message);
                break;
            case MessageType.GET_PLAYERS:
                handleGetPlayers();
                break;

            case MessageType.LOGOUT:
                handleLogout();
                break;
        }
    }

    private void handleLogin(Message message) throws IOException, SQLException {
        System.out.println("Loi Login Client Handler");
        String[] credentials = (String[]) message.getContent();
        String username = credentials[0];
        String password = credentials[1];
        Pair<Player, Boolean> pairAuthnticatedUser = dbManager.authenticate(username, password);
        Player _player = pairAuthnticatedUser.getKey();
        Boolean isOffline = pairAuthnticatedUser.getValue();
        if (_player != null && isOffline == true) {
            this.player = _player;
            dbManager.updatePlayerStatus(player.getId(), true);
            player.setIsOnline(true);
            sendMessage(new Message(MessageType.LOGIN_SUCCESS, player));
            server.broadcast(new Message(MessageType.STATUS_UPDATE, player.getUsername() + " đã online."));
            server.addClient(player.getId(), this);

        } else if (_player != null && isOffline == false) {
            sendMessage(new Message(MessageType.LOGIN_FAILURE, "Tài khoản được đăng nhập ở nơi khác"));
        } else {
            sendMessage(new Message(MessageType.LOGIN_FAILURE, "Tài khoản hoặc mật khẩu không đúng"));
        }
    }

    private void handleLogout() throws IOException, SQLException {
        if (player != null) {
            dbManager.updatePlayerStatus(player.getId(), false);
            player.setIsOnline(false);
            server.broadcast(new Message(MessageType.STATUS_UPDATE, player.getUsername() + " đã offline."));
            if (socket != null && !socket.isClosed()) {
                sendMessage(new Message(MessageType.LOGOUT_SUCCESS, "Đăng xuất thành công."));
            }
            isRunning = false;
            server.removeClient(this);
            socket.close();
        }
    }

    private void handleGetPlayers() throws IOException, SQLException {
        List<Player> players = dbManager.getPlayers();
        sendMessage(new Message(MessageType.PLAYER_LIST, player));
    }

    public void sendMessage(Message message) {
        try {
            if (socket != null && !socket.isClosed()) {
                out.writeObject(message);
                out.flush();
            } else {
                System.out.println(
                        "Socket đã đóng, không thể gửi tin nhắn tới " + (player != null ? player.getUsername() : "client"));
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi gửi tin nhắn tới " + (player != null ? player.getUsername() : "client") + ": "
                    + e.getMessage());
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public GameServer getServer() {
        return server;
    }

}
