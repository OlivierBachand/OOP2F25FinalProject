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

    private final Manager manager; // single fixed manager
    private final List<Client> clients; // dynamic list of clients

    /**
     * Initializes the login model with a fixed manager and empty client list.
     */
    public Login() {
        // manager credentials
        manager = new Manager("Admin", "admin@gmt.com", "admin123");
        clients = new ArrayList<>();
    }

    /**
     * Registers a new client
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
