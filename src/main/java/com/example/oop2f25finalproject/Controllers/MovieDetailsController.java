package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class MovieDetailsController {
    @FXML
    private Label aTitleDetailLabel;

    @FXML
    private Label aGenreDetailLabel;

    @FXML
    private Label aShowtimeDetailLabel;

    @FXML
    private Label aRoomDetailLabel;

    @FXML
    private Label aTicketPriceDetailLabel;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("M/d/yy, h:mm a");

    public void setMovieDetails(Movie pMovie, ShowTime pShowTime, Ticket pTicket) {
        aTitleDetailLabel.setText(pMovie.getTitle());
        aGenreDetailLabel.setText(pMovie.getaGenre());
        aShowtimeDetailLabel.setText(pShowTime.getaDateTime().format(FORMATTER));
        aRoomDetailLabel.setText(pShowTime.getaRoom().getName());
        aTicketPriceDetailLabel.setText(String.format("$%.2f", pTicket.getaPrice()));
    }

    public void onCloseButtonDetails(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
