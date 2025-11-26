package com.example.oop2f25finalproject.Model;

import java.util.List;

/**
 * Handles sign-up logic for registering new clients.
 *
 * This class performs input validation, checks for duplicate emails,
 * and assigns unique client IDs.
 *
 * @author Rohina
 */
public class Signup {

    /**
     * Reference to the login model that stores clients and manager
     */
    private final Login aLogin;

    /**
     * Constructs the new Signup model with the given login instance.
     *
     * @param pLogin The login instance
     */
    public Signup(Login pLogin) {
        this.aLogin = pLogin;
    }

    /**
     * Registers a new client with the given name, email, and password.
     *
     * @param pName             Client's name
     * @param pEmail            Client's email address
     * @param pPassword         Client's password
     * @param pConfirmPassword  Password confirmation
     * @return The newly registered Client
     * @throws IllegalArgumentException if validation fails or email already exists
     */
    public Client registerClient(String pName, String pEmail, String pPassword, String pConfirmPassword) {
        // Trim input to remove leading/trailing spaces
        pName = pName.trim();
        pEmail = pEmail.trim();

        // Validate fields
        if (pName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (pEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (pPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (!pPassword.equals(pConfirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Check if the email is already registered
        List<Client> existingClients = aLogin.getClients();
        for (Client client : existingClients) {
            if (client.getaEmail().equalsIgnoreCase(pEmail)) {
                throw new IllegalArgumentException("This email is already registered");
            }
        }

        // Assign unique client ID
        int newClientID = existingClients.size() + 1;

        // Create and register new client
        Client newClient = new Client(newClientID, pName, pEmail, pPassword);
        aLogin.addClient(newClient);

        return newClient;
    }
}
