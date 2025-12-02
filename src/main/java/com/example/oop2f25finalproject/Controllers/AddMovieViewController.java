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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for creating a new {@link Movie} and managing its associated data.
 * <p>
 * This view allows the user to input basic movie information (title, genre, and length),
 * as well as add, edit, or remove showtimes before finalizing and saving the movie.
 * </p>
 */
public class AddMovieViewController {

    /** Text field used to enter the movie's title. */
    @FXML
    private TextField aTitleTextField;

    /** Text field used to enter the movie's genre. */
    @FXML
    private TextField aGenreTextField;

    /** Text field used to enter the movie's length in HH:mm:ss format. */
    @FXML
    private TextField aLengthTextField;

    /** ListView that displays all added showtimes as formatted strings. */
    @FXML
    private ListView<String> aShowTimeListView;

    /** Observable list containing string representations of the showtimes for display. */
    @FXML
    private ObservableList<String> aShowTimes = FXCollections.observableArrayList();

    /** Temporary list holding the ShowTime objects before the movie is officially created. */
    private final List<ShowTime> aShowTimesList = new ArrayList<>();

    /**
     * Closes the "Add Movie" window without saving changes.
     */
    @FXML
    private void onCancelButtonClick() {
        ((Stage) this.aTitleTextField.getScene().getWindow()).close();
    }

    /**
     * Creates a new {@link Movie} object using the input fields and the collected showtimes.
     * <p>
     * If the provided information is invalid, an error alert is shown.
     * On success, the window is closed.
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
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * Deletes the currently selected showtime from the temporary list.
     * <p>
     * Prompts the user for confirmation before removing the showtime.
     * If no showtime is selected, an error alert is displayed.
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
     * Opens the "Edit ShowTime" window for the selected showtime.
     * <p>
     * The selected ShowTime is passed to the edit controller. After the modal window is closed,
     * the displayed list is refreshed. If no showtime is selected, an error alert is shown.
     * </p>
     *
     * @param pEvent the UI event originating from clicking the Edit button
     * @throws IOException if the FXML resource cannot be loaded
     */
    @FXML
    private void onEditButtonClick(ActionEvent pEvent) throws IOException {
        int selectedIndex = this.aShowTimeListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("edit-show-time-view.fxml"));
            Parent view = fxmlLoader.load();
            EditShowTimeViewController newView = fxmlLoader.getController();
            Scene nextScene = new Scene(view, 475, 475);
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
        else {
            new Alert(Alert.AlertType.ERROR, "No movie selected", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Opens the "Add ShowTime" window, allowing the user to create a new ShowTime.
     * <p>
     * The newly created showtime is added to the internal temporary list.
     * After the modal window closes, the ShowTime ListView is refreshed.
     * </p>
     *
     * @param pEvent the user action that triggered this method
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onAddShowTimeButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("add-show-time-view.fxml"));
        Parent view = fxmlLoader.load();
        AddShowTimeViewController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
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
     * Initializes the view by connecting the ListView to the observable list
     * containing the formatted showtime strings.
     */
    public void initialize() {
        this.aShowTimeListView.setItems(this.aShowTimes);
    }

    /**
     * Refreshes the displayed list of showtimes.
     * <p>
     * This method clears and repopulates the observable list based on the
     * internal list of ShowTime objects.
     * </p>
     */
    private void refreshShowTimes() {
        this.aShowTimes.clear();
        for (ShowTime showTime : this.aShowTimesList) {
            this.aShowTimes.add(showTime.toString());
        }
    }
}
