package com.example.oop2f25finalproject;

/**
 * Represents a user in the movie ticket booking system.
 * This is an abstract base class for different types of users (Client, Manager).
 *
 * The User class is part of a disjoint specialization hierarchy, meaning
 * a user can be either a Client or a Manager, but not both.
 *
 * @author Shanley Aninzo
 */
public class User {
    /** The user's name */
    private final String aName;
    /** The user's email address */
    private final String aEmail;
    /** The user's password */
    private final String aPassword;

    /**
     * Creates a new User with the specified credentials.
     *
     * @param pName The user's name
     * @param pEmail The user's email address
     * @param pPassword The user's password
     */
    public User(String pName, String pEmail, String pPassword) {
        this.aName = pName;
        this.aEmail = pEmail;
        this.aPassword = pPassword;
    }

    /**
     * Gets the user's name.
     *
     * @return The user's full name
     */
    public String getaName() {
        return aName;
    }

    /**
     * Gets the user's email address.
     *
     * @return The user's email address
     */
    public String getaEmail() {
        return aEmail;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password
     */
    public String getaPassword() {
        return aPassword;
    }
}
