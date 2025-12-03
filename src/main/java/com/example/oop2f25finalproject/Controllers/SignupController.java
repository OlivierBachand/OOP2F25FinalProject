package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Client;
import com.example.oop2f25finalproject.Model.Signup;
import com.example.oop2f25finalproject.Model.Login;
import com.example.oop2f25finalproject.MovieTheatreApplication;
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
 * <p>
 * Handles user input, passes it to the Signup model
 * for validation and registration, and
 * redirects to the Client Dashboard on successful registration.
 * </p>
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
     * The shared Login model instance for storing clients.
     */
    private Login aLogin;

    /**
     * Setter for injecting the shared Login instance.
     *
     * @param aLogin The shared Login instance
     */
    public void setLogin(Login aLogin) {
        this.aLogin = aLogin;
    }

    /**
     * Initializes the controller.
     * Clears all input fields and the error message label.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
        nameTextField.setText("");
        emailTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
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

        try {
            // Use the Signup model for registration and validation
            Signup signup = new Signup(aLogin);
            Client newClient = signup.registerClient(name, email, password, confirmPassword);

            // Redirect to the Client Dashboard
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("/com/example/oop2f25finalproject/client-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) signupButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IllegalArgumentException e) {
            // Display validation errors from the Signup model in the errorMessage label
            errorMessageLabel.setText(e.getMessage());
        } catch (IOException e) {
            errorMessageLabel.setText("Failed to load Dashboard. Please refresh the page.");
        }
    }
}
