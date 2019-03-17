package com.example.films.adapter.controller;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FilmController {

    @Resource
    private FilmService filmService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<FilmDTO> getFilms() {
        return filmService.getFilms();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addFilm(@RequestBody FilmDTO film) {
        filmService.saveFilm(film);
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFilm(@PathVariable String title, @RequestBody FilmDTO film) {
        filmService.deleteFilm(film);
    }
}