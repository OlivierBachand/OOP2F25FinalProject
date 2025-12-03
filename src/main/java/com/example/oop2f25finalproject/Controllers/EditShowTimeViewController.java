package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Room;
import com.example.oop2f25finalproject.Model.ShowTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class EditShowTimeViewController {

    /** DatePicker used to select the showtime's calendar date. */
    @FXML
    private DatePicker aDatePicker;

    /** Text field for entering the time in HH:mm format. */
    @FXML
    private TextField aTimeTextField;

    /** Dropdown menu listing all available rooms. */
    @FXML
    private ComboBox<String> aRoomComboBox;

    private ShowTime aCurrentShowTime;

    /**
     * Initializes the view by populating the room combo box with all registered rooms.
     */
    public void initialize() {
        if (!Room.roomList.isEmpty()) {
            for (int i = 0; i < Room.roomList.size(); i++) {
                aRoomComboBox.getItems().add("Room " + Room.roomList.get(i).getName());
            }
        }
    }

    /**
     * Handles the Save button click.
     * <p>
     * Changes the current showtime using the selected room, chosen date,
     * and entered time.
     * If the input is invalid (missing room, date, or time), an error message is shown.
     * </p>
     */
    @FXML
    private void onSaveButtonClick() {
        try {
            int selectedIndex = aRoomComboBox.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                LocalDate date = aDatePicker.getValue();
                LocalTime time = LocalTime.parse(
                        aTimeTextField.getText(),
                        DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
                );

                LocalDateTime dateTime = LocalDateTime.of(date, time);
                aCurrentShowTime.setDateTime(dateTime);
                this.onCancelButtonClick();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "No room selected", ButtonType.OK).show();
            }
        }
        catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * Closes the Edit ShowTime window without saving.
     */
    @FXML
    private void onCancelButtonClick() {
        ((Stage) aDatePicker.getScene().getWindow()).close();
    }

    /**
     * Assigns an existing showtime to the current showtime variable.
     * Sets fields as with the showtime's attributes.
     *
     * @param pShowTime the showtime being edited
     */
    public void setShowTime(ShowTime pShowTime) {
        this.aCurrentShowTime = pShowTime;
        this.aDatePicker.setValue((pShowTime.getaDateTime().toLocalDate()));
        this.aTimeTextField.setText(pShowTime.getaDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)));
        this.aRoomComboBox.getSelectionModel().select(Room.roomList.indexOf(this.aCurrentShowTime.getaRoom()));
    }
}
