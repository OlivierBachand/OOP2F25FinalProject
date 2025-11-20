package com.example.oop2f25finalproject.Model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a movie with a title, genre, length, and associated showtimes.
 */
public class Movie {

    /** The title of the movie. */
    private String aTitle;

    /** The genre of the movie (e.g., Action, Comedy). */
    private String aGenre;

    /** The length of the movie, stored as a SQL Time object (HH:mm:ss). */
    private Time aLength;

    /** A list of showtimes for the movie. */
    private final List<ShowTime> aShowTimes = new ArrayList<>();

    /**
     * Constructor of the Movie class.
     * @param pTitle Title of the movie.
     * @param pGenre Genre of the movie.
     * @param pLength length of the movie.
     * @throws ParseException when the format is wrong.
     */
    public Movie(String pTitle, String pGenre, String pLength) throws ParseException {
        this.setTitle(pTitle);
        this.setGenre(pGenre);
        this.setLength(pLength);
    }

    /**
     * Returns the genre of the movie.
     *
     * @return the movie genre
     */
    public String getaGenre() {
        return aGenre;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the movie title
     */
    public String getTitle() {
        return aTitle;
    }

    /**
     * Returns the length of the movie.
     *
     * @return the movie duration as a Time object
     */
    public Time getLength() {
        return aLength;
    }

    /**
     * Sets the movie genre.
     *
     * @param pGenre the genre to assign
     * @throws IllegalArgumentException if the genre is empty or null
     */
    public void setGenre(String pGenre) {
        if (pGenre != null && !pGenre.isEmpty()) {
            this.aGenre = pGenre;
        }
        else {
            throw new IllegalArgumentException("Genre cannot be empty");
        }
    }

    /**
     * Sets the movie title.
     *
     * @param aTitle the new title
     * @throws IllegalArgumentException if the title is empty or null
     */
    public void setTitle(String aTitle) {
        if (aTitle != null && !aTitle.isEmpty()) {
            this.aTitle = aTitle;
        }
        else {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    /**
     * Sets the movie length using a string format (HH:mm:ss).
     *
     * @param pLength a string containing the movie length
     * @throws ParseException if the string is not in HH:mm:ss format
     */
    public void setLength(String pLength) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = sdf.parse(pLength);
        aLength = new Time(date.getTime());
    }
}

