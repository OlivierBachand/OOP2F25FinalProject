package com.example.oop2f25finalproject.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room within the system, containing basic information such as
 * its capacity and name. A static list of all created rooms is maintained.
 *
 * <p>This class provides validation for capacity (accepting both integers and
 * numeric strings) and ensures that room names are non-empty. Each constructed
 * Room instance is automatically added to the global room list.</p>
 *
 * @author Olivier Bachand
 */
public class Room {
    /** A static collection maintaining all Room instances created. */
    public static List<Room> roomList = new ArrayList<>();

    /** Maximum number of people the room can accommodate. */
    private int aCapacity;

    /** Name identifying the room. */
    private String aName;

    /**
     * Constructs a Room using an integer capacity and a room name.
     * Automatically adds the room to the global room list.
     *
     * @param pCapacity The capacity of the room (must be non-negative).
     * @param pName     The name of the room (must be non-empty).
     * @throws IllegalArgumentException if capacity is negative or name invalid.
     */
    public Room(int pCapacity, String pName) {
        this.setCapacity(pCapacity);
        this.setName(pName);
        if (Room.roomList == null) {  // Only create list if it doesn't exist
            Room.roomList = new ArrayList<>();
        }
        Room.addRoom(this);
    }

    /**
     * Constructs a Room using a string-based capacity and a room name.
     * Automatically adds the room to the global room list.
     *
     * @param pCapacity The room capacity as a numeric string (digits only).
     * @param pName     The name of the room (must be non-empty).
     * @throws NumberFormatException    if the capacity string contains non-numeric characters.
     * @throws IllegalArgumentException if the name is invalid.
     */
    public Room(String pCapacity, String pName) {
        this.setCapacity(pCapacity);
        this.setName(pName);
        if (Room.roomList == null) {  // Only create list if it doesn't exist
            Room.roomList = new ArrayList<>();
        }
        Room.addRoom(this);
    }

    /**
     * Returns the Room stored at a given index in the global room list.
     *
     * @param pIndex the index of the desired Room.
     * @return the Room at the specified index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public static Room getRoom(int pIndex) {
        return roomList.get(pIndex);
    }

    /**
     * Adds a new Room to the global list.
     *
     * @param pRoom the Room to add (must not be null).
     * @throws NullPointerException if the Room is null.
     */
    private static void addRoom(Room pRoom) {
        if (pRoom == null) {
            throw new NullPointerException("Room is null");
        }
        roomList.add(pRoom);
    }

    /**
     * Removes a Room at the specified index from the global list.
     *
     * @param pIndex index of the Room to remove.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public static void removeRoom(int pIndex) {
        roomList.remove(pIndex);
    }

    /**
     * Returns the capacity of this Room.
     *
     * @return the room capacity.
     */
    public int getCapacity() {
        return aCapacity;
    }

    /**
     * Sets the capacity of this Room using an integer.
     *
     * @param pCapacity the new capacity (must not be negative).
     * @throws IllegalArgumentException if capacity is negative.
     */
    public void setCapacity(int pCapacity) {
        if (pCapacity < 0)
            throw new IllegalArgumentException("Capacity must be a positive number");
        this.aCapacity = pCapacity;
    }

    /**
     * Sets the capacity of this Room using a string-based numeric value.
     *
     * @param pCapacity string containing digits representing the capacity.
     * @throws NumberFormatException if the string contains non-digit characters.
     */
    public void setCapacity(String pCapacity) {
        pCapacity = pCapacity.trim();
        for (int i = 0; i < pCapacity.length(); i++) {
            // Checks if the string is only composed of numbers.
            if ((int) pCapacity.charAt(i) < 48 || (int) pCapacity.charAt(i) > 57)
                throw new NumberFormatException("Invalid capacity: Only integers are accepted.");
        }
        this.aCapacity = Integer.parseInt(pCapacity);
    }

    /**
     * Returns the name of the Room.
     *
     * @return the room name.
     */
    public String getName() {
        return aName;
    }

    /**
     * Sets the name of the Room.
     *
     * @param pName the new name (must not be null or empty).
     * @throws IllegalArgumentException if name is null or empty.
     */
    public void setName(String pName) {
        if (pName == null || pName.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");

        // Check if roomList exists then check for duplicate room name
        if (roomList != null) {
            for (Room room : roomList) {
                if (room.getName().equalsIgnoreCase(pName.trim())) {
                    throw new IllegalArgumentException("Room name already exists");
                }
            }
        }
        this.aName = pName.trim();
    }
}
