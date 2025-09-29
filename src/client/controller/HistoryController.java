package client.controller;

import client.Client;
import entity.MatchHistory;
import entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryController {

    @FXML
    private TableView<MatchHistory> tblHistory;
    @FXML
    private TableColumn<MatchHistory, Integer> colMatchId;
    @FXML
    private TableColumn<MatchHistory, String> colOpponent;
    @FXML
    private TableColumn<MatchHistory, String> colResult;
    @FXML
    private TableColumn<MatchHistory, Integer> colScoreChange;
    @FXML
    private TableColumn<MatchHistory, String> colTime;

    private ObservableList<MatchHistory> historyData = FXCollections.observableArrayList();
    
    // Back lich su dau
    private Client app;  

    public void setClient(Client app) {
        this.app = app;
    }


    @FXML
    private void initialize() {
        colMatchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        colOpponent.setCellValueFactory(new PropertyValueFactory<>("opponentName"));
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colScoreChange.setCellValueFactory(new PropertyValueFactory<>("scoreChange"));

        // Định dạng thời gian hiển thị
        colTime.setCellValueFactory(cellData -> {
            LocalDateTime time = cellData.getValue().getTime();
            String formatted = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });

        // Dữ liệu demo
        historyData.addAll(
                new MatchHistory(101, "PlayerB", "WIN", +20, LocalDateTime.now().minusMinutes(15)),
                new MatchHistory(102, "PlayerC", "LOSE", -15, LocalDateTime.now().minusMinutes(40)),
                new MatchHistory(103, "PlayerD", "DRAW", 0, LocalDateTime.now().minusHours(2))
        );

        tblHistory.setItems(historyData);
    }

    @FXML
    private void onBack() {
        if (app != null) {
            app.showHistoryUI(); // mở lại MainUI ở chế độ "Lịch sử đấu"
        } else {
            System.err.println("Client chưa được thiet lap trong HistoryController");
        }
    }
    
    public void loadHistoryForPlayer(Player player) {
        // TODO: load dữ liệu lịch sử từ DB hoặc danh sách tạm
        historyData.clear();
        historyData.addAll(
            new MatchHistory(201, "PlayerX", "WIN", +10, LocalDateTime.now().minusMinutes(5)),
            new MatchHistory(202, "PlayerY", "LOSE", -5, LocalDateTime.now().minusMinutes(30))
        );
        tblHistory.setItems(historyData);
    }
}
