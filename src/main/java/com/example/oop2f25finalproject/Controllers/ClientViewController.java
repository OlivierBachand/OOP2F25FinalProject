package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.*;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ClientViewController {
    @FXML
    private ListView<String> aShowingMoviesListView;

    private Client aCurrentClient;
    private ObservableList<String> aShowTimeDisplayList;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yy, h:mm a");

    @FXML void initialize() {
        aShowTimeDisplayList =  FXCollections.observableArrayList();
        aShowingMoviesListView.setItems(aShowTimeDisplayList);

        loadMovies();
    }

    private void loadMovies() {
        aShowTimeDisplayList.clear();

        for (Movie movie : Movie.movieList) {
            if (movie.getaShowTimes() != null) {
                for (ShowTime showtime : movie.getaShowTimes()) {

                    String display = String.format("%s - %s",
                            movie.getTitle(),
                            showtime.getaDateTime().format(FORMATTER));
                    aShowTimeDisplayList.add(display);
                }
            }
        }
    }

    public void setCurrentClient(Client pCurrentClient) {
        this.aCurrentClient = pCurrentClient;
    }

    // public void onSortNameButton(ActionEvent actionEvent) {
    //     FXCollections.sort(aShowTimeDisplayList);
    // }

    public void onSortShowtimeButton(ActionEvent actionEvent) {
        FXCollections.sort(aShowTimeDisplayList);
    }

    public void onViewDetailsButton(ActionEvent actionEvent) {
        String selected = aShowingMoviesListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a movie");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Movie Details");
        alert.setContentText("Selected: " + selected);
        alert.showAndWait();
    }

    public void onClientLogOutButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/oop2f25finalproject/login-view.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
