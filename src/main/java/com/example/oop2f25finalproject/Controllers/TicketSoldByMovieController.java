package com.example.oop2f25finalproject.Controllers;


import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.ShowTime;
import com.example.oop2f25finalproject.Model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for displaying ticket sales grouped by movie.
 * This controller populates a ListView with movie titles and their
 * corresponding ticket sales count. Each entry shows the movie title
 * followed by the total number of tickets sold across all showtimes.
 *
 * @author Shanley Aninzo
 */
public class TicketSoldByMovieController {

    /** ListView displaying movie titles and ticket counts */
    @FXML
    private ListView<String> aMovieTicketsSoldListView;

    /** Button to close the window */
    @FXML
    private Button aMovieCloseButton;

    /**
     * Initializes the controller and populates the ListView with ticket sales data.
     * This method is automatically called after the FXML file has been loaded.
     * It iterates through all movies, counts tickets sold for each movie across
     * all its showtimes, and displays the results in the ListView.
     *
     */
    @FXML
    public void initialize() {
        loadTicketSalesData();
    }

    /**
     * Loads and displays ticket sales data grouped by movie.
     * For each movie in the system, this method:
     * Iterates through all showtimes for that movie
     * Counts the total tickets sold across all showtimes
     * Formats the data as "Movie Title\t\tTicket Count"
     * Adds it to the ListView</li>
     *
     */
    private void loadTicketSalesData() {
        ObservableList<String> salesData = FXCollections.observableArrayList();

        // Iterate through all movies
        for (Movie movie : Movie.movieList) {
            int totalTicketsSold = 0;

            // Count tickets across all showtimes for this movie
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);
                totalTicketsSold += showTime.getTickets().size();
            }

            // Format
            String displayText = String.format("%-60s\t\t%d", movie.getTitle(), totalTicketsSold);

            salesData.add(displayText);
        }

        // Set the data to the ListView
        aMovieTicketsSoldListView.setItems(salesData);
    }

    /**
     * Handles the close button click event.
     * Closes the current window and returns to the manager view.
     *
     * @param pEvent the action event triggered by clicking the close button
     */
    @FXML
    private void onMovieCloseButton(ActionEvent pEvent) {
        Stage stage = (Stage) aMovieCloseButton.getScene().getWindow();
        stage.close();
    }
}

