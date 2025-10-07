package server;

public class ServerRun {

    private static final int PORT = 23456;

    public static void main(String[] args) {
        GameServer server = new GameServer(PORT);
        server.start();
    }
}