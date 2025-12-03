package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.Room;
import com.example.oop2f25finalproject.Model.ShowTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Controller for the "Add ShowTime" view.
 * <p>
 * This class allows the user to create a new {@link ShowTime} by selecting
 * a date, entering a time, and choosing a room. The generated ShowTime
 * can either be added directly to an existing movie or stored temporarily
 * in a shared list used during movie creation.
 * </p>
 */
public class AddShowTimeViewController {

    /** DatePicker used to select the showtime's calendar date. */
    @FXML
    private DatePicker aDatePicker;

    /** Text field for entering the time in HH:mm format. */
    @FXML
    private TextField aTimeTextField;

    /** Dropdown menu listing all available rooms. */
    @FXML
    private ComboBox<String> aRoomComboBox;

    /** Reference to the list used to temporarily store newly created showtimes. */
    private List<ShowTime> aShowTimesList;

    /** Optional reference to a Movie, if directly adding showtimes to an existing movie. */
    private Movie aCurrentMovie;

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
     * Handles the Add button click.
     * <p>
     * Creates a new {@link ShowTime} using the selected room, chosen date,
     * and entered time. The resulting showtime is then added either to:
     * <ul>
     *     <li>the currently edited Movie, or</li>
     *     <li>the temporary list used during the Add Movie workflow</li>
     * </ul>
     * If the input is invalid (missing room, date, or time), an error message is shown.
     * </p>
     */
    @FXML
    private void onAddButtonClick() {
        try {
            int selectedIndex = aRoomComboBox.getSelectionModel().getSelectedIndex();

            if (selectedIndex != -1) {
                ShowTime newShowTime = new ShowTime(null, Room.roomList.get(selectedIndex));

                LocalDate date = aDatePicker.getValue();
                LocalTime time = LocalTime.parse(
                        aTimeTextField.getText(),
                        DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
                );

                LocalDateTime dateTime = LocalDateTime.of(date, time);
                newShowTime.setDateTime(dateTime);
                newShowTime.setRoom(Room.roomList.get(selectedIndex));

                if (aCurrentMovie != null) {
                    aCurrentMovie.addShowTime(newShowTime);
                }
                else {
                    aShowTimesList.add(newShowTime);
                }
                this.onCancelButtonClick();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "No room selected", ButtonType.OK).show();
            }
        }
        catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid date and/or time.", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Closes the Add ShowTime window without saving.
     */
    @FXML
    private void onCancelButtonClick() {
        ((Stage) aDatePicker.getScene().getWindow()).close();
    }

    /**
     * Assigns an existing movie to receive new showtimes.
     *
     * @param pMovie the movie being edited
     */
    public void setMovie(Movie pMovie) {
        this.aCurrentMovie = pMovie;
    }

    /**
     * Sets the temporary list used when adding showtimes during the movie creation workflow.
     *
     * @param pShowTimesList list where newly created showtimes will be stored
     */
    public void setShowTimesList(List<ShowTime> pShowTimesList) {
        this.aShowTimesList = pShowTimesList;
    }
}
