package com.example.films.adapter.controller;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Not needed in JSP web app, but retained as example of rest controller & controller tests
 */

@RestController
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/films2", method = RequestMethod.GET)
    public List<FilmDTO> getFilms() {
        return filmService.getFilms();
    }

    @RequestMapping(value = "/films2", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addFilm(@RequestBody FilmDTO film) {
        filmService.saveFilm(film);
    }

    @RequestMapping(value = "/films2/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id.intValue());
    }
}