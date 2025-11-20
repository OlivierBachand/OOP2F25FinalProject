package com.example.oop2f25finalproject;

public class Client extends User {
    private final int aClientID;

    public Client(int pClientID, String pName, String pEmail, String pPassword) {
        super (pName, pEmail, pPassword);
        this.aClientID = pClientID;
    }

    public int getaClientID() {
        return aClientID;
    }
}
