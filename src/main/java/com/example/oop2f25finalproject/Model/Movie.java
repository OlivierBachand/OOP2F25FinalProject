package com.example.oop2f25finalproject.Model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a movie with a title, genre, duration, and associated showtimes.
 * <p>
 * This class stores basic information about a movie and maintains a static
 * collection of all movie instances for global access. The movie duration is
 * stored as a {@link Time} object formatted as <code>HH:mm:ss</code>.
 * </p>
 *
 * @author Olivier Bachand
 */
public class Movie {
    /** A static list containing all Movie instances created in the application. */
    public static List<Movie> movieList = new ArrayList<>();

    /** The title of the movie. */
    private String aTitle;

    /** The genre of the movie (e.g., Action, Comedy, Drama). */
    private String aGenre;

    /** The length of the movie, stored as a SQL Time object (HH:mm:ss). */
    private Time aLength;

    /** A list of all showtimes associated with this movie. */
    private final List<ShowTime> aShowTimes = new ArrayList<>();

    /**
     * Constructs a new Movie with the specified title, genre, and duration.
     *
     * @param pTitle  the movie's title (must not be null or empty)
     * @param pGenre  the movie's genre (must not be null or empty)
     * @param pLength the movie length as a string in <code>HH:mm:ss</code> format
     * @throws ParseException if the provided length string is not in the expected format
     * @throws IllegalArgumentException if title or genre are invalid
     */
    public Movie(String pTitle, String pGenre, String pLength) throws ParseException {
        this.setTitle(pTitle);
        this.setGenre(pGenre);
        this.setLength(pLength);
        Movie.addMovie(this);
    }

    /**
     * Adds a movie to the static movie list.
     *
     * @param pMovie the movie to add
     */
    private static void addMovie(Movie pMovie) {
        Movie.movieList.add(pMovie);
    }

    /**
     * Returns the movie stored at a specific index in the global movie list.
     *
     * @param pIndex index of the desired movie
     * @return the movie at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public static Movie getMovie(int pIndex) {
        return Movie.movieList.get(pIndex);
    }

    /**
     * Removes the specified movie from the global movie list.
     *
     * @param pIndex the index of the movie to remove
     */
    public static void removeMovie(int pIndex) {
        Movie.movieList.remove(pIndex);
    }

    /**
     * Returns a list of showtimes for this movie as formatted strings.
     * Each entry represents a ShowTime object's string representation.
     *
     * @return a list of showtime descriptions
     */
    public ShowTime getShowTime(int pIndex) {
        return this.aShowTimes.get(pIndex);
    }

    public int getShowTimesSize() {
        return this.aShowTimes.size();
    }

    /**
     * Removes a specific showtime from this movie based on its index in the list.
     * <p>
     * This method is typically used when a scheduled showtime needs to be cancelled
     * or modified. If the index is valid, the corresponding {@code ShowTime}
     * object is removed from the movie's internal list of showtimes.
     * </p>
     *
     * @param pIndex the position of the showtime to remove in the list
     * @throws IndexOutOfBoundsException if the index does not exist in the list,
     *                                   ensuring that accidental invalid removals are caught
     */
    public void deleteShowTime(int pIndex) {
        this.aShowTimes.remove(pIndex);
    }

    /**
     * Adds a new showtime to this movie's list of scheduled showtimes.
     * <p>
     * This method is used when creating a new showtime for a movie,
     * allowing the system to track when and where the movie will be shown.
     * The method does not perform duplicate checks, so it assumes that the caller
     * ensures the provided {@code ShowTime} is valid and appropriate for the schedule.
     * </p>
     *
     * @param pShowTime the showtime to associate with this movie
     * @throws NullPointerException if the provided showtime is {@code null},
     *                              preventing insertion of invalid data
     */
    public void addShowTime(ShowTime pShowTime) {
        this.aShowTimes.add(pShowTime);
    }

    /**
     * Returns the genre of the movie.
     *
     * @return the movie's genre
     */
    public String getaGenre() {
        return aGenre;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the movie's title
     */
    public String getTitle() {
        return aTitle;
    }

    /**
     * Returns the duration of the movie.
     *
     * @return the movie's length as a {@link Time} object
     */
    public Time getLength() {
        return this.aLength;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param pGenre the genre to assign
     * @throws IllegalArgumentException if the genre is null or empty
     */
    public void setGenre(String pGenre) {
        if (pGenre != null && !pGenre.trim().isEmpty()) {
            this.aGenre = pGenre;
        } else {
            throw new IllegalArgumentException("Genre cannot be empty");
        }
    }

    /**
     * Sets the title of the movie.
     *
     * @param aTitle the new title
     * @throws IllegalArgumentException if the title is null or empty
     */
    public void setTitle(String aTitle) {
        if (aTitle != null && !aTitle.trim().isEmpty()) {
            this.aTitle = aTitle;
        } else {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    /**
     * Sets the movie length using a string formatted as <code>HH:mm:ss</code>.
     *
     * @param pLength the duration string to parse
     * @throws ParseException if the string does not match <code>HH:mm:ss</code>
     */
    public void setLength(String pLength) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = sdf.parse(pLength);
        aLength = new Time(date.getTime());
    }
}
