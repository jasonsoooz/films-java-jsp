package com.example.films.port.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class FilmDTO {
    private int id;
    private int year;
    private String title;
    private float imdbRating;
    private String director;

    public FilmDTO() {}

    public FilmDTO(int id, int year, String title, float imdbRating, String director) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.imdbRating = imdbRating;
        this.director = director;
    }

    public static FilmDTO of(FilmDTO film) {
        return new FilmDTO(film.getId(),
                film.getYear(),
                film.getTitle(),
                film.getImdbRating(),
                film.getDirector());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}