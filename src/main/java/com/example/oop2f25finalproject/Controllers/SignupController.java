package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Client;
import com.example.oop2f25finalproject.Model.Signup;
import com.example.oop2f25finalproject.Model.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the sign-up page.
 * Handles user input.
 * Redirects to the Client Dashboard.
 *
 * @author Rohina
 */
public class SignupController {

    @FXML
    public Label errorMessageLabel;

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField confirmPasswordField;

    @FXML
    public Button signupButton;

    /**
     * The signup model handling registration logic
     */
    private final Signup aSignup;

    /**
     * Constructor for the controller.
     * Passes in the shared Login instance to the Signup model.
     */
    public SignupController(Login pLogin) {
        this.aSignup = new Signup(pLogin);
    }

    /**
     * Initializes the controller.
     * Clears the error message label.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
    }

    /**
     * Handles the signup button click.
     * Attempts to register a new client.
     * Redirects to the client dashboard.
     */
    @FXML
    public void onSignupButtonClick() {
        String name = nameTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate passwords match
        if (!password.equals(confirmPassword)) {
            errorMessageLabel.setText("Passwords do not match");
            return;
        }

        try {
            // Register the client
            Client newClient = aSignup.registerClient(name, email, password, confirmPassword);

            // Redirect to the Client Dashboard
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) nameTextField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IllegalArgumentException e) {
            // Show validation errors in the errorMessage label
            errorMessageLabel.setText(e.getMessage());
        } catch (IOException e) {
            errorMessageLabel.setText("Failed to load Dashboard. Please refresh the page.");
            System.err.println("IOException while loading Dashboard: " + e.getMessage());
        }
    }
}
