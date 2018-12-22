package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private List<FilmDTO> films;

    public FilmServiceImpl() {
        this.films = new ArrayList<>();
    }

    @Override
    public List<FilmDTO> getFilms() {
        return films;
    }

    @Override
    public void saveFilm(FilmDTO filmDTO) {
        films.add(filmDTO);
    }

    @Override
    public void deleteFilm(FilmDTO filmDTO) {
        films.remove(filmDTO);
    }
}