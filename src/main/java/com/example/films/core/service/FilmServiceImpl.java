package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

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
        filmDTO.setId(getNextId(filmDTO.getId()));
        films.add(filmDTO);
    }

    @Override
    public void deleteFilm(int id) {
        films.remove(find(id));
    }

    @Override
    public void clear() {
        films.clear();
    }

    private FilmDTO find(int id) {
        return films.stream().filter(film -> film.getId() == id)
                .findFirst()
                .<IllegalArgumentException>orElseThrow(() -> {
                    throw new IllegalArgumentException(format("film id: %s not found", id));
                });
    }

    private int getNextId(int id) {
        if (id > 0) return id;

        return films.size() + 1;
    }
}