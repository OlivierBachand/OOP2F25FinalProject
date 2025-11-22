package com.example.oop2f25finalproject.Model;

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
     * @param pName The user's name (must not be null or empty)
     * @param pEmail The user's email address (must be valid format)
     * @param pPassword The user's password (must be at least 6 characters)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public User(String pName, String pEmail, String pPassword) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (!isValidEmail(pEmail)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (pPassword == null || pPassword.length() <6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        this.aName = pName;
        this.aEmail = pEmail;
        this.aPassword = pPassword;
    }

    /**
     * Validates email format using regex pattern.
     * Ensures email contains @ symbol and valid domain structure.
     *
     * @param pEmail The email to validate
     * @return true if email format is valid, false otherwise
     */
    private boolean isValidEmail(String pEmail) {
        String validEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return pEmail != null && pEmail.matches(validEmail);
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

    /**
     * Returns a string representation of the user.
     *
     * @return A formatted string containing user details
     */
    @Override
    public String toString() {
        return String.format("User[Name=%s, Email=%s]", aName, aEmail);
    }
}
