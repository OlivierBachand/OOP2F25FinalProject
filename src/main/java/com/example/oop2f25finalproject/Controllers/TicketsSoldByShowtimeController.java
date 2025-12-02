package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.Model.ShowTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class TicketsSoldByShowtimeController {
    @FXML
    private ListView<String> aShowtimeTicketSoldListView;

    private ObservableList<String> aShowtimeTicketSalesList;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("M/d/yy, h:mm a");

    @FXML
    public void initialize(){
        aShowtimeTicketSalesList = FXCollections.observableArrayList();
        aShowtimeTicketSoldListView.setItems(aShowtimeTicketSalesList);

        loadTicketSales();
    }

    private void loadTicketSales() {
        aShowtimeTicketSalesList.clear();

        for (Movie movie : Movie.movieList) {
            if (movie.getaShowTimes() != null) {
                for (ShowTime showTime : movie.getaShowTimes()) {
                    int ticketCount = showTime.getTickets().size();

                    String formattedTime = showTime.getaDateTime().format(FORMATTER);
                    String display = String.format("%s - %d tickets", formattedTime, ticketCount);

                    aShowtimeTicketSalesList.add(display);
                }
            }
        }
    }

    public void onShowtimeCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
