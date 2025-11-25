package com.example.oop2f25finalproject.Model;

/**
 * Represents a screening room.
 * Each room has a name, a unique number, and a seating capacity.
 *
 * This class serves as the data model for Add, Edit, and Delete Room operations.
 *
 * @author Rohina
 */
public class Room {

    /** Name of the screening room */
    private String aRoomName;

    /** Unique number identifying the room */
    private int aRoomNumber;

    /** Seating capacity of the room */
    private int aRoomCapacity;

    /**
     * Creates a new Room object with the specified name, number, and capacity.
     *
     * @param pRoomName     The name of the room
     * @param pRoomNumber   The unique number of the room
     * @param pRoomCapacity The seating capacity of the room
     */
    public Room(String pRoomName, int pRoomNumber, int pRoomCapacity) {
        this.aRoomName = pRoomName;
        this.aRoomNumber = pRoomNumber;
        this.aRoomCapacity = pRoomCapacity;
    }

    /**
     *
     * @return The name of the room
     */
    public String getRoomName() {
        return aRoomName;
    }

    /**
     * Sets a new name for the room.
     *
     * @param pRoomName The name of the room
     */
    public void setRoomName(String pRoomName) {
        this.aRoomName = pRoomName;
    }

    /**
     *
     * @return  The number of the room
     */
    public int getRoomNumber() {
        return aRoomNumber;
    }

    /**
     * Sets a number for the room.
     *
     * @param pRoomNumber   The new room number
     */
    public void setRoomNumber(int pRoomNumber) {
        this.aRoomNumber = pRoomNumber;
    }

    /**
     *
     * @return The room capacity
     */
    public int getRoomCapacity() {
        return aRoomCapacity;
    }

    /**
     * Sets the seating capacity for the room.
     *
     * @param pRoomCapacity The new room capacity
     */
    public void setRoomCapacity(int pRoomCapacity) {
        this.aRoomCapacity = pRoomCapacity;
    }

}
