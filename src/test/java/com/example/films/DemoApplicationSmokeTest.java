package com.example.films;

import com.example.films.adapter.controller.FilmController;
import com.example.films.auth.login.LoginController;
import com.example.films.auth.login.UserRepository;
import com.example.films.auth.signup.SignupController;
import com.example.films.port.provides.FilmService;
import com.example.films.port.provides.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationSmokeTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmController filmController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private SignupController signupController;

    @Autowired
    private DemoFilmsApplication demoFilmsApplication;

    @Test
    @DisplayName("repository should load")
    void contextRepositoryLoads() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    @DisplayName("services should load")
    void contextServiceLoads() {
        assertThat(filmService).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("controllers should load")
    void contextControllerLoads() {
        assertThat(filmController).isNotNull();
        assertThat(loginController).isNotNull();
        assertThat(signupController).isNotNull();
    }

    @Test
    @DisplayName("application should load")
    void contextApplicationLoads() {
        assertThat(demoFilmsApplication).isNotNull();
    }
}