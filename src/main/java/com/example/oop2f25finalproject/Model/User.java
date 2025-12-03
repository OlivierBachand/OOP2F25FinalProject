package com.example.oop2f25finalproject.Model;

/**
 * Represents a user in the movie ticket booking system.
 * This is an abstract base class for different types of users (Client, Manager).
 * The User class serves as a common structure for all users in the system.
 *
 * @author Shanley Aninzo
 */
public abstract class User {
    /** The user's name */
    private final String aName;
    /** The user's email address */
    private final String aEmail;
    /** The user's password */
    private final String aPassword;

    /**
     * This constructor validates that the name is not null or empty,
     * the email follows a valid format, and the password has at least 6 characters.
     * </p>
    *
    * @param pName The user's name (must not be null or empty)
    * @param pEmail The user's email address (must be a valid format)
    * @param pPassword The user's password (must be at least 6 characters)
    * @throws IllegalArgumentException if any parameter is invalid
    */
    public User(String pName, String pEmail, String pPassword) {
        validateName(pName);
        validateEmail(pEmail);
        validatePassword(pPassword);

        this.aName = pName;
        this.aEmail = pEmail;
        this.aPassword = pPassword;
    }

    /**
     * Validates that a name is not null or empty.
     *
     * @param pName The name to validate
     * @throws IllegalArgumentException if the name is null or empty
     */
    public static void validateName(String pName) {
        if (pName == null || pName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    /**
     * Validates that an email is not null, not empty, and follows a valid email pattern.
     *
     * @param pEmail The email to validate
     * @throws IllegalArgumentException if the email is null, empty, or invalid
     */
    public static void validateEmail(String pEmail) {
        String validEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (pEmail == null || pEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!pEmail.matches(validEmail)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    /**
     * Validates that a password is not null and has at least 6 characters.
     *
     * @param pPassword The password to validate
     * @throws IllegalArgumentException if the password is null or less than 6 characters
     */
    public static void validatePassword(String pPassword) {
        if (pPassword == null || pPassword.length() <6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
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