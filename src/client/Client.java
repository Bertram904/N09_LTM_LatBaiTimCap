package client;

import client.controller.LoginController;
import client.controller.MainController;
import constants.MessageType;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import javafx.fxml.*;
import javafx.scene.*;
import entity.Message;
import entity.Player;
import javafx.application.Platform;

/**
 *
 * @author ngotu
 */
public class Client {

    private final Stage primaryStage;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private MainController mainController;
    private LoginController loginController;

    public Client(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

        System.out.println("Đã kết nối tới server: " + host + ":" + port);

        listenServer();
    }

    public void sendMessage(Message msg) {
        try {
            if (out != null) {
                out.writeObject(msg);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenServer() {
        new Thread(() -> {
            try {
                while (true) {
                    Message msg = (Message) in.readObject();
                    switch (msg.getType()) {
                        case MessageType.LOGIN_SUCCESS:
                            Player player = (Player) msg.getContent();
                            System.out.println("Login OK: " + player.getUsername());
                            Platform.runLater(() -> showMainUI());
                            break;
                        case MessageType.LOGIN_FAILURE:
                            String err = (String) msg.getContent();
                            System.out.println("Login fail: " + err);
                            break;
                        case MessageType.GET_PLAYERS:
                            
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginUI() {
        try {
            URL fxml = getClass().getResource("/UI/LoginUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());

            loginController = loader.getController();
            if (loginController == null) {
                loginController = new LoginController();
                loader.setController(loginController);
            }

            loginController.setClient(this);
            URL cssLocation = MainController.class.getResource("/UI/style.css");
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

    public void showMainUI() {
        try {
            URL fxml = getClass().getResource("/UI/MainUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());

            mainController = loader.getController();
            if (mainController == null) {
                mainController = new MainController();
                loader.setController(mainController);
            }
            mainController.setClient(this);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(ClientRunApp.class, args);
    }
}
