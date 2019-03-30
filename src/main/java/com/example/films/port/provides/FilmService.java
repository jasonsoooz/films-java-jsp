package com.example.films.port.provides;

import com.example.films.port.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getFilms();
    FilmDTO find(int id);
    void saveFilm(FilmDTO filmDTO);
    void deleteFilm(int id);
    void clear();
}