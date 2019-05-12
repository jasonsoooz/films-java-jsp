package com.example.films.core.entity;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.dto.Genre;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(name = "Film")
public class FilmEntity {

    private static DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private LocalDate releaseDate;
    private String title;
    private BigDecimal imdbRating;
    private String director;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private boolean isAwardWinning;

    // Required by JPA
    private FilmEntity() {
    }

    public FilmEntity(Long id, LocalDate releaseDate, String title, BigDecimal imdbRating, String director, Genre genre, boolean isAwardWinning) {
        if (id != null) {
            this.id = id;
        }
        this.timestamp = new Date();
        this.releaseDate = releaseDate;
        this.title = title;
        this.imdbRating = imdbRating;
        this.director = director;
        this.genre = genre;
        this.isAwardWinning = isAwardWinning;
    }

    public static FilmEntity of(FilmDTO filmDTO) {
        return new FilmEntity(filmDTO.getId(),
                LocalDate.parse(filmDTO.getReleaseDateString(), dateFormat),
                filmDTO.getTitle(),
                filmDTO.getImdbRating(),
                filmDTO.getDirector(),
                filmDTO.getGenre(),
                filmDTO.getIsAwardWinning()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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

    public boolean isAwardWinning() {
        return isAwardWinning;
    }

    public void setAwardWinning(boolean awardWinning) {
        isAwardWinning = awardWinning;
    }
}
