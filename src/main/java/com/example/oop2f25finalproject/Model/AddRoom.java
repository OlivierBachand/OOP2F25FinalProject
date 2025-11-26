package com.example.oop2f25finalproject.Model;

import java.util.List;

/**
 * Model class to handle adding new rooms.
 * Validates input before creating a new Room.
 *
 * @author Rohina
 */
public class AddRoom {

     /**
     * Adds a new room to the system after validation.
     *
     * @param pName         Room name
     * @param pCapacity     Room capacity as a string
     * @return              The newly created Room
     * @throws IllegalArgumentException If validation fails
     */
    public Room addRoom(String pName, String pCapacity) {

        // Validate name
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }

        String name = pName.trim();

        // Check if roomList exists then check for duplicate room name
        if (Room.roomList != null) {
            for (Room room : Room.roomList) {
                if (room.getName().equalsIqnoreCase(name)) {
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
            throw new IllegalArgumentException("Room capacity must be greater than 0");
        }

        // Create new room
        return new Room(roomCapacity, name);
    }
}
