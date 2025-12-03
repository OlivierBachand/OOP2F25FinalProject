package com.example.oop2f25finalproject.Model;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random; // New import

/**
 * Initializes test data for the movie theatre application.
 * Creates sample rooms, movies, and showtimes for testing purposes.
 *
 * @author Test Data Generator
 */
public class TestDataInitializer {

    /**
     * Initializes all test data.
     * Call this method once at application startup.
     */
    public static void initializeTestData() {
        System.out.println("Initializing test data...");

        try {
            createRooms();
            createMoviesAndShowtimes();
            createSampleSales(); // FIX: Call to create sample tickets
            System.out.println("Test data initialization complete!");
            System.out.println("- Rooms created: " + Room.roomList.size());
            System.out.println("- Movies created: " + Movie.movieList.size());
        } catch (Exception e) {
            System.err.println("Error initializing test data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates sample rooms with different capacities.
     */
    private static void createRooms() {
        // Create 5 different rooms
        new Room(150, "1");
        new Room(200, "2");
        new Room(100, "3");
        new Room(250, "4");
        new Room(120, "5");

        System.out.println("Created " + Room.roomList.size() + " rooms");
    }

    /**
     * Creates sample movies with multiple showtimes.
     */
    private static void createMoviesAndShowtimes() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Movie 1: The Matrix
        Movie matrix = new Movie("The Matrix", "Sci-Fi", "02:16:00");
        matrix.addShowTime(createShowTime("15/12/2024 14:00", 0));
        matrix.addShowTime(createShowTime("15/12/2024 17:30", 0));
        matrix.addShowTime(createShowTime("15/12/2024 21:00", 1));
        matrix.addShowTime(createShowTime("16/12/2024 19:00", 0));

        // Movie 2: Inception
        Movie inception = new Movie("Inception", "Sci-Fi", "02:28:00");
        inception.addShowTime(createShowTime("15/12/2024 13:00", 1));
        inception.addShowTime(createShowTime("15/12/2024 16:30", 2));
        inception.addShowTime(createShowTime("15/12/2024 20:00", 1));
        inception.addShowTime(createShowTime("16/12/2024 18:00", 2));

        // Movie 3: Avatar
        Movie avatar = new Movie("Avatar", "Action", "02:42:00");
        avatar.addShowTime(createShowTime("15/12/2024 12:00", 3));
        avatar.addShowTime(createShowTime("15/12/2024 15:30", 3));
        avatar.addShowTime(createShowTime("15/12/2024 19:00", 4));
        avatar.addShowTime(createShowTime("16/12/2024 20:00", 3));

        // Movie 4: Interstellar
        Movie interstellar = new Movie("Interstellar", "Sci-Fi", "02:49:00");
        interstellar.addShowTime(createShowTime("15/12/2024 11:00", 2));
        interstellar.addShowTime(createShowTime("15/12/2024 18:00", 2));
        interstellar.addShowTime(createShowTime("16/12/2024 21:00", 4));

        // Movie 5: Pulp Fiction
        Movie pulpFiction = new Movie("Pulp Fiction", "Crime", "02:34:00");
        pulpFiction.addShowTime(createShowTime("15/12/2024 14:30", 2));
        pulpFiction.addShowTime(createShowTime("15/12/2024 20:30", 2));
        pulpFiction.addShowTime(createShowTime("16/12/2024 16:00", 0));

        System.out.println("Created " + Movie.movieList.size() + " movies with showtimes");
    }

    /**
     * Helper method to create a ShowTime object.
     *
     * @param dateTimeStr Date and time in format "dd/MM/yyyy HH:mm"
     * @param roomIndex Index of the room (0-4)
     * @return A new ShowTime object
     */
    private static ShowTime createShowTime(String dateTimeStr, int roomIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        Room room = Room.roomList.get(roomIndex);
        return new ShowTime(dateTime, room);
    }

    /**
     * SAMPLE
     * Creates sample tickets for various showtimes to simulate sales.
     * This provides data for the manager's ticket sales views.
     */
    private static void createSampleSales() {
        Random rand = new Random();
        int totalTickets = 0;
        int dummyClientId = 1; // Required by Ticket constructor
        double dummyTicketPrice = 12.50; // Required by Ticket constructor

        // Iterate through all movies and their showtimes
        for (Movie movie : Movie.movieList) {
            for (int i = 0; i < movie.getShowTimesSize(); i++) {
                ShowTime showTime = movie.getShowTime(i);

                // Determine a random number of tickets sold for this showtime (5 to 50)
                int ticketsToSell = 5 + rand.nextInt(46);
                totalTickets += ticketsToSell;

                // Simulate creating and adding tickets to the showtime
                for (int j = 0; j < ticketsToSell; j++) {
                    // Correctly instantiates Ticket with all required parameters
                    showTime.addTicket(new Ticket(dummyClientId, showTime, dummyTicketPrice));
                }
            }
        }
        System.out.println("Created " + totalTickets + " sample ticket sales.");
    }
}