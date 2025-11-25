package com.example.oop2f25finalproject.Model;

import java.util.List;

/**
 * Handles the addition of new screening rooms to the system.
 * Performs validation for empty fields, numeric values, positive numbers,
 * and ensures room names and numbers are unique.
 *
 * This class serves as the model for the Add Room functionality.
 *
 * @author Rohina
 */
public class AddRoom {

    /** The list of existing rooms where new rooms will be added */
    private  final List<Room> aRoomList;

    /**
     * Constructor for AddRoom
     *
     * @param pRoomList List of rooms
     */
    public AddRoom(List<Room> pRoomList) {
        this.aRoomList = pRoomList;
    }

    /**
     * Adds a new room to the list after validation.
     *
     * @param pName     The name of the room
     * @param pNumber   The room number as a String
     * @param pCapacity The room capacity as a String
     * @return          The newly created Room object
     * @throws IllegalArgumentException If validation fails
     */
    public Room addRoom(String pName, String pNumber, String pCapacity) {

        // Validate room name
        if (pName == null || pName.isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }

        int roomNumber;
        int roomCapacity;

        // Validate numeric input
        try {
            roomNumber = Integer.parseInt(pNumber);
            roomCapacity = Integer.parseInt(pCapacity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Room number must be a valid number");
        }

        // Validate positive numbers
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be a positive number");
        }

        if (roomCapacity <= 0) {
            throw new IllegalArgumentException("Room capacity must be a positive number");
        }

        // Check for duplicate room name
        for (Room pRoom : aRoomList) {
            if (pRoom.getRoomName().equalsIgnoreCase(pName)) {
                throw new IllegalArgumentException("A room with this name already exists");
            }
        }

        // Check for duplicate room number
        for (Room pRoom : aRoomList) {
            if (pRoom.getRoomNumber() == roomNumber) {
                throw new IllegalArgumentException("A room with this number already exists");
            }
        }

        // Create new room and add to the list
        Room newRoom = new Room(pName, roomNumber, roomCapacity);
        aRoomList.add(newRoom);

        return newRoom;
    }
}
