package com.example.oop2f25finalproject.Model;

/**
 * Represents a client in the movie ticket booking system.
 * A Client is a specialized type of User who can book tickets.
 *
 * This class extends User and adds client-specific functionality,
 * including a unique client identifier used for ticket bookings.
 *
 * Relationships:
 * - A Client can book many Tickets (1 to many relationship)
 *
 * @author Shanley Aninzo
 *
 */
public class Client extends User {
    /** Unique identifier for this client */
    private final int aClientID;

    /**
     * Creates a new Client with the specified credentials and ID.
     *
     * @param pClientID The unique identifier for this client
     * @param pName The client's name
     * @param pEmail The client's email address
     * @param pPassword The client's password
     */
    public Client(int pClientID, String pName, String pEmail, String pPassword) {
        super (pName, pEmail, pPassword);
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
}
