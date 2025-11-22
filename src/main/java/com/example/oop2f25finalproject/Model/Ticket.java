package com.example.oop2f25finalproject.Model;

public class Ticket {
    private final int aClientID;
    private final ShowTime aShowTime;
    private final double aPrice;

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

    public int getClientID() {
        return aClientID;
    }

    public ShowTime getaShowtime() {
        return aShowTime;
    }

    public double getaPrice() {
        return aPrice;
    }

    @Override
    public String toString() {
        return String.format("Ticket[ClientID=%d, ShowTime=%s, Price=$%.2f",
        aClientID, aShowTime.getaDateTime(), aPrice);
    }
}



