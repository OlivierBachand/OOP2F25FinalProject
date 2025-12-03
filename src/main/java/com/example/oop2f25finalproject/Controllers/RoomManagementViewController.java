package com.example.oop2f25finalproject.Controllers;

import com.example.oop2f25finalproject.Model.Room;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the room management view.
 * This controller handles the display and management of cinema rooms,
 * including adding, editing, and deleting rooms. It displays all rooms
 * in a ListView with their names and capacities.
 *
 * @author Shanley Aninzo
 */
public class RoomManagementViewController {

    /** ListView displaying all rooms with their details */
    @FXML
    private ListView<String> aRoomListView;

    /** Observable list containing formatted room information for display */
    private ObservableList<String> aRooms = FXCollections.observableArrayList();

    /**
     * Initializes the controller and populates the ListView with room data.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the ListView and loads all existing rooms from the system.
     *
     */
    @FXML
    public void initialize() {
        aRoomListView.setItems(aRooms);
        refreshRooms();
    }

    /**
     * Refreshes the room list by reloading all rooms from the Room model.
     * This method clears the current display list and rebuilds it from
     * the Room.roomList, formatting each room as "Room [Name] - Capacity: [Number]".
     * It's called after any add, edit, or delete operation to keep the display current.
     *
     */
    private void refreshRooms() {
        aRooms.clear();
        if (Room.roomList != null) {
            for (Room room : Room.roomList) {
                aRooms.add(String.format("Room %s - Capacity: %d", room.getName(), room.getCapacity()));
            }
        }
    }

    /**
     * Handles the back button click event.
     * Closes the room management window and returns to the previous view
     *
     * @param pEvent the action event triggered by the button click
     */
    @FXML
    public void onBackButtonClick(ActionEvent pEvent) {
        Stage stage = (Stage) aRoomListView.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the close button click event.
     * Exits the entire application by shutting down the JavaFX runtime.
     *
     * @param pEvent the action event triggered by the button click
     */
    @FXML
    public void onCloseButtonClick(ActionEvent pEvent) {
        Platform.exit();
    }

    /**
     * Handles the add button click event.
     * Opens a modal dialog for adding a new room. After the dialog is closed,
     * the room list is refreshed to display the newly added room.
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file for the add room view cannot be loaded
     */
    @FXML
    public void onAddButtonClick(ActionEvent pEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MovieTheatreApplication.class.getResource("add-room-view.fxml")
        );
        Parent view = fxmlLoader.load();

        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Add Room");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();

        // Refresh the list to show the newly added room
        refreshRooms();
    }

    /**
     * Handles the edit button click event.
     * Opens a modal dialog for editing the selected room. If no room is selected,
     * displays an error alert. After the dialog is closed, the room list is
     * refreshed to display any changes.
     *
     * @param pEvent the action event triggered by the button click
     * @throws IOException if the FXML file for the edit room view cannot be loaded
     */
    @FXML
    public void onEditButtonClick(ActionEvent pEvent) throws IOException {
        int selectedIndex = aRoomListView.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader(
                MovieTheatreApplication.class.getResource("edit-room-view.fxml")
        );
        Parent root = loader.load();

        EditRoomController controller = loader.getController();

        Room selectedRoom = Room.getRoom(selectedIndex);
        controller.setRoomToEdit(selectedRoom);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 475, 475));
        stage.setTitle("Edit Room: " + selectedRoom.getName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) pEvent.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();

        refreshRooms();
    }

    /**
     * Handles the delete button click event.
     * Deletes the selected room from the system. If no room is selected,
     * displays an error alert. After deletion, the room list is refreshed.
     *
     * @param pEvent the action event triggered by the button click
     */
    @FXML
    public void onDeleteButtonClick(ActionEvent pEvent) {
        int selectedIndex = aRoomListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            // Remove the room from the model
            Room.removeRoom(selectedIndex);
            // Refresh the display
            refreshRooms();
        } else {
            // Show error if no room is selected
            new Alert(Alert.AlertType.ERROR, "No room selected", ButtonType.OK).showAndWait();
        }
    }
}
