package com.example.oop2f25finalproject.Model;

/**
 * Represents a ticket in the movie ticket booking system.
 *  * A Ticket connects a Client to a specific ShowTime (movie representation).
 *  *
 *  * Each ticket is for one movie showing at a specific date/time in a specific room.
 *  * The ticket stores the client ID, showtime, and price information.
 *
 * @author Shanley Aninzo
 */
public class Ticket {
    /** The ID of the client who purchased this ticket */
    private final int aClientID;
    /** The showtime this ticket is for */
    private final ShowTime aShowTime;
    /** The price of this ticket */
    private final double aPrice;

    /**
     * Creates a new Ticket for a client and showtime.
     * All fields are validated to ensure data integrity.
     *
     * @param pClientID The ID of the client who purchased this ticket
     * @param pShowTime The showtime this ticket is for
     * @param pPrice The price of the ticket
     * @throws IllegalArgumentException if clientID is invalid or price is negative
     * @throws NullPointerException if showtime is null
     */
    public Ticket(int pClientID, ShowTime pShowTime, double pPrice) {
        if (pClientID <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero.");
        }
        if (pShowTime == null) {
            throw new IllegalArgumentException("Show time must not be null.");
        }
        if (pPrice <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        this.aClientID = pClientID;
        this.aShowTime = pShowTime;
        this.aPrice = pPrice;
    }

    /**
     * Gets the client ID associated with this ticket.
     * Used to link tickets back to their purchasing client.
     *
     * @return The ID of the client who owns this ticket
     */
    public int getClientID() {
        return aClientID;
    }

    /**
     * Gets the showtime for this ticket.
     *
     * @return The showtime for this ticket
     */

    public ShowTime getaShowtime() {
        return aShowTime;
    }

    /**
     * Gets the price of this ticket.
     *
     * @return The ticket price in dollars
     */
    public double getaPrice() {
        return aPrice;
    }

    /**
     * Returns a string representation of this ticket.
     *
     * @return A formatted string containing ticket details
     */
    @Override
    public String toString() {
        return String.format("Ticket[ClientID=%d, ShowTime=%s, Price=$%.2f",
        aClientID, aShowTime.getaDateTime(), aPrice);
    }
}



