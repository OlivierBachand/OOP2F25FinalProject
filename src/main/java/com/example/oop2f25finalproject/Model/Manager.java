package com.example.oop2f25finalproject.Model;

/**
 * Represents a manager in teh movie ticket booking system.
 *
 * This class extends the {@link User} class and does not add additional fields
 * for now, but it can be expanded later to include manager-specific functionalities.
 *
 * @author Rohina
 */
public class Manager extends User {

    /**
     * Creates a new Manager with the specified credentials.
     *
     * @param pName Manager's full name
     * @param pEmail Manager's email address
     * @param pPassword Manager's password
     */
    public Manager(String pName, String pEmail, String pPassword) {
        super(pName, pEmail, pPassword);
    }
}
