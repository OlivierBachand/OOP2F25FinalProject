package com.example.oop2f25finalproject;

public class User {
    private final String aName;
    private final String aEmail;
    private final String aPassword;

    public User(String pName, String pEmail, String pPassword) {
        this.aName = pName;
        this.aEmail = pEmail;
        this.aPassword = pPassword;
    }

    public String getaName() {
        return aName;
    }

    public String getaEmail() {
        return aEmail;
    }

    public String getaPassword() {
        return aPassword;
    }
}
