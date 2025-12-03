package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.ShowTime;
import com.example.oop2f25finalproject.MovieTheatreApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the "Add Movie" view.
 *
 * <p>
 * This controller manages the creation of a new {@link Movie}, including
 * user-entered details such as the movie’s title, genre, length, and
 * associated {@link ShowTime} objects.
 * </p>
 *
 * <p>
 * Users may add, edit, or delete showtimes before finalizing the creation of
 * the movie. All showtimes remain stored in a temporary list until the movie
 * is successfully created. If the user cancels the process, no data is saved.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * </p>
 * <ul>
 *     <li>Collecting and validating basic movie information</li>
 *     <li>Managing temporary showtime data</li>
 *     <li>Opening modal windows for adding/editing showtimes</li>
 *     <li>Providing feedback through alerts for errors or confirmations</li>
 * </ul>
 */
public class AddMovieViewController {

    /** Text field used to enter the movie’s title. */
    @FXML
    private TextField aTitleTextField;

    /** Text field used to enter the movie’s genre. */
    @FXML
    private TextField aGenreTextField;

    /**
     * Text field used to enter the movie’s length.
     * Expected format: HH:mm:ss.
     */
    @FXML
    private TextField aLengthTextField;

    /**
     * ListView displaying all added showtimes in string format.
     */
    @FXML
    private ListView<String> aShowTimeListView;

    /**
     * Observable list of formatted showtime strings.
     * Backed by {@link #aShowTimesList}.
     */
    @FXML
    private ObservableList<String> aShowTimes = FXCollections.observableArrayList();

    /**
     * Internal list containing the actual {@link ShowTime} objects.
     * Modified when adding, editing, or deleting showtimes.
     */
    private final List<ShowTime> aShowTimesList = new ArrayList<>();

    /** Reference to the Edit button, used for double-click editing. */
    @FXML
    private Button aEditButton;

    /**
     * Closes the "Add Movie" window without saving any entered data.
     * Triggered when the Cancel button is clicked.
     */
    @FXML
    private void onCancelButtonClick() {
        ((Stage) this.aTitleTextField.getScene().getWindow()).close();
    }

    /**
     * Attempts to create a new {@link Movie} using the entered data and collected showtimes.
     *
     * <p>
     * If validation fails (for example, invalid length format), an appropriate
     * error alert is shown. On successful creation, the window closes.
     * </p>
     */
    @FXML
    private void onAddMovieButtonClick() {
        try {
            Movie newMovie = new Movie(
                    this.aTitleTextField.getText(),
                    this.aGenreTextField.getText(),
                    this.aLengthTextField.getText()
            );

            for (ShowTime showTime : aShowTimesList) {
                newMovie.addShowTime(showTime);
            }

            this.onCancelButtonClick();
        }
        catch (Exception e) {
            if (e instanceof ParseException)
                new Alert(Alert.AlertType.ERROR, "Invalid Length", ButtonType.OK).showAndWait();
            else
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * Deletes the currently selected showtime from the temporary list.
     *
     * <p>
     * A confirmation dialog is shown before deletion. If no showtime is selected,
     * an error alert is displayed.
     * </p>
     */
    @FXML
    private void onDeleteButtonClick() {
        int selectedIndex = this.aShowTimeListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to delete this ShowTime?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                this.aShowTimesList.remove(selectedIndex);
                this.refreshShowTimes();
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No ShowTime selected", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Opens a modal window for editing the selected {@link ShowTime}.
     *
     * <p>
     * The selected showtime is passed to the edit controller. After the modal window closes,
     * the displayed showtime list is refreshed.
     * </p>
     *
     * @param pEvent the UI event triggering this action
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onEditButtonClick(ActionEvent pEvent) throws IOException {
        int selectedIndex = this.aShowTimeListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("edit-show-time-view.fxml"));
            Parent view = fxmlLoader.load();
            EditShowTimeViewController newView = fxmlLoader.getController();

            Scene nextScene = new Scene(view, 286, 266);
            Stage nextStage = new Stage();

            newView.setShowTime(this.aShowTimesList.get(selectedIndex));

            nextStage.setScene(nextScene);
            nextStage.setTitle("Edit ShowTime");
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
            nextStage.setResizable(false);
            nextStage.showAndWait();

            this.refreshShowTimes();
        }
    }

    /**
     * Opens a modal window for creating a new {@link ShowTime}.
     *
     * <p>
     * After the modal window closes, the new showtime is added to the internal list,
     * and the ListView is refreshed.
     * </p>
     *
     * @param pEvent the UI event triggering this action
     * @throws IOException if the FXML resource cannot be loaded
     */
    @FXML
    private void onAddShowTimeButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("add-show-time-view.fxml"));
        Parent view = fxmlLoader.load();
        AddShowTimeViewController newView = fxmlLoader.getController();

        Scene nextScene = new Scene(view, 286, 266);
        Stage nextStage = new Stage();

        newView.setShowTimesList(this.aShowTimesList);

        nextStage.setScene(nextScene);
        nextStage.setTitle("New ShowTime");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();

        this.refreshShowTimes();
    }

    /**
     * Initializes the controller after the FXML is loaded.
     *
     * <p>
     * This method:
     * </p>
     * <ul>
     *     <li>Binds the ListView to the observable showtime list</li>
     *     <li>Enables double-click editing by firing the Edit button</li>
     * </ul>
     */
    public void initialize() {
        aShowTimeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                aEditButton.fire();
            }
        });
        this.aShowTimeListView.setItems(this.aShowTimes);
    }

    /**
     * Refreshes the ListView to reflect the current list of showtimes.
     *
     * <p>
     * Clears and repopulates the observable list using the string
     * representation of each {@link ShowTime}.
     * </p>
     */
    private void refreshShowTimes() {
        this.aShowTimes.clear();
        for (ShowTime showTime : this.aShowTimesList) {
            this.aShowTimes.add(showTime.toString());
        }
    }
}
