package client.controller;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private Client app;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if ("admin".equals(user) && "123".equals(pass)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void setClient(Client app) {
        this.app = app;
    }
}
