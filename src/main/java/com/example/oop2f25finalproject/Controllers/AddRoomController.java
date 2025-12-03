package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Add Room view.
 * Handles user input for creating a new Room.
 *
 * @author Rohina
 */
public class AddRoomController {

    @FXML
    public Label messageLabel;

    @FXML
    public TextField roomNameTextField;

    @FXML
    public TextField roomCapacityTextField;

    @FXML
    public Button saveButton;

    @FXML
    public Button cancelButton;

    /**
     * Initializes the controller.
     * Clears messageLabel text.
     */
    @FXML
    public void initialize() {
        messageLabel.setText("");
    }

    /**
     * Handles save button click event.
     * Attempts to create a new room using the AddRoom model.
     *
     * @param pEvent The action triggered by clicking save button
     */
    @FXML
    public void onSaveButtonClick(ActionEvent pEvent) {
        String name = roomNameTextField.getText().trim();
        String capacity = roomCapacityTextField.getText().trim();

        try {
            new Room(capacity, name);

            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Room added successfully!");

            // Clear input fields after successfully adding room
            roomNameTextField.clear();
            roomCapacityTextField.clear();

        } catch (IllegalArgumentException e) {
            messageLabel.setText(e.getMessage());
        }
    }

    /**
     * Handles cancel button click event.
     * Closes the current window.
     *
     * @param pEvent The action triggered by clicking cancel button
     */
    @FXML
    public void onCancelButtonClick(ActionEvent pEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
