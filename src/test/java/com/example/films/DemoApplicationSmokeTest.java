package com.example.films;

import com.example.films.adapter.controller.FilmController;
import com.example.films.port.provides.FilmService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationSmokeTest {

	@Autowired
	private FilmService filmService;

	@Autowired
	private FilmController filmController;

	@Test
	@DisplayName("service should load")
	void contextServiceLoads() {
		assertThat(filmService).isNotNull();
	}

	@Test
	@DisplayName("controller should load")
	void contextControllerLoads() {
		assertThat(filmController).isNotNull();
	}
}