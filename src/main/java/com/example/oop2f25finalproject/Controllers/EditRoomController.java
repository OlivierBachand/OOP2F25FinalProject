package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.AddRoom;
import com.example.oop2f25finalproject.Model.EditRoom;
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
     * Model for editing rooms.
     */
    private final EditRoom aEditRoomModel;

    /**
     * The Room being edited.
     */
    private final Room aRoom;

    /**
     * Constructor receiving the model and the room to edit.
     *
     * @param pEditRoomModel The EditRoom model
     * @param pRoom         The Room to edit
     */
    public EditRoomController(EditRoom pEditRoomModel, Room pRoom) {
        this.aEditRoomModel = pEditRoomModel;
        this.aRoom = pRoom;
    }

    /**
     * Initializes the controller.
     * Pre-fills the text fields with the room's current values.
     */
    @FXML
    public void initialize() {
        errorMessageLabel.setText("");
        roomNameTextField.setText(aRoom.getName());
        roomCapacityTextField.setText(String.valueOf(aRoom.getCapacity()));
    }

    /**
     * Handles save button click.
     * Attempts to update the room and close the window if successful.
     *
     * @param pEvent The action event
     */
    @FXML
    public void onSaveButtonClick(ActionEvent pEvent) {
        String newName = roomNameTextField.getText().trim();
        String newCapacity = roomCapacityTextField.getText().trim();

        try {
            // Validate using AddRoom model
            aEditRoomModel.editRoom(aRoom, newName, newCapacity);

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
     * @param pEvent The action event
     */
    @FXML
    public void onCancelButtonClick(ActionEvent pEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
