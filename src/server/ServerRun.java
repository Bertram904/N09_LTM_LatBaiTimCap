/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.concurrent.*;
import entity.Message;
import server.DAO.DAO;

/**
 *
 * @author ngotu
 */
public class ServerRun {
    private static final int PORT = 23456;
    private ServerSocket serverSocket;
    private DAO databaseConnection;
    private volatile boolean isRunning = false;
    
    public ServerRun() {
        try {
            serverSocket = new ServerSocket(PORT);
            databaseConnection = new DAO();
            System.out.println("This server is start on " + PORT);
            listenning();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void listenning() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Have a connected from: " + socket.getInetAddress());
                new Thread();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new ServerRun();
    }
}
