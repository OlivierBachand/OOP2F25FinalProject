package com.example.oop2f25finalproject;

import com.example.oop2f25finalproject.Model.Room;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieTheatreApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("movie-management-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 475, 475);
        stage.setTitle("Login");
        stage.setScene(scene);
        new Room(100, "one");
        new Room(100, "two");
        new Room(100, "three");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}