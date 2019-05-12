package com.example.films.port.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class FilmDTO {
    private int id;

    @NotEmpty(message = "Film release date must not be empty")
    private String releaseDateString;

    @NotEmpty(message = "Film title must not be empty")
    private String title;

    private float imdbRating;
    private String director;
    private Genre genre;

    public FilmDTO() {}

    public FilmDTO(int id, String releaseDateString, String title, float imdbRating, String director, Genre genre) {
        this.id = id;
        this.releaseDateString = releaseDateString;
        this.title = title;
        this.imdbRating = imdbRating;
        this.director = director;
        this.genre = genre;
    }

    public static FilmDTO of(FilmDTO film) {
        return new FilmDTO(film.getId(),
                film.getReleaseDateString(),
                film.getTitle(),
                film.getImdbRating(),
                film.getDirector(),
                film.getGenre()
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReleaseDateString() {
        return releaseDateString;
    }

    public void setReleaseDateString(String releaseDateString) {
        this.releaseDateString = releaseDateString;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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