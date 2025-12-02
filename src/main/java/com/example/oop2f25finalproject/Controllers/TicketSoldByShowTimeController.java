package com.example.oop2f25finalproject.Controllers;


import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller for displaying ticket sales grouped by showtime.
 * This controller populates a ListView with showtime information and their
 * corresponding ticket sales count. Each entry shows the movie title,
 * showtime details, followed by the number of tickets sold.
 *
 * @author Shanley Aninzo
 */
public class TicketSoldByShowTimeController {

    /** ListView displaying showtime details and ticket counts */
    @FXML
    private ListView<String> aShowtimeTicketsSoldListView;

    /** Button to close the window */
    @FXML
    private Button aShowtimeCloseButton;

    /**
     * Initializes the controller and populates the ListView with ticket sales data.
     * This method is automatically called after the FXML file has been loaded.
     * It iterates through all movies and their showtimes, displaying each
     * showtime with its ticket sales count.
     *
     */
    @FXML
    public void initialize() {
        loadTicketSalesData();
    }

    /**
     * Loads and displays ticket sales data grouped by showtime.
     * For each movie in the system, this method:
     * Iterates through all showtimes for that movie
     * Counts the tickets sold for each specific showtime
     * Formats the data including movie title and showtime details
     * Adds it to the ListView
     *
     */
    private void loadTicketSalesData() {
        ObservableList<String> salesData = FXCollections.observableArrayList();

        // Iterate through all movies
        for (Movie movie : Movie.movieList) {
            // Iterate through all showtimes for each movie
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);
                int ticketsSold = showTime.getTickets().size();

                // Format
                String displayText = String.format("%s - %s\t\t%d", movie.getTitle(), showTime.toString(), ticketsSold);
                salesData.add(displayText);
            }
        }

        // Set the data to the ListView
        aShowtimeTicketsSoldListView.setItems(salesData);
    }

    /**
     * Handles the close button click event.
     * Closes the current window and returns to the manager view.
     *
     * @param pEvent the action event triggered by clicking the close button
     */
    @FXML
    private void onShowtimeCloseButton(ActionEvent pEvent) {
        Stage stage = (Stage) aShowtimeCloseButton.getScene().getWindow();
        stage.close();
    }
}