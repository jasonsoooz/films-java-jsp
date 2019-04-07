package com.example.films;

import com.example.films.auth.login.UserDTO;
import com.example.films.port.provides.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class DemoFilmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFilmsApplication.class, args);
	}

	@Resource
	UserService userService;

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;

	@PostConstruct
	private void createDefaultUser() {
		userService.saveUser(new UserDTO(username, password));
	}
}