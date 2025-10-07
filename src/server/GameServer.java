package server;

import entity.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import server.DAO.DAO;
import server.DAO.PlayerDAO;

public class GameServer {

    private final int port;
    private ServerSocket serverSocket;
    private PlayerDAO playerDAO;
    private final ConcurrentHashMap<Integer, ClientHandler> clientMap;

    public GameServer(int port) {
        this.port = port;
        this.clientMap = new ConcurrentHashMap<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            playerDAO = new PlayerDAO();
            System.out.println("Game server started on port: " + port);

            listenForClients();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void listenForClients() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket, this, playerDAO);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ========== Quản lý Client ==========
    public synchronized void addClient(int id, ClientHandler clientHandler) {
        clientMap.put(id, clientHandler);
    }

    public synchronized ClientHandler getClientHandler(int id) {
        return clientMap.get(id);
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        if (clientHandler.getPlayer() != null) {
            clientMap.remove(clientHandler.getPlayer().getId());
        }
    }

    public synchronized void broadcast(Message message) {
        for (ClientHandler client : clientMap.values()) {
            client.sendMessage(message);
        }
    }
}
