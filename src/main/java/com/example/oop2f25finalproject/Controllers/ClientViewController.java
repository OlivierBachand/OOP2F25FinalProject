package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.ShowTime;
import com.example.oop2f25finalproject.Model.Ticket;
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
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the client's main view.
 * This controller displays all available movies and their showtimes,
 * allows sorting by movie name (alphabetical) or by showtime (chronological),
 * and provides navigation to view movie details and logout functionality.
 *
 * @author Shanley Aninzo
 */
public class ClientViewController {

    /** ListView displaying movies and their showtimes */
    @FXML
    private ListView<String> aShowingMoviesListView;

    /** Flag to track current sort mode (true = alphabetical, false = chronological) */
    private boolean isSortedByName = true;

    /**
     * Initializes the controller and populates the ListView with movie data.
     * This method is automatically called after the FXML file has been loaded.
     * It loads all movies and showtimes and displays them sorted alphabetically by default.
     */
    @FXML
    public void initialize() {
        loadMoviesByName();
    }

    /**
     * Loads and displays movies sorted alphabetically by title.
     */
    private void loadMoviesByName() {
        ObservableList<String> displayList = FXCollections.observableArrayList();

        for (Movie movie : Movie.movieList) {
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);
                String displayText = String.format("%s\t\t%s", movie.getTitle(), showTime.toString());
                displayList.add(displayText);
            }
        }

        FXCollections.sort(displayList);
        aShowingMoviesListView.setItems(displayList);
    }

    /**
     * Loads and displays movies sorted chronologically by showtime.
     */
    private void loadMoviesByShowtime() {
        ObservableList<String> displayList = FXCollections.observableArrayList();

        for (Movie movie : Movie.movieList) {
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);
                // Put datetime first for chronological sorting, then format for display
                String sortKey = showTime.getaDateTime().toString();
                String displayText = String.format("%s\t\t%s", movie.getTitle(), showTime.toString());
                displayList.add(sortKey + "|" + displayText);
            }
        }

        // Sort by the datetime prefix
        FXCollections.sort(displayList);

        // Remove the sort key prefix for display
        ObservableList<String> finalList = FXCollections.observableArrayList();
        for (String item : displayList) {
            String[] parts = item.split("\\|", 2);
            if (parts.length == 2) {
                finalList.add(parts[1]);
            }
        }

        aShowingMoviesListView.setItems(finalList);
    }

    /**
     * Handles the "Sort by Name" button click.
     * Sorts the movies alphabetically by title.
     *
     * @param pEvent the action event triggered by the button click
     */
    @FXML
    private void onSortNameButton(ActionEvent pEvent) {
        if (!isSortedByName) {
            loadMoviesByName();
            isSortedByName = true;
        }
    }

    /**
     * Handles the "Sort by Showtime" button click.
     * Sorts all movie showtimes chronologically by date and time.
     *
     * @param pEvent the action event triggered by the button click
     */
    @FXML
    private void onSortShowtimeButton(ActionEvent pEvent) {
        if (isSortedByName) {
            loadMoviesByShowtime();
            isSortedByName = false;
        }
    }

    /**
     * Handles the "View Details" button click.
     * Opens a modal window showing details for the selected movie and showtime.
     * If no item is selected, displays an alert message.
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onViewDetailsButton(ActionEvent pEvent) throws IOException {
        String selectedItem = aShowingMoviesListView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a movie and showtime to view details.");
            alert.showAndWait();
            return;
        }

        // Parse the selected item to find the movie and showtime
        String[] parts = selectedItem.split("\t\t");
        if (parts.length < 2) {
            return;
        }

        String movieTitle = parts[0].trim();
        String showtimeInfo = parts[1].trim();

        // Find the matching movie and showtime
        Movie selectedMovie = null;
        ShowTime selectedShowtime = null;

        for (Movie movie : Movie.movieList) {
            if (movie.getTitle().equals(movieTitle)) {
                for (int i = 0; i < movie.getShowTimesSize(); i++) {
                    ShowTime showTime = movie.getShowTime(i);
                    if (showTime.toString().equals(showtimeInfo)) {
                        selectedMovie = movie;
                        selectedShowtime = showTime;
                        break;
                    }
                }
                if (selectedShowtime != null) {
                    break;
                }
            }
        }

        if (selectedMovie == null || selectedShowtime == null) {
            return;
        }

        // Load the movie details view
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("movie-details-view.fxml"));
        Parent view = fxmlLoader.load();

        // Get the controller and pass the selected data
        MovieDetailsController controller = fxmlLoader.getController();
        controller.setMovieDetails(selectedMovie, selectedShowtime);

        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Movie Details");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();

        // Refresh the list after closing details window
        if (isSortedByName) {
            loadMoviesByName();
        } else {
            loadMoviesByShowtime();
        }
    }

    /**
     * Handles the logout button click.
     * Returns the user to the login screen and closes the client view window.
     *
     * @param pEvent the action event triggered by the logout button
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onClientLogOutButton(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("login-view.fxml"));
        Parent view = fxmlLoader.load();

        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Login");
        nextStage.show();

        // Close the current client view window
        Stage currentStage = (Stage) ((Node) pEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}