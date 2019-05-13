package com.example.films.core.repository;

import com.example.films.core.entity.FilmEntity;
import com.example.films.port.dto.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @BeforeEach
    void reset() {
        filmRepository.deleteAll();
    }

    @Test
    @DisplayName("insert and retrieve films")
    void insertAndRetrieveFilms() {
        FilmEntity expectedFilm = new FilmEntity(null, LocalDate.now(), "film title", BigDecimal.TEN, "director", Genre.ACTION, false);

        filmRepository.save(expectedFilm);

        assertThat(filmRepository.findAll().size()).isEqualTo(1);
        FilmEntity actualFilm = filmRepository.findAll().get(0);
        assertFilms(actualFilm, expectedFilm);
    }

    @Test
    @DisplayName("delete films")
    void deleteFilms() {
        insertFilm(null, LocalDate.now(), "film title", BigDecimal.TEN, "director", Genre.ACTION, false);
        assertThat(filmRepository.findAll().size()).isEqualTo(1);

        filmRepository.deleteAll();

        assertThat(filmRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("update film")
    void updateFilm() {
        FilmEntity expectedFilm = insertFilm(null, LocalDate.now(), "film title", BigDecimal.TEN, "director", Genre.ACTION, false);
        assertThat(filmRepository.findAll().size()).isEqualTo(1);

        expectedFilm.setTitle("title 2");
        expectedFilm.setImdbRating(BigDecimal.ONE);
        expectedFilm.setDirector("director 2");
        expectedFilm.setGenre(Genre.MARTIAL_ARTS);
        expectedFilm.setAwardWinning(true);
        filmRepository.save(expectedFilm);

        assertThat(filmRepository.findAll().size()).isEqualTo(1);
        FilmEntity actualFilm = filmRepository.findAll().get(0);
        assertFilms(actualFilm, expectedFilm);
    }

    private FilmEntity insertFilm(Long id, LocalDate releaseDate, String title, BigDecimal imdbRating,
                                  String director, Genre genre, boolean isAwardWinning) {
        return filmRepository.save(new FilmEntity(id, releaseDate, title,
                        imdbRating, director, genre, isAwardWinning
                )
        );
    }

    private void assertFilms(FilmEntity actualFilm, FilmEntity expectedFilm) {
        assertThat(actualFilm.getTitle()).isEqualTo(expectedFilm.getTitle());
        assertThat(actualFilm.getReleaseDate()).isEqualTo(expectedFilm.getReleaseDate());
        // BigDecimal actual contains decimal point, but expected doesn't
        assertThat(actualFilm.getImdbRating().intValue()).isEqualTo(expectedFilm.getImdbRating().intValue());
        assertThat(actualFilm.getDirector()).isEqualTo(expectedFilm.getDirector());
        assertThat(actualFilm.getGenre()).isEqualTo(expectedFilm.getGenre());
        assertThat(actualFilm.isAwardWinning()).isEqualTo(expectedFilm.isAwardWinning());
    }
}
