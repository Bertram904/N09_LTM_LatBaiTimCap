/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

import client.Client;
import entity.Message;
import entity.Player;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;


/**
 *
 * @author ngotu
 */
public class MainController {
    private Client app;
    private Player userLogin;
    
    @FXML private Label lblWelcome;
    @FXML private TableView<Player> tblPlayers;
    @FXML private TableColumn<Player, String> colName;
    @FXML private TableColumn<Player, Integer> colScore;
    @FXML private TableColumn<Player, Boolean> colStatus;
    
    private ObservableList<Player> players = FXCollections.observableArrayList();

    public void setClient(Client app) throws IOException {
        loadUsers();
        this.app = app;
    }
    
    private void loadUsers() throws IOException {
        System.out.println("Loading player...");
        players.clear(); // tránh add trùng khi gọi lại
        players.addAll(
                new Player("Kien", "1", 12, true),
                new Player("Tuan Anh", "1", 20, false),
                new Player("Bao", "1", 8, true),
                new Player("Dong", "1", 0, true));
        System.out.println("size users " + players.size());
        tblPlayers.setItems(players);
        
        try {
            Message request = new Message("get_players", null);
            app.sendMessage(request);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Khong call dc server");
        }
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
                    showAcceptDialog(1); // test mock ui
                    
                    // request to server
                    int fromId = userLogin.getId(); // id of user login 
                    int toId = player.getId(); // id of player invite
                    Object[] content = { fromId, toId };
                    Message request = new Message("invite_player", content);
//                    try {
//                        app.sendMessage(request);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
////                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                } else {
                    System.out.println("Cancel invite player");
                }
            });
        }
    }
    
    private void showAcceptDialog(int fromId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Accept the invitation");
        alert.setHeaderText("Would you like to accept the challenge from " + fromId + "?");
        alert.setContentText("Accept the invitations now!");

        ButtonType btnOk = new ButtonType("Ok");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnOk, btnCancel);

        // show alert
        alert.showAndWait().ifPresent(result -> {
            if(result == btnOk) {
                System.out.println("join " + fromId);
                // TODO gui yeu cau
                /* -> server
                    action: 'invite_response'
                    from: B
                    to: A
                    accepted: true/false
                */
            } else {
                System.out.println("Cancel invitation from " + fromId);
            }
        });
    }
}