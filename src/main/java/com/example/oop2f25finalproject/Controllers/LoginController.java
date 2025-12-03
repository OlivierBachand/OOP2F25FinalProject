package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Login;
import com.example.oop2f25finalproject.Model.Manager;
import com.example.oop2f25finalproject.Model.User;
import com.example.oop2f25finalproject.MovieTheatreApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the login page.
 * <p>
 * Handles user input, validates credentials, and redirects to the appropriate dashboard
 * based on the user role (Manager or Client), and allows navigation to the sign-up page.
 * </p>
 * Uses a shared login model for authentication and client management.
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
    private Login aLogin;

    /**
     * Default constructor.
     * The shared login instance must be set using {@link #setLogin(Login)}.
     */
    public LoginController() {

    }

    /**
     * Sets the shared Login instance for authentication and registration.
     *
     * @param aLogin The shared Login model
     */
    public void setLogin(Login aLogin) {
        this.aLogin = aLogin;
    }

    /**
     * Initializes the controller.
     * Clears all input fields and the error message label when the scene loads.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
        emailTextField.setText("");
        passwordTextField.setText("");
    }

    /**
     * Handles the login button click event.
     * <p>
     * Validates input fields, authenticates the user using the Login model,
     * and redirects to the appropriate dashboard based on the user role.
     * </p>
     */
    @FXML
    public void onLoginButtonClick() {
        try {

            String email = emailTextField.getText().trim();
            String password = passwordTextField.getText();

            // Authenticate using the login model
            User loggedInUser = aLogin.authenticate(email, password);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader;

            if (loggedInUser instanceof Manager) {
                fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("/com/example/oop2f25finalproject/manager-view.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("/com/example/oop2f25finalproject/client-view.fxml"));
            }

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IllegalArgumentException e) {
            errorMessageLabel.setText(e.getMessage());
        } catch (IOException e) {
            errorMessageLabel.setText("Failed to load the dashboard. Please refresh the page.");
        }
    }

    /**
     * Handles the create account button click.
     * Navigates to the sign-up page, passing the shared login instance
     * to the SignupController.
     */
    @FXML
    public void onCreateAccountButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("/com/example/oop2f25finalproject/signup-view.fxml"));
            Parent root = fxmlLoader.load();

            SignupController signupController = fxmlLoader.getController();
            signupController.setLogin(aLogin);

            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign Up");
            stage.show();

        } catch (IOException e) {
            errorMessageLabel.setText("Failed to load Sign Up page. Please try again.");
        }
    }
}
