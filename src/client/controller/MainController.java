/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

import client.Client;
import entity.Player;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ngotu
 */
public class MainController {

    private Client app;

    @FXML
    private Label lblWelcome;
    @FXML
    private TableView<Player> tblPlayers;
    @FXML
    private TableColumn<Player, String> colName;
    @FXML
    private TableColumn<Player, Integer> colScore;
    @FXML
    private TableColumn<Player, Boolean> colStatus;

    private ObservableList<Player> players = FXCollections.observableArrayList();

    public void setClient(Client app) {
        loadUsers();
        this.app = app;
    }

    private void loadUsers() {
        System.out.println("Loading users...");
        players.clear();
        players.addAll(
                new Player("Kien", "1", 12, true),
                new Player("Tuan Anh", "1", 20, false),
                new Player("Bao", "1", 8, true),
                new Player("Dong", "1", 0, true));
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
