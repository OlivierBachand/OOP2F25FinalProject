package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.MovieTheatreApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the manager's main view.
 * <p>
 * This controller provides navigation to all management-related windows,
 * including movie management, room management, ticket sales reports,
 * and system actions such as logout or closing the application.
 * </p>
 *
 * <p>
 * Each button click loads a new modal window with its corresponding FXML view
 * and controller, preventing interaction with the previous window until the
 * modal window is closed.
 * </p>
 *
 * @author Olivier Bachand
 */
public class ManagerViewController {

    /** Button responsible for logging out the current user. */
    @FXML
    private Button aLogOutButton;

    /**
     * Opens the movie management window.
     * <p>This loads the <code>manager-movie-view.fxml</code> file and
     * displays it in a modal stage.</p>
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onManageMoviesButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("manager-movie-view.fxml"));
        Parent view = fxmlLoader.load();
        MovieManagementController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Manage Movies");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
    }

    /**
     * Opens the room management window.
     * <p>This loads the <code>room-view.fxml</code> file and
     * displays it in a modal stage.</p>
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onManageRoomsButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("room-view.fxml"));
        Parent view = fxmlLoader.load();
        RoomManagementView newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Manage Rooms");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
    }

    /**
     * Opens the ticket sales report grouped by movie.
     * <p>This loads the <code>tickets-sold-by-movie.fxml</code> file and
     * displays it in a modal stage.</p>
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onViewSalesMovieButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("tickets-sold-by-movie.fxml"));
        Parent view = fxmlLoader.load();
        TicketSoldByMovieController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Ticket Sales");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
    }

    /**
     * Opens the ticket sales report grouped by showtime.
     * <p>This loads the <code>tickets-sold-by-showtime.fxml</code> file and
     * displays it in a modal stage.</p>
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onViewSalesShowTimeButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("tickets-sold-by-showtime.fxml"));
        Parent view = fxmlLoader.load();
        TicketSoldByShowTimeController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Ticket Sales");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
    }

    /**
     * Logs out the current user and returns to the login screen.
     * <p>
     * This opens the login view in a new window and closes the manager window.
     * </p>
     *
     * @param pEvent the action event triggered by the logout button
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onLogOutButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("login-view.fxml"));
        Parent view = fxmlLoader.load();
        LogInViewController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Ticket Sales");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.show();
        Stage stage = (Stage) aLogOutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the entire application.
     * <p>
     * This uses {@link Platform#exit()} to shut down the JavaFX runtime.
     * </p>
     *
     * @param pEvent the action event triggered by the close button
     */
    @FXML
    private void onCloseButtonClick(ActionEvent pEvent) {
        Platform.exit();
    }
}
