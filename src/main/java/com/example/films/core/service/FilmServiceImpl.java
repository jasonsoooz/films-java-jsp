package com.example.films.core.service;

import com.example.films.core.entity.FilmEntity;
import com.example.films.core.repository.FilmRepository;
import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<FilmDTO> getFilms() {
        return filmRepository.findAll().stream()
                .map(FilmDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO saveFilm(FilmDTO filmDTO) {
        // copy element so changes of element don't affect list
        FilmDTO copy = FilmDTO.of(filmDTO);

        return FilmDTO.of(filmRepository.save(FilmEntity.of(copy)));
    }

    @Override
    public void deleteFilm(long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public void clear() {
        filmRepository.deleteAll();
    }

    @Override
    public FilmDTO find(long id) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> {
                            throw new IllegalArgumentException(format("film id: %s not found", id));
                        }
                );

        return FilmDTO.of(filmEntity);
    }
}