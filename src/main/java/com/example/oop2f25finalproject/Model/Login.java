package com.example.oop2f25finalproject.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all login-related logic, including authentication and client registration.
 * Serves as the model for the login functionality.
 *
 * @author Rohina
 */

public class Login {

    /**
     * The singleton instance of Login.
     */
    private static Login instance;

    /**
     * Single fixed manager account.
     */
    private final Manager manager;

    /**
     * List of registered clients.
     */
    private final List<Client> clients;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the login model with a fixed manager and empty client list.
     */
    private Login() {
        // manager credentials
        manager = new Manager("Admin", "admin@gmt.com", "admin123");
        clients = new ArrayList<>();
    }

    /**
     * Created if the instance does not exist.
     * @return The singleton Login instance
     */
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
    /**
     * Registers a new client by adding it to the client list.
     *
     * @param client The client to register
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Authenticates a user by email and password.
     *
     * @param email The user's email
     * @param password The user's password
     * @return Manager object if manager logs in,
     *         A Client object is a client logs in,
     *         Null if credentials are invalid
     */
    public User authenticate(String email, String password) {
        // Check manager credentials
        if (manager.getaEmail().equals(email) && manager.getaPassword().equals(password)) {
            return manager;
        }

        // Check registered client credentials
        for (Client client : clients) {
            if (client.getaEmail().equals(email) && client.getaPassword().equals(password)) {
                return client;
            }
        }

        return null; // login failed
    }

    /**
     *
     * @return List of registered clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     *
     * @return Manager
     */
    public Manager getManager() {
        return manager;
    }
}
