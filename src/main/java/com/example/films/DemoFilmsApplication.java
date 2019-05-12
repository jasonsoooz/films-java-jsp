package com.example.films;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import static java.lang.String.format;

@SpringBootApplication
@PropertySource("classpath:private.properties")
public class DemoFilmsApplication {

	private static String moreVisible = "*".repeat(25);

	public static void main(String[] args) {
		printCommandLineArgs(args);
		SpringApplication.run(DemoFilmsApplication.class, args);
	}

	private static void printCommandLineArgs(String[] args) {
		if (args.length <= 0) {
			System.out.println(format("%s No command line args %s", moreVisible, moreVisible));
		}

		for (String arg : args) {
			System.out.println(format("%s command line arg: %s %s", moreVisible, arg, moreVisible));
		}
	}
}