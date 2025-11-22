package com.example.oop2f25finalproject.Model;

/**
 * Represents a client in the movie ticket booking system.
 * A Client is a specialized type of User who can book tickets.
 *
 * This class extends User and adds client-specific functionality,
 * including a unique client identifier used for ticket bookings.
 *
 * @author Shanley Aninzo
 *
 */
public class Client extends User {
    /** Unique identifier for this client */
    private final int aClientID;

    /**
     * Creates a new Client with the specified credentials and ID.
     * Calls the parent User constructor to set and validate common properties.
     *
     * @param pClientID The unique identifier for this client (must be positive)
     * @param pName The client's name (validated by User constructor)
     * @param pEmail The client's email address (validated by User constructor)
     * @param pPassword The client's password (validated by User constructor)
     * @throws IllegalArgumentException if clientID is not positive, or if User validation fails
     */
    public Client(int pClientID, String pName, String pEmail, String pPassword) {
        super (pName, pEmail, pPassword);

        if (pClientID <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero.");
        }

        this.aClientID = pClientID;
    }

    /**
     * Gets the client's unique identifier.
     * This ID is used to associate tickets with this client.
     *
     * @return The client's ID
     */
    public int getaClientID() {
        return aClientID;
    }

    /**
     * Returns a string representation of this client.
     *
     * @return A formatted string containing client details
     */
    @Override
    public String toString() {
        return String.format("Client[ID=%d], Email=%s", aClientID, getaName(), getaEmail());
    }
}
