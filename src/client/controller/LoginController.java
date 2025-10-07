package client.controller;

import client.Client;
import constants.MessageType;
import entity.Message;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private Client app;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    public void handleLogin(ActionEvent event) {

        if (statusLabel != null) {
            statusLabel.setText("Đang đăng nhập...");
        }

        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            System.out.println("Loi User");
            if (statusLabel != null) {
                statusLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            }
            return;
        }

        if (app == null) {
            System.out.println("Loi chua ket noi");
            if (statusLabel != null) {
                statusLabel.setText("Lỗi: Chưa kết nối!");
            }
            return;
        }
        try {
            app.sendMessage(new Message(MessageType.LOGIN, new String[]{user, pass}));
            if (statusLabel != null) {
                statusLabel.setText("Đã gửi yêu cầu đăng nhập...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (statusLabel != null) {
                statusLabel.setText("Lỗi: " + e.getMessage());
            }
        }
    }

    public void setClient(Client app) {
        this.app = app;
    }
}
