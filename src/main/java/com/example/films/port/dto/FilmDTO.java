package com.example.films.port.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class FilmDTO {
    private long id;

    @NotEmpty(message = "Film release date must not be empty")
    private String releaseDateString;

    @NotEmpty(message = "Film title must not be empty")
    private String title;

    private BigDecimal imdbRating;
    private String director;
    private Genre genre;
    private boolean isAwardWinning;

    public FilmDTO() {}

    public FilmDTO(long id, String releaseDateString, String title, BigDecimal imdbRating, String director, Genre genre, boolean isAwardWinning) {
        this.id = id;
        this.releaseDateString = releaseDateString;
        this.title = title;
        this.imdbRating = imdbRating;
        this.director = director;
        this.genre = genre;
        this.isAwardWinning = isAwardWinning;
    }

    public static FilmDTO of(FilmDTO film) {
        return new FilmDTO(film.getId(),
                film.getReleaseDateString(),
                film.getTitle(),
                film.getImdbRating(),
                film.getDirector(),
                film.getGenre(),
                film.getIsAwardWinning()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public BigDecimal getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(BigDecimal imdbRating) {
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

    public boolean getIsAwardWinning() {
        return isAwardWinning;
    }

    public void setIsAwardWinning(boolean isAwardWinning) {
        this.isAwardWinning = isAwardWinning;
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