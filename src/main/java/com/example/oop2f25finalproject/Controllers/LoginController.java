package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Login;
import com.example.oop2f25finalproject.Model.Manager;
import com.example.oop2f25finalproject.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField emailTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    @FXML
    public Button createAccountButton;

    @FXML
    public Label errorMessageLabel;

    // The login model handling authentication
    private final Login login = new Login();

    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
    }

    @FXML
    public void onLoginButtonClick() throws IOException {
        String email = emailTextField.getText().trim();
        String password = passwordTextField.getText();

        // Validate input
        if(email.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Email and Password cannot be empty");
        }

        // Authenticate using the login model
        User loggedInUser = login.authenticate(email, password);

        if (loggedInUser == null) {
            errorMessageLabel.setText("Invalid email or password");
        }

        // Redirect based on the role (Manager or client)
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader;

        if (loggedInUser instanceof Manager) {
            // Manager fxml view to be added
            fxmlLoader = new FXMLLoader(getClass().getResource(""));
        } else {
            fxmlLoader = new FXMLLoader(getClass().getResource("client-view.fxml"));
        }

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void onCreateAccountButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) createAccountButton.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }
}
