/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import client.controller.LobbyController;
import client.controller.LoginController;
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

    private Stage primaryStage;
    private LoginController loginController;

    private LobbyController lobbyController;

    public Client(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginUi() {
        try {
            URL fxml = getClass().getResource("/UI/LoginUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());

            loginController = loader.getController();
            loginController.setClient(this);

            URL cssLocation = LoginController.class.getResource("/UI/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
                System.out.println("CSS file loaded: " + cssLocation.toExternalForm());
            } else {
                System.err.println("Cannot find CSS file: style.css");
            }

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLobbyUI() {
        try {
            URL fxml = getClass().getResource("/UI/LobbyUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());

            lobbyController = loader.getController();
            lobbyController.setClient(this);

            URL cssLocation = LobbyController.class.getResource("/UI/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
                System.out.println("CSS file loaded: " + cssLocation.toExternalForm());
            } else {
                System.err.println("Cannot find CSS file: style.css");
            }

            primaryStage.setTitle("Lobby");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        javafx.application.Application.launch(ClientRunApp.class, args);
//    }
}
