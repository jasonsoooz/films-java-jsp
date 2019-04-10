package com.example.films;

import com.example.films.auth.login.UserDTO;
import com.example.films.port.provides.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataLoader implements CommandLineRunner {

    private UserService userService;

    public DefaultDataLoader(UserService userService) {
        this.userService = userService;
    }

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        // default user
        userService.saveUser(new UserDTO(username, password));
    }
}
