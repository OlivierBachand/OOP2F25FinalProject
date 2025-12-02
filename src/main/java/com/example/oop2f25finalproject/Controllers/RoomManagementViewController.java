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

public class RoomManagementViewController {

    @FXML
    private ListView<String> aRoomListView;

    private ObservableList<String> aRooms = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        aRoomListView.setItems(aRooms);
        refreshRooms();
    }

    private void refreshRooms() {
        aRooms.clear();
        if (Room.roomList != null) {
            for (Room room : Room.roomList) {
                aRooms.add(String.format("Room %s - Capacity: %d",
                        room.getName(), room.getCapacity()));
            }
        }
    }

    @FXML
    public void onBackButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) aRoomListView.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCloseButtonClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MovieTheatreApplication.class.getResource("add-room-view.fxml")
        );
        Parent view = fxmlLoader.load();

        Scene nextScene = new Scene(view, 475, 475);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.setTitle("Add Room");
        nextStage.initModality(Modality.WINDOW_MODAL);
        nextStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        nextStage.setResizable(false);
        nextStage.showAndWait();

        refreshRooms();
    }

    @FXML
    public void onEditButtonClick(ActionEvent actionEvent) throws IOException {
        int selectedIndex = aRoomListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MovieTheatreApplication.class.getResource("edit-room-view.fxml")
            );
            Parent view = fxmlLoader.load();

            Room selectedRoom = Room.getRoom(selectedIndex);

            Scene nextScene = new Scene(view, 475, 475);
            Stage nextStage = new Stage();
            nextStage.setScene(nextScene);
            nextStage.setTitle("Edit Room: " + selectedRoom.getName());
            nextStage.initModality(Modality.WINDOW_MODAL);
            nextStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            nextStage.setResizable(false);
            nextStage.showAndWait();

            refreshRooms();
        } else {
            new Alert(Alert.AlertType.ERROR, "No room selected", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void onDeleteButtonClick(ActionEvent actionEvent) {
        int selectedIndex = aRoomListView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            Room.removeRoom(selectedIndex);
            refreshRooms();
        } else {
            new Alert(Alert.AlertType.ERROR, "No room selected", ButtonType.OK).showAndWait();
        }
    }
}

