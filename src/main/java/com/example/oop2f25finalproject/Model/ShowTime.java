package com.example.oop2f25finalproject.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a scheduled movie showtime, including the date/time,
 * the room where it plays, and the tickets associated with it.
 * @author Olivier bachand
 */
public class ShowTime {

    /** The date and time when the show occurs. */
    private LocalDateTime aDateTime;

    /** The room in which the show will play. */
    private Room aRoom;

    /** The list of tickets sold or reserved for this showtime. */
    private final List<Ticket> aTickets = new ArrayList<>();

    /**
     * Constructs a ShowTime with a specific date/time and room.
     *
     * @param pDateTime the scheduled date and time
     * @param pRoom the room assigned for the show
     */
    public ShowTime(LocalDateTime pDateTime, Room pRoom) {
        this.aDateTime = pDateTime;
        this.aRoom = pRoom;
    }

    /**
     * Adds a ticket to this showtime.
     *
     * @param ticket the ticket to add
     * @throws NullPointerException if the ticket is null
     */
    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            aTickets.add(ticket);
        }
        else {
            throw new NullPointerException("Ticket is null");
        }
    }

    /**
     * Returns a copy of the list of tickets for this showtime.
     *
     * @return a new list containing all tickets
     */
    public List<Ticket> getTickets() {
        return new ArrayList<>(aTickets);
    }

    /**
     * Returns the room assigned to this showtime.
     *
     * @return the room
     */
    public Room getaRoom() {
        return aRoom;
    }

    /**
     * Returns the date and time of the showtime.
     *
     * @return the showtime's date and time
     */
    public LocalDateTime getaDateTime() {
        return aDateTime;
    }

    /**
     * Sets the date and time of the showtime using a formatted string.
     * Expected format: dd/MM/yyyy HH:mm
     *
     * @param pDateTime the date/time string
     * @throws java.time.format.DateTimeParseException if the format is invalid
     */
    public void setDateTime(String pDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.aDateTime = LocalDateTime.parse(pDateTime, formatter);
    }

    /**
     * Sets the room for the showtime.
     *
     * @param pRoom the new room
     */
    public void setRoom(Room pRoom) {
        this.aRoom = pRoom;
    }
}

