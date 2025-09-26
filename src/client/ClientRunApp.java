package client;

import javafx.application.Application;
import javafx.stage.*;


public class ClientRunApp extends Application {   
    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client(primaryStage);
        client.showMainUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
