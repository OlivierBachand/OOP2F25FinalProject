package com.example.oop2f25finalproject.Model;

import java.util.List;

/**
 * Model class to handle adding new rooms.
 * Interacts with the static Room.roomList from the Room class.
 * Validates input before creating a new Room.
 *
 * @author Rohina
 */
public class AddRoom {

    /**
     * Adds a new room to the system.
     *
     * @param pName         Name of the room
     * @param pCapacity     Capacity of the room as a string
     * @return              The newly created Room
     * @throws IllegalArgumentException If validation fails
     */
    public Room addRoom(String pName, String pCapacity) {

        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }

        // Check for duplicate room name
        if (Room.roomList != null) {
            for (Room room : Room.roomList) {
                if (room.getName().equalsIqnoreCase(pName.trim())) {
                    throw new IllegalArgumentException("Room name already exists");
                }
            }
        }

        int roomCapacity;

        // Validate capacity
        try {
            roomCapacity = Integer.parseInt(pCapacity.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Room capacity must be a valid number");
        }

        if (roomCapacity <= 0) {
            throw new IllegalArgumentException("Room capacity must be a positive number");
        }

        // Create new room
        return new Room(pCapacity, pName);
    }
}
