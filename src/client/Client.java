/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import client.controller.MainController;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import javafx.fxml.*;
import javafx.scene.*;

/**
 *
 * @author ngotu
 */
public class Client {
    private final Stage primaryStage;
    
    private MainController lobbyController;
    
    public Client(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void showMainUI() {
        try {
            URL fxml = getClass().getResource("/UI/MainUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load()); 

            lobbyController = loader.getController();
            lobbyController.setClient(this);

            URL cssLocation = MainController.class.getResource("/UI/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
                System.out.println("CSS file loaded: " + cssLocation.toExternalForm());
            } else {
                System.err.println("Cannot find CSS file: style.css");
            }

            primaryStage.setTitle("Main UI");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
    
    public static void main(String[] args) {
        javafx.application.Application.launch(ClientRunApp.class, args);
    }
}
