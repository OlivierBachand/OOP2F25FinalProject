package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for editing an existing Room.
 * Uses EditRoom model for validation and updating.
 *
 * @author Rohina
 */
public class EditRoomController {

    @FXML
    public Label errorMessageLabel;

    @FXML
    public TextField roomNoTextField;

    @FXML
    public TextField roomNameTextField;

    @FXML
    public TextField roomCapacityTextField;

    @FXML
    public Button saveButton;

    @FXML
    public Button cancelButton;

    /**
     * The room being edited.
     */
    private Room aRoomToEdit;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
    }

    /**
     * Passes the room to edit from the previous view.
     * Pre-fills the text fields with the room's current values.
     *
     * @param pRoomToEdit Room object to edit
     */
    public void setRoomToEdit(Room pRoomToEdit) {
        this.aRoomToEdit = pRoomToEdit;
        roomNameTextField.setText(pRoomToEdit.getName());
        roomCapacityTextField.setText(String.valueOf(pRoomToEdit.getCapacity()));
    }

    /**
     * Handles save button click.
     * Updates the room and close the window if successful.
     *
     * @param pEvent The action event triggered by save button
     */
    @FXML
    public void onSaveButtonClick(ActionEvent pEvent) {
        String newName = roomNameTextField.getText().trim();
        String newCapacity = roomCapacityTextField.getText().trim();

        try {
            // Update room name if changed
            if (!newName.equalsIgnoreCase(aRoomToEdit.getName())) {
                aRoomToEdit.setName(newName);
            }

            // Update capacity if changed
            if (!newCapacity.equals(String.valueOf(aRoomToEdit.getCapacity()))) {
                aRoomToEdit.setCapacity(newCapacity);
            }

            // Close the window upon successful edit
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        } catch (IllegalArgumentException e) {
            errorMessageLabel.setText(e.getMessage());
        }
    }

    /**
     * Handles cancel button click.
     * Closes the window without saving.
     *
     * @param pEvent The action event triggered by cancel button
     */
    @FXML
    public void onCancelButtonClick(ActionEvent pEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
