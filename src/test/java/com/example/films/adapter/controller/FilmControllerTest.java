package com.example.films.adapter.controller;

import com.example.films.port.dto.FilmDTO;
import com.example.films.port.provides.FilmService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("should get films")
    void shouldGetFilms() throws Exception {
        List<FilmDTO> films = new ArrayList<>();
        films.add(new FilmDTO(2002, "Spiderman", 7.3f, "Sam Raimi"));
        when(filmService.getFilms()).thenReturn(films);

        this.mockMvc.perform(get("/films")
                .contextPath("/films")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(containsString(gson.toJson(films))));
    }

    // curl -d '{"year":2004,"title":"Spiderman 2","imdbRating":7.3,"director":"Sam Raimi"}' -H "Content-Type: application/json" -X POST http://localhost:8015/films/
    @Test
    @DisplayName("should add film")
    void shouldAddFilm() throws Exception {
        FilmDTO film = new FilmDTO(2002, "Spiderman", 7.3f, "Sam Raimi");

        this.mockMvc.perform(post("/films/")
                .contextPath("/films")
                .content(gson.toJson(film))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(filmService).saveFilm(film);
    }

    // curl -d '{"year":2004,"title":"Spiderman 2","imdbRating":7.3,"director":"Sam Raimi"}' -H "Content-Type: application/json" -X DELETE http://localhost:8015/films/Spiderman%202
    @Test
    void shouldDeleteFilm() throws Exception {
        FilmDTO film = new FilmDTO(2004, "Spiderman 2", 7.3f, "Sam Raimi");

        this.mockMvc.perform(delete("/films/Spiderman%202")
                .contextPath("/films")
                .content(gson.toJson(film))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(filmService).deleteFilm(film);
    }
}