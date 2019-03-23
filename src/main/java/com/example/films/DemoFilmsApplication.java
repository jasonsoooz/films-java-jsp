package com.example.films;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class DemoFilmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFilmsApplication.class, args);
	}
}