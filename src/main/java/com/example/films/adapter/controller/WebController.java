package com.example.films.adapter.controller;


import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WebController {

    @Resource
    private FilmService filmService;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public String films(Model model) {
        List<FilmDTO> films = filmService.getFilms();
        model.addAttribute("films", films);
        return "films";
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("film", new FilmDTO());
        return "addFilm";
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute FilmDTO film) {
        filmService.saveFilm(film);
        return "redirect:/films";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.POST)
    public String deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id.intValue());
        return "redirect:/films";
    }
}
