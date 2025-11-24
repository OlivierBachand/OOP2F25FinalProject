package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Client;
import com.example.oop2f25finalproject.Model.Signup;
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
    public Button signupButton;

    /**
     * The signup model handling registration logic
     */
    private final Signup aSignup;

    /**
     * Constructor for the controller.
     * Passes in the shared Login instance to the Signup model.
     */
    public SignupController() {
        // check the login instance used in loginController. it should be the same
        this.aSignup = new Signup(Login.getInstance());
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
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();

        try {
            // Register the client
            Client newClient = aSignup.registerClient(name, email, password);

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
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load Dashboard");
        }
    }
}
