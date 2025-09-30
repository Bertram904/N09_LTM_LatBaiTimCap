/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import entity.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import server.DAO.DAO;

/**
 *
 * @author ngotu
 */
public class ServerRun {

    private static final int PORT = 23456;
    private ServerSocket serverSocket;
    private DAO dbManager;
    private ConcurrentHashMap<Integer, ClientHandler> clientMap = new ConcurrentHashMap<>();

    public ServerRun() {
        try {
            serverSocket = new ServerSocket(PORT);
            dbManager = new DAO();
            System.out.println("Server started on PORT: " + PORT);
            listenForClients();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addClient(int id, ClientHandler clientHandler) {
        clientMap.put(id, clientHandler);
    }

    public synchronized ClientHandler getClientHandler(int id) {
        return clientMap.get((id));
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

    private void listenForClients() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Connection received from " + socket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(socket, this, dbManager);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ServerRun();
    }
}