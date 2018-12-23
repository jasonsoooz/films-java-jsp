package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FilmServiceImplTest {

    @Autowired
    private FilmService filmService;

    @Test
    @DisplayName("should insert and get films")
    void shouldInsertAndGetFilms() {
        FilmDTO film = new FilmDTO(2002, "Spiderman", 7.3f, "Sam Raimi");

        filmService.saveFilm(film);

        List<FilmDTO> films = filmService.getFilms();
        assertThat(films.size()).isEqualTo(1);
        FilmDTO actualFilm = films.get(0);
        System.out.println("actual film: " + actualFilm);
        assertThat(films).contains(film);
    }

    @Test
    @DisplayName("should delete film")
    void shouldDeleteFilm() {
        FilmDTO film = new FilmDTO(2002, "Spiderman", 7.3f, "Sam Raimi");

        filmService.saveFilm(film);
        assertThat(filmService.getFilms().size()).isEqualTo(1);

        filmService.deleteFilm(film);

        assertThat(filmService.getFilms().size()).isEqualTo(0);
    }
}