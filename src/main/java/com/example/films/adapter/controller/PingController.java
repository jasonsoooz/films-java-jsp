package com.example.films.adapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PingController {

    @GetMapping({"/ping"})
    public String ping() {
        return String.format("Health Check Ping Service: %s", new Date());
    }
}
