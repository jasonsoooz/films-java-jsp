package com.example.films.port.provides;

import com.example.films.port.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getFilms();
    void saveFilm(FilmDTO filmDTO);
    void deleteFilm(FilmDTO filmDTO);
}