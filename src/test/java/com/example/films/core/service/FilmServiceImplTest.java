package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.dto.Genre;
import com.example.films.port.provides.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static com.example.films.port.dto.Genre.ACTION;
import static com.example.films.port.dto.Genre.SCI_FICTION;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class FilmServiceImplTest {

    @Autowired
    private FilmService filmService;

    @BeforeEach
    void setup() {
        filmService.clear();
    }

    @Test
    @DisplayName("should insert and get films")
    void shouldInsertAndGetFilms() {
        FilmDTO expectedFilm = new FilmDTO(1, "2002-04-29", "Spiderman", BigDecimal.valueOf(7.3), "Sam Raimi", ACTION, false);

        filmService.saveFilm(expectedFilm);

        List<FilmDTO> films = filmService.getFilms();
        assertThat(films.size()).isEqualTo(1);
        FilmDTO actualFilm = films.get(0);
        assertFilm(actualFilm, expectedFilm);
    }

    @Test
    @DisplayName("should update film")
    void shouldUpdateFilm() {
        FilmDTO expectedFilm = insertFilm(1, "2002-04-29", "Spiderman", BigDecimal.valueOf(7.3), "Sam Raimi", ACTION,false);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        expectedFilm.setReleaseDateString("2004-06-22");
        expectedFilm.setTitle("Spiderman 2");
        expectedFilm.setGenre(SCI_FICTION);
        expectedFilm.setIsAwardWinning(true);
        filmService.saveFilm(expectedFilm);

        assertThat(filmService.getFilms().size()).isEqualTo(1);
        FilmDTO actualFilm = filmService.getFilms().get(0);
        assertFilm(actualFilm, expectedFilm);
    }

    @Test
    @DisplayName("should delete film")
    void shouldDeleteFilm() {
        FilmDTO expectedFilm = insertFilm(1, "2002-04-29", "Spiderman", BigDecimal.valueOf(7.3), "Sam Raimi", ACTION, false);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        filmService.deleteFilm(expectedFilm.getId());

        assertThat(filmService.getFilms().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should throw exception if deleting film not found")
    void shouldThrowExceptionIfDeletingFilmNotFound() {
        int notFoundId = -1;

        assertThatThrownBy(() -> filmService.deleteFilm(notFoundId))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessageStartingWith("No")
                .hasMessageContaining(format("entity with id %s exists", notFoundId));
    }

    private FilmDTO insertFilm(long id, String releaseDateString, String title, BigDecimal imdbRating,
                               String director, Genre genre, boolean isAwardWinning) {
        FilmDTO film = new FilmDTO(id, releaseDateString, title, imdbRating, director, genre, isAwardWinning);

        return filmService.saveFilm(film);
    }

    private void assertFilm(FilmDTO actual, FilmDTO expected) {
        assertThat(actual.getReleaseDateString()).isEqualTo(expected.getReleaseDateString());
        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        // actual has decimal place & expected doesn't
        assertThat(actual.getImdbRating().intValue()).isEqualTo(expected.getImdbRating().intValue());
        assertThat(actual.getDirector()).isEqualTo(expected.getDirector());
        assertThat(actual.getGenre()).isEqualTo(expected.getGenre());
        assertThat(actual.getIsAwardWinning()).isEqualTo(expected.getIsAwardWinning());
    }
}