package com.example.films.port.provides;

import com.example.films.port.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getFilms();
    FilmDTO find(long id);
    FilmDTO saveFilm(FilmDTO filmDTO);
    void deleteFilm(long id);
    void clear();
}