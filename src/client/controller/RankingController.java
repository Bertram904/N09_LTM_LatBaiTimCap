package client.controller;

import client.Client;
import entity.PlayerRanking;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class RankingController {

    @FXML
    private TableView<PlayerRanking> tblRanking;
    @FXML
    private TableColumn<PlayerRanking, Integer> colRank;
    @FXML
    private TableColumn<PlayerRanking, String> colName;
    @FXML
    private TableColumn<PlayerRanking, Integer> colTotalScore;
    @FXML
    private TableColumn<PlayerRanking, String> colWinAmount;

    @FXML
    private void initialize() {
        colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTotalScore.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
        colWinAmount.setCellValueFactory(new PropertyValueFactory<>("winAmount"));

        ObservableList<PlayerRanking> data = FXCollections.observableArrayList(
                new PlayerRanking(1, "DongLD", 1200, "1"),
                new PlayerRanking(2, "CornHub", 1100, "2"),
                new PlayerRanking(3, "KienPT", 1050, "3"),
                new PlayerRanking(4, "JohnnyBaoDang", 990, "4")
        );

        tblRanking.setItems(data);
    }
    
    // nút Back
    @FXML
    private void onBack() {
        try {
            // lấy stage hiện tại
            Stage stage = (Stage) tblRanking.getScene().getWindow();
            // dùng Client để quay lại
            new Client(stage).showMainUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
