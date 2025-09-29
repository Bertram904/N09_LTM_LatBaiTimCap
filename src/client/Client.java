package client;

import client.controller.MainController;
import client.controller.RankingController;
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
    
    private MainController mainController;
    private RankingController rankingController;

    
    public Client(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void showMainUI() {
        try {
            URL fxml = getClass().getResource("/UI/MainUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load()); 

            mainController = loader.getController();
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
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }


    public void showRankingUI() {
        try {
            URL fxml = getClass().getResource("/UI/RankingUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());
            

            rankingController = loader.getController();
            // Nếu cần truyền Client vào RankingController thì thêm setClient(this);
            URL cssLocation = MainController.class.getResource("/UI/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }

            primaryStage.setTitle("Bảng xếp hạng");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace(); 
        }
    }
    
    public void showHistoryUI() {
        try {
            URL fxml = getClass().getResource("/UI/MainUI.fxml");
            FXMLLoader loader = new FXMLLoader(fxml);
            Scene scene = new Scene(loader.load());

            MainController controller = loader.getController();
            
            URL cssLocation = MainController.class.getResource("/UI/style.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }
            
            controller.setClient(this);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Main UI - Lịch sử đấu");
            primaryStage.show();

            // bật chế độ Lịch sử đấu ngay khi vào
            controller.onHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(ClientRunApp.class, args);
    }
}
