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

/**
 * Controller for the login page.
 * Handles user input, validates credentials, and redirects to the appropriate dashboard
 * based on the user role (Manager or Client).
 * It Also allows navigation to the sign-up page.
 *
 * It uses a shared login model for authentication and client management.
 *
 * @author Rohina
 */
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

    /**
     * The shared login model used for authentication
     */
    private final Login aLogin;

    /**
     * Constructor that receives the shared login instance.
     *
     * @param pLogin The shared login model
     */
    public LoginController(Login pLogin) {
        this.aLogin = pLogin;
    }

    /**
     * Initializes the controller.
     * Clears the error message label when the scene loads.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
    }

    /**
     * Handles the login button click event.
     *
     * Reads and trims user input.
     * Validates input is not empty.
     * Authenticates user using the login model
     * Redirects to the appropriate dashboard based on the user role.
     *
     * @throws IOException If the FXML for the dashboard cannot be loaded
     */
    @FXML
    public void onLoginButtonClick() throws IOException {
        String email = emailTextField.getText().trim();
        String password = passwordTextField.getText();

        // Validate input
        if(email.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("Email and Password cannot be empty");
        }

        // Authenticate using the login model
        User loggedInUser = aLogin.authenticate(email, password);

        if (loggedInUser == null) {
            errorMessageLabel.setText("Invalid email or password");
        }

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load Dashboard");
        }
    }

    /**
     * Handles the create account button clilck.
     * Navigates to the sign-up page, passing the shared login instance to the SignupController.
     *
     * @throws IOException If the sign-up FXML cannot be loaded
     */
    @FXML
    public void onCreateAccountButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        fxmlLoader.setController(new SignupController(aLogin));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) createAccountButton.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }
}
