package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
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
        FilmDTO film = new FilmDTO(1, 2002, "Spiderman", 7.3f, "Sam Raimi");

        filmService.saveFilm(film);

        List<FilmDTO> films = filmService.getFilms();
        assertThat(films.size()).isEqualTo(1);
        assertThat(films).contains(film);
    }

    @Test
    @DisplayName("should delete film")
    void shouldDeleteFilm() {
        int filmId = 1;
        insertFilm(filmId, 2002, "Spiderman", 7.3f, "Sam Raimi");
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        filmService.deleteFilm(filmId);

        assertThat(filmService.getFilms().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should throw exception if deleting film not found")
    void shouldThrowExceptionIfDeletingFilmNotFound() {
        insertFilm(1, 2002, "Spiderman", 7.3f, "Sam Raimi");
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        int notFoundId = 0;
        assertThatThrownBy(() -> filmService.deleteFilm(notFoundId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format("film id: %s not found", notFoundId));
    }

    private void insertFilm(int id, int year, String title, float imdbRating, String director) {
        filmService.saveFilm(new FilmDTO(id, year, title, imdbRating, director));
    }
}