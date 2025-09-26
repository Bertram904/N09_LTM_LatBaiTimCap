/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

import client.Client;
import entity.Player;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle;

/**
 *
 * @author ngotu
 */
public class MainController {
     private Client app;
    
    @FXML private Label lblWelcome;
    @FXML private TableView<Player> tblPlayers;
    @FXML private TableColumn<Player, String> colName;
    @FXML private TableColumn<Player, Integer> colScore;
    @FXML private TableColumn<Player, Boolean> colStatus;
    
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
    private void onHistory() {
        System.out.println("On History");
    }
    
    @FXML
    private void onRanking() {
        System.out.println("Lobby show ranking");
    }
    
    @FXML
    private void onRefresh() {
        System.out.println("Lobby refresh");
    }
    
    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("isOnline"));
        
        // config render ui for isOnline
        colStatus.setCellFactory(column -> new TableCell<Player, Boolean>() {
            private final Circle circle = new Circle(6); // r = 6px
            @Override
            protected void updateItem(Boolean online, boolean empty) {
                super.updateItem(online, empty);
                if (empty || online == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setText(online ? "online" : "offline");
                    circle.setFill(online ? Color.GREENYELLOW : Color.SILVER);
                    setGraphic(circle);
                }
            }
        });
        
        // settings for double-click a row -> open popup
        tblPlayers.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getClickCount() == 2) { // double-click
                    Player clickedPlayer = row.getItem();
                    // show popup invite for player if player online
                    showInviteDialog(clickedPlayer);
                }
            });
            return row;
        });
    }
    
    private void showInviteDialog(Player player) {
        // Player is online
        if(player.getIsOnline()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Invite Player");
            alert.setHeaderText("Do you want to challenge " + player.getUsername() + "?");
            alert.setContentText("Send invitations now!");

            ButtonType btnOk = new ButtonType("Ok");
            ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btnOk, btnCancel);

            // show alert
            alert.showAndWait().ifPresent(result -> {
                if(result == btnOk) {
                    System.out.println("Invited " + player.getUsername());
                    // TODO gui yeu cau
                    /* -> server
                        action: 'invite'
                        from: A
                        to: B
                    */
                    // mock
                    showAcceptDialog(player.getUsername());
                } else {
                    System.out.println("Cancel invite player");
                }
            });
        }
    }
    
    private void showAcceptDialog(String fromUser) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Accept the invitation");
        alert.setHeaderText("Would you like to accept the challenge from " + fromUser + "?");
        alert.setContentText("Accept the invitations now!");

        ButtonType btnOk = new ButtonType("Ok");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnOk, btnCancel);

        // show alert
        alert.showAndWait().ifPresent(result -> {
            if(result == btnOk) {
                System.out.println("join " + fromUser);
                // TODO gui yeu cau
                /* -> server
                    action: 'invite_response'
                    from: B
                    to: A
                    accepted: true/false
                */
            } else {
                System.out.println("Cancel invitation from " + fromUser);
            }
        });
    }
}
