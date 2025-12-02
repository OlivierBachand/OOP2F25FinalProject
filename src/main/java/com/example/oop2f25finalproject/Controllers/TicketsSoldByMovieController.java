package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.*;


public class TicketsSoldByMovieController {
    @FXML
    private ListView<String> aMovieTicketSoldListView;

    private ObservableList<String> aMovieTSalesList;

    @FXML
    public void initialize(){
        aMovieTSalesList = FXCollections.observableArrayList();
        aMovieTicketSoldListView.setItems(aMovieTSalesList);

        loadTicketSales();
    }

    private void loadTicketSales() {
        aMovieTSalesList.clear();

        for (Movie movie : Movie.movieList) {
            int totalTickets = 0;

            if (movie.getaShowTimes() != null) {
                for (ShowTime showTime : movie.getaShowTimes()) {
                    totalTickets += showTime.getTickets().size();
                }
            }

            String display = String.format("%s - %d tickets", movie.getTitle(), totalTickets);
            aMovieTSalesList.add(display);
        }
    }

    public void onMovieCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
