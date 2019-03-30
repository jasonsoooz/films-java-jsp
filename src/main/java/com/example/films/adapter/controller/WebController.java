package com.example.films.adapter.controller;


import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public String displayFilmToSave(Model model) {
        if (!model.containsAttribute("film")) {
            model.addAttribute("film", new FilmDTO());
        }
        return "addFilm";
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public String addFilm(@ModelAttribute("film") @Valid FilmDTO film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("film", film);
            return "addFilm";
        }

        filmService.saveFilm(film);
        return "redirect:films";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET)
    public String updateFilm(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        FilmDTO found = filmService.find(id.intValue());
        redirectAttributes.addFlashAttribute("film", found);
        return "redirect:/film";
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.POST)
    public String deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id.intValue());
        return "redirect:/films";
    }
}
