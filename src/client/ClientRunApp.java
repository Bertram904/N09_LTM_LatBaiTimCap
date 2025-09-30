package client;

import javafx.application.Application;
import javafx.stage.*;

public class ClientRunApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Client client = new Client(primaryStage);

        try {
            client.connect("26.207.177.68", 23456);
            client.showLoginUI();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Không thể kết nối đến server!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
