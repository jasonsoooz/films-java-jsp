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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static com.example.films.port.dto.Genre.ACTION;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Not needed in JSP web app, but retained as example of rest controller & controller tests
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private Gson gson;

    @Test
    @WithMockUser
    @DisplayName("should get films")
    void shouldGetFilms() throws Exception {
        List<FilmDTO> films = new ArrayList<>();
        films.add(new FilmDTO(1, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION, false));
        when(filmService.getFilms()).thenReturn(films);

        this.mockMvc.perform(get("/films2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(containsString(gson.toJson(films))));

        verify(filmService).getFilms();
    }

    // curl -d '{"year":2004,"title":"Spiderman 2","imdbRating":7.3,"director":"Sam Raimi"}' -H "Content-Type: application/json" -X POST http://localhost:8018/demo/films
    @Test
    @WithMockUser
    @DisplayName("should add film")
    void shouldAddFilm() throws Exception {
        FilmDTO film = new FilmDTO(1, "2002-04-29", "Spiderman", 7.3f, "Sam Raimi", ACTION, false);

        this.mockMvc.perform(post("/films2/")
                .content(gson.toJson(film))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(filmService).saveFilm(film);
    }

    // curl -d '{"year":2004,"title":"Spiderman 2","imdbRating":7.3,"director":"Sam Raimi"}' -H "Content-Type: application/json" -X DELETE http://localhost:8018/demo/films/Spiderman%202
    @Test
    @WithMockUser
    void shouldDeleteFilm() throws Exception {
        int filmId = 1;
        FilmDTO film = new FilmDTO(filmId,"2004-06-22", "Spiderman 2", 7.3f, "Sam Raimi", ACTION, false);

        this.mockMvc.perform(delete("/films2/" + filmId)
                .content(gson.toJson(film))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(filmService).deleteFilm(filmId);
    }
}