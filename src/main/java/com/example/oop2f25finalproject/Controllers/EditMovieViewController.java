package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
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
import java.util.Optional;

/**
 * Controller for the "Edit Movie" view.
 * <p>
 * This class manages the user interface that allows editing a movie's
 * basic information (title, genre, length) as well as managing its
 * associated showtimes (adding, editing, or deleting).
 * </p>
 */
public class EditMovieViewController {

    /** Text field where the movie's title is displayed and edited. */
    @FXML
    private TextField aTitleTextField;

    /** Text field where the movie's genre is displayed and edited. */
    @FXML
    private TextField aGenreTextField;

    /** Text field where the movie's length (HH:mm:ss) is displayed and edited. */
    @FXML
    private TextField aLengthTextField;

    /** ListView showing all showtimes formatted as strings. */
    @FXML
    private ListView<String> aShowTimeListView;

    /** The movie currently being edited. */
    @FXML
    private Movie aMovie;

    /** Observable list used to populate the ListView of showtimes. */
    @FXML
    private ObservableList<String> aShowTimes = FXCollections.observableArrayList();

    /**
     * Closes the edit window without saving any changes.
     * Triggered when the "Cancel" button is clicked.
     */
    @FXML
    private void onCancelButtonClick() {
        ((Stage) this.aTitleTextField.getScene().getWindow()).close();
    }

    /**
     * Saves the changes made to the movie's title, genre, and length.
     * <p>
     * If any field is invalid (e.g., empty title or incorrect length format),
     * an error alert is shown and the update is aborted.
     * </p>
     */
    @FXML
    private void onSaveButtonClick() {
        try {
            this.aMovie.setTitle(this.aTitleTextField.getText());
            this.aMovie.setGenre(this.aGenreTextField.getText());
            this.aMovie.setLength(this.aLengthTextField.getText());
            this.onCancelButtonClick();
        }
        catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * Deletes the currently selected showtime from the movie.
     * <p>
     * If no showtime is selected, the method silently does nothing.
     * </p>
     */
    @FXML
    private void onDeleteButtonClick() {
        int selectedIndex = this.aShowTimeListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this ShowTime?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                this.aMovie.deleteShowTime(selectedIndex);
                this.refreshShowTimes();
            }
        }
        else
            new Alert(Alert.AlertType.ERROR, "No ShowTime selected", ButtonType.OK).showAndWait();
    }

    /**
     * Opens a window allowing the user to edit the selected showtime.
     * <p>
     * If a valid showtime is selected, the corresponding editor window is opened
     * in a modal stage. Once closed, the showtime list is refreshed.
     * </p>
     *
     * @param pEvent the UI event triggered by clicking the Edit button
     * @throws IOException if the FXML file for the edit view cannot be loaded
     */
    @FXML
    private void onEditButtonClick(ActionEvent pEvent) throws IOException {
        int selectedIndex = this.aShowTimeListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("../../../../../../../../../class Backups/edit-show-time-view.fxml"));
            Parent view = fxmlLoader.load();
            EditShowTimeViewController newView = fxmlLoader.getController();
            Scene nextScene = new Scene(view, 475, 475);
            Stage nextStage = new Stage();
            newView.setShowTime(this.aMovie.getShowTime(selectedIndex));
            nextScene.getWindow();
            nextStage.setScene(nextScene);
            nextStage.setTitle("Edit ShowTime");
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
            nextStage.setResizable(false);
            nextStage.showAndWait();
            this.refreshShowTimes();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No ShowTime selected", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Opens the window for adding a new showtime to the movie.
     * <p>
     * After the modal window is closed, the updated showtime list is reloaded.
     * </p>
     *
     * @param pEvent the UI event that triggered the Add button
     * @throws IOException if the FXML resource cannot be loaded
     */
    @FXML
    private void onAddButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("../../../../../../../../../class Backups/add-show-time-view.fxml"));
        Parent view = fxmlLoader.load();
        AddShowTimeViewController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        newView.setMovie(this.aMovie);
        nextStage.setScene(nextScene);
        nextStage.setTitle("New ShowTime");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
        this.refreshShowTimes();
    }

    /**
     * Initializes the controller when the view is loaded.
     * Sets the ListView to use the observable list of showtimes.
     */
    public void initialize() {
        this.aShowTimeListView.setItems(this.aShowTimes);
    }

    /**
     * Assigns the movie to be edited and populates the UI fields
     * with its current information.
     * <p>
     * This method must be called by the parent controller before the
     * edit view is shown.
     * </p>
     *
     * @param pMovie the movie to load into the editor
     * @throws NullPointerException if the movie is null
     */
    public void setMovie(Movie pMovie) {
        if (pMovie == null)
            throw new NullPointerException("Movie cannot be null");
        this.aMovie = pMovie;
        this.aTitleTextField.setText(pMovie.getTitle());
        this.aGenreTextField.setText(pMovie.getaGenre());
        this.aLengthTextField.setText(pMovie.getLength().toString());
        this.refreshShowTimes();
    }

    /**
     * Refreshes the list of showtimes displayed in the UI by rebuilding
     * the observable list using the movie's current showtime data.
     */
    private void refreshShowTimes() {
        this.aShowTimes.clear();
        for (int i = 0; i < this.aMovie.getShowTimesSize(); i++) {
            this.aShowTimes.add(this.aMovie.getShowTime(i).toString());
        }
    }
}
