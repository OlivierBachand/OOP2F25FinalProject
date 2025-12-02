package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the movie details view.
 * This controller displays detailed information about a selected movie and showtime,
 * including the movie title, genre, showtime date/time, room, and ticket price.
 * It provides a read-only view for clients to review movie information before
 * making a booking decision.
 *
 * @author Shanley Aninzo
 */
public class MovieDetailsController {

    /** Label displaying the movie title */
    @FXML
    private Label aTitleDetailLabel;

    /** Label displaying the movie genre */
    @FXML
    private Label aGenreDetailLabel;

    /** Label displaying the formatted showtime date and time */
    @FXML
    private Label aShowtimeDetailLabel;

    /** Label displaying the room name/number */
    @FXML
    private Label aRoomDetailLabel;

    /** Label displaying the ticket price */
    @FXML
    private Label aTicketPriceDetailLabel;

    /**
     * Populates the movie details view with information from the selected movie,
     * showtime, and ticket.
     * This method is called by the parent controller to pass the selected data
     * and display it in the appropriate labels. The showtime uses the model's
     * default toString() format (dd/MM/yyyy HH:mm) for consistency with other views.
     *
     * @param pMovie the movie to display
     * @param pShowTime the showtime containing date/time and room information
     * @param pTicket the ticket containing pricing information
     */
    public void setMovieDetails(Movie pMovie, ShowTime pShowTime, Ticket pTicket) {
        aTitleDetailLabel.setText(pMovie.getTitle());
        aGenreDetailLabel.setText(pMovie.getaGenre());
        aShowtimeDetailLabel.setText(pShowTime.toString());
        aRoomDetailLabel.setText(pShowTime.getaRoom().getName());
        aTicketPriceDetailLabel.setText(String.format("$%.2f", pTicket.getaPrice()));
    }

    /**
     * Handles the close button click event.
     * Closes the movie details window and returns to the previous view
     *
     * @param actionEvent the action event triggered by the button click
     */
    public void onCloseButtonDetails(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}