/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

import client.Client;
import entity.Player;
import java.io.IOException;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author ngotu
 */
public class MainController {
     private Client app;
     
    @FXML private HBox buttonBar;
    @FXML private Label lblSectionTitle;
    @FXML private Label lblWelcome;
    @FXML private TableView<Player> tblPlayers;
    @FXML private TableColumn<Player, String> colName;
    @FXML private TableColumn<Player, Integer> colScore;
    @FXML private TableColumn<Player, Boolean> colStatus;
    @FXML private Button btnBack;

    
    private ObservableList<Player> players = FXCollections.observableArrayList();

    public void setClient(Client app) {
        loadUsers();
        this.app = app;
    }
    
    private void loadUsers() {
        System.out.println("Loading users...");
        players.clear(); // tránh add trùng khi gọi lại
        players.addAll(
            new Player("Kien", "1", 12, true),
            new Player("Tuan Anh", "1", 20, false),
            new Player("Bao", "1", 8, true),
            new Player("Dong", "1", 0, true)
        );
        System.out.println("size users " + players.size());
        tblPlayers.setItems(players);
    }
    
    @FXML
    private void onLogout() {
        System.out.println("Lobby Logout");
    }
    
    @FXML
    private void onInvite() {
        System.out.println("Lobby invite");
    }
    
    @FXML
    public void onHistory() {
        // Đổi tiêu đề
        lblSectionTitle.setText("Lịch sử đấu");
        // Ẩn dãy nút
        buttonBar.setVisible(false);
        buttonBar.setManaged(false);
        
        btnBack.setVisible(true);
        btnBack.setManaged(true);

        // Cập nhật bảng hiển thị "danh sách người chơi" thành "chọn người để xem lịch sử"
        tblPlayers.setOnMouseClicked(event -> {
            Player selected = tblPlayers.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showPlayerHistory(selected);
            }
        });
    }

    // Hàm hiển thị lịch sử của người chơi
    private void showPlayerHistory(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HistoryUI.fxml"));
            Parent root = loader.load();

            HistoryController controller = loader.getController();
            controller.setClient(app); // truyền client vào
            controller.loadHistoryForPlayer(player);

            Stage stage = (Stage) tblPlayers.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lịch sử đấu của " + player.getUsername());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    private void onRanking() {
        if (app != null) {
            app.showRankingUI();
        } else {
            System.err.println("Client chưa được set!");
        }
    }


    
    @FXML
    private void onRefresh() {
        System.out.println("Lobby refresh");
    }
    
    // back lai MainUI
    @FXML
    private void onBackToMain() {
        lblSectionTitle.setText("Danh sách người chơi");

        buttonBar.setVisible(true);
        buttonBar.setManaged(true);

        btnBack.setVisible(false);
        btnBack.setManaged(false);

        // reset lại sự kiện click bảng -> không mở History nữa
        tblPlayers.setOnMouseClicked(null);
    }

    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("isOnline"));
        
        // map boolean -> text
        colStatus.setCellFactory(column -> new TableCell<Player, Boolean>() {
            @Override
            protected void updateItem(Boolean online, boolean empty) {
                super.updateItem(online, empty);
                if (empty || online == null) {
                    setText(null);
                } else {
                    setText(online ? "online" : "offline");
                }
            }
        });
    }
}
