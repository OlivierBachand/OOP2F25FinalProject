package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Movie;
import com.example.oop2f25finalproject.MovieTheatreApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller responsible for managing the movie list within the manager interface.
 * <p>
 * This view allows the user to:
 * <ul>
 *     <li>View all movies</li>
 *     <li>Add new movies</li>
 *     <li>Edit existing movies</li>
 *     <li>Delete movies</li>
 *     <li>Return to the previous screen</li>
 *     <li>Close the application</li>
 * </ul>
 * Movies displayed in the list view are fetched from {@link Movie#movieList}.
 * </p>
 *
 * <p>
 * The controller maintains an observable list to ensure that the ListView updates
 * automatically when movie data changes.
 * </p>
 *
 * @author Olivier Bachand
 */
public class MovieManagementViewController {

    /** ListView displaying the string representation of all movies. */
    @FXML
    private ListView<Movie> aMoviesListView;

    /**
     * Handles the Back button click.
     * <p>
     * This closes the current window and returns to the previous manager view.
     * </p>
     */
    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) aMoviesListView.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the entire application.
     * <p>
     * Uses {@link Platform#exit()} to shut down JavaFX completely.
     * </p>
     */
    @FXML
    private void onCloseButtonClick() {
        Platform.exit();
    }

    /**
     * Initializes the Movie Management view.
     * <p>
     * Populates the ListView with all current movies from {@link Movie#movieList}.
     * </p>
     */
    public void initialize() {
        aMoviesListView.setCellFactory(lv -> new ListCell<Movie>() {
            private final HBox content;
            private final Label titleLabel;
            private final Label genreLabel;
            private final Label lengthLabel;

            {
                titleLabel = new Label();
                genreLabel = new Label();
                lengthLabel = new Label();

                int width = (int) (aMoviesListView.getWidth() / 3);
                titleLabel.setPrefWidth(width);
                genreLabel.setPrefWidth(width);
                lengthLabel.setPrefWidth(width);

                content = new HBox(20);
                content.getChildren().addAll(titleLabel, genreLabel, lengthLabel);
            }

            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    titleLabel.setText(item.getTitle());
                    genreLabel.setText(item.getaGenre());
                    lengthLabel.setText(item.getLength().toString());
                    setGraphic(content);
                }
            }
        });

        for (int i = 0; i < Movie.movieList.size(); i++) {
            this.aMoviesListView.getItems().add(Movie.getMovie(i));
        }
    }

    /**
     * Refreshes the movie list displayed in the ListView.
     * <p>
     * This method is typically called after adding, editing, or removing a movie.
     * </p>
     */
    private void refreshMovies() {
        this.aMoviesListView.getItems().clear();
        for (int i = 0; i < Movie.movieList.size(); i++) {
            this.aMoviesListView.getItems().add(Movie.getMovie(i));
        }
    }

    /**
     * Opens the Edit Movie window for the selected movie.
     * <p>
     * If a movie is selected, this loads <code>edit-movie-view.fxml</code>,
     * passes the selected movie to the controller, and opens a modal editing window.
     * After editing, the movie list refreshes.
     * </p>
     *
     * @param pEvent the action event triggered by the Edit button
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onEditButtonClick(ActionEvent pEvent) throws IOException {
        int selectedIndex = aMoviesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("edit-movie-view.fxml"));
            Parent view = fxmlLoader.load();
            EditMovieViewController newView = fxmlLoader.getController();
            Scene nextScene = new Scene(view, 475, 475);
            Stage nextStage = new Stage();
            newView.setMovie(Movie.getMovie(selectedIndex));
            nextStage.setScene(nextScene);
            nextStage.setTitle(Movie.getMovie(selectedIndex).getTitle());
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
            nextStage.setResizable(false);
            nextStage.showAndWait();
            this.refreshMovies();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No movie selected", ButtonType.OK).showAndWait();
        }
    }

    /**
     * Deletes the selected movie from the system.
     * <p>
     * If no movie is selected, an error alert is shown.
     * </p>
     */
    @FXML
    private void onDeleteButtonClick() {
        int selectedIndex = aMoviesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(Alert.AlertType.ERROR, "No movie selected", ButtonType.OK).showAndWait();
        }
        else {
            Movie.removeMovie(selectedIndex);
            this.refreshMovies();
        }
    }

    /**
     * Opens the Add Movie window.
     * <p>
     * Loads <code>add-movie-view.fxml</code> and displays the add-movie form in a
     * modal window. After adding a new movie, the displayed movie list is refreshed.
     * </p>
     *
     * @param pEvent the action event triggered by the Add button
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onAddButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieTheatreApplication.class.getResource("add-movie-view.fxml"));
        Parent view = fxmlLoader.load();
        AddMovieViewController newView = fxmlLoader.getController();
        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Add Movie");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();
        this.refreshMovies();
    }
}
