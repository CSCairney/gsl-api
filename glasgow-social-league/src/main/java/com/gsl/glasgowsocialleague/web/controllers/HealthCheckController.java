package com.gsl.glasgowsocialleague.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        log.info("Health check endpoint accessed");
        return ResponseEntity.ok("Application is running");
    }
}

