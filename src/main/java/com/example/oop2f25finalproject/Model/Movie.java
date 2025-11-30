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
        return aLength;
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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = sdf.parse(pLength);
        aLength = new Time(date.getTime());
    }

    @Override
    public String toString() {
        return this.aTitle + "\t\t" + this.aGenre + "\t\t" + this.aLength;
    }
}
