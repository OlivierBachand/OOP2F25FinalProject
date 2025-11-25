package com.example.oop2f25finalproject.Model;

/**
 * Model class to handle editing existing rooms.
 * Updates name and/or capacity while validating input.
 *
 * @author Rohina
 */
public class EditRoom {

    /**
     * Edits an existing room's name and capacity.
     *
     * @param pRoom         The Room to edit
     * @param pNewName      New name for the room
     * @param pNewCapacity  New capacity for the room as a string
     * @throws IllegalArgumentException If validation fails
     */
    public void editRoom(Room pRoom, String pNewName, String pNewCapacity) {

        // Update name if changed
        if (pNewName != null && !pNewName.trim().isEmpty() && !pNewName.equalsIgnoreCase(pRoom.getName())) {
            for (Room room : Room.roomList) {
                if (room.getName().equalsIgnoreCase(pNewName.trim())) {
                    throw new IllegalArgumentException("Room name already exists");
                }
            }
            pRoom.setName(pNewName.trim());
        }

        // Update capacity if changed
        if (pNewCapacity != null && !pNewCapacity.trim().isEmpty()) {
            int capacity;
            try {
                capacity = Integer.parseInt(pNewCapacity.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Capacity must be a valid number");
            }

            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be a positive number");
            }

            pRoom.setCapacity(pNewCapacity);
        }
    }
}
