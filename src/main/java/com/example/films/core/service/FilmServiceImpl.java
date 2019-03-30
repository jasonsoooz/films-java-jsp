package com.example.films.core.service;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // copy element so changes of element don't affect list
        FilmDTO copy = FilmDTO.of(filmDTO);

        int index = findIndex(copy.getId());
        // not found
        if (index == -1) {
            // add
            copy.setId(getNextId(copy.getId()));
            films.add(copy);
        } else {
            // update
            films.set(index, copy);
        }
    }

    @Override
    public void deleteFilm(int id) {
        films.remove(find(id));
    }

    @Override
    public void clear() {
        films.clear();
    }

    @Override
    public FilmDTO find(int id) {
        return films.stream().filter(film -> film.getId() == id)
                .findFirst()
                .<IllegalArgumentException>orElseThrow(() -> {
                    throw new IllegalArgumentException(format("film id: %s not found", id));
                });
    }

    private int findIndex(int id) {
        List<Integer> ids = films.stream()
                .map(FilmDTO::getId)
                .collect(Collectors.toList());
        return ids.indexOf(id);
    }

    private int getNextId(int id) {
        if (id > 0) return id;

        return films.size() + 1;
    }
}