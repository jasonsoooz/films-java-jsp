package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.dto.Genre;
import com.example.films.port.provides.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.films.port.dto.Genre.ACTION;
import static com.example.films.port.dto.Genre.SCI_FICTION;
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
        FilmDTO film = new FilmDTO(1, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION, false);

        filmService.saveFilm(film);

        List<FilmDTO> films = filmService.getFilms();
        assertThat(films.size()).isEqualTo(1);
        assertThat(films).contains(film);
    }

    @Test
    @DisplayName("should update film")
    void shouldUpdateFilm() {
        FilmDTO film = new FilmDTO(1, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION,false);
        filmService.saveFilm(film);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        String updatedReleaseDateString = "2004-06-22";
        String updatedTitle = "Spiderman 2";
        Genre updatedGenre = SCI_FICTION;
        boolean updatedIsAwardWinning = true;
        film.setReleaseDateString(updatedReleaseDateString);
        film.setTitle(updatedTitle);
        film.setGenre(updatedGenre);
        film.setIsAwardWinning(updatedIsAwardWinning);
        filmService.saveFilm(film);

        assertThat(filmService.getFilms().size()).isEqualTo(1);
        FilmDTO updatedFilm = filmService.getFilms().get(0);
        assertThat(updatedFilm.getReleaseDateString()).isEqualTo(updatedReleaseDateString);
        assertThat(updatedFilm.getTitle()).isEqualTo(updatedTitle);
        assertThat(updatedFilm.getGenre()).isEqualTo(updatedGenre);
        assertThat(updatedFilm.getIsAwardWinning()).isEqualTo(updatedIsAwardWinning);
    }

    @Test
    @DisplayName("should delete film")
    void shouldDeleteFilm() {
        int filmId = 1;
        insertFilm(filmId, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION, false);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        filmService.deleteFilm(filmId);

        assertThat(filmService.getFilms().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should throw exception if deleting film not found")
    void shouldThrowExceptionIfDeletingFilmNotFound() {
        insertFilm(1, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION, false);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        int notFoundId = 0;
        assertThatThrownBy(() -> filmService.deleteFilm(notFoundId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format("film id: %s not found", notFoundId));
    }

    private void insertFilm(int id, String releaseDateString, String title, float imdbRating, String director, Genre genre, boolean isAwardWinning) {
        filmService.saveFilm(new FilmDTO(id, releaseDateString, title, imdbRating, director, genre, isAwardWinning));
    }
}