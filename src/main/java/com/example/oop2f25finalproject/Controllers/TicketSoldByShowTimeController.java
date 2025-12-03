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

import java.time.format.DateTimeFormatter; // Required Import

/**
 * Controller for displaying ticket sales grouped by showtime.
 * Displays only the date/time and the tickets sold for each showtime.
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
     */
    @FXML
    public void initialize() {
        loadTicketSalesData();
    }

    /**
     * Loads and displays ticket sales data grouped by showtime.
     */
    private void loadTicketSalesData() {
        ObservableList<String> salesData = FXCollections.observableArrayList();

        // Define the formatter ONCE outside the loop for efficiency
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        // Iterate through all movies
        for (Movie movie : Movie.movieList) {
            // Iterate through all showtimes for each movie
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);
                int ticketsSold = showTime.getTickets().size();

                // Format the date/time using the reusable formatter object
                String dateTimeOnly = showTime.getaDateTime().format(formatter);

                // Format the output string
                String displayText = String.format("%-60s\t\t%d", dateTimeOnly, ticketsSold);

                salesData.add(displayText);
            }
        }

        aShowtimeTicketsSoldListView.setItems(salesData);
    }

    /**
     * Handles the close button click event.
     * Closes the current window and returns to the manager view.
     */
    @FXML
    private void onShowtimeCloseButton(ActionEvent pEvent) {
        Stage stage = (Stage) aShowtimeCloseButton.getScene().getWindow();
        stage.close();
    }
}