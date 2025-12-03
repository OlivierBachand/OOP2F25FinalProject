package com.example.oop2f25finalproject;

import com.example.oop2f25finalproject.Controllers.LoginController;
import com.example.oop2f25finalproject.Helper.TestDataInitializer;
import com.example.oop2f25finalproject.Model.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieTheatreApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Initialize helper
        TestDataInitializer.initializeTestData();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();

        Scene scene = new Scene(root,330, 460);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}