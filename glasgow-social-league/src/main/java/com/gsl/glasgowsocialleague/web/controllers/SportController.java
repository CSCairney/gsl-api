package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.core.service.SportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sports")
@Slf4j
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<Sport> getAllSports() {
        log.info("Fetching all sports");
        return sportService.getAllSports();
    }

    @GetMapping("/{id}")
    public Optional<Sport> getSportById(@PathVariable Integer id) {
        log.info("Fetching sport with ID: {}", id);
        return sportService.getSportById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sport createSport(@RequestBody Sport sport) {
        log.info("Creating a new sport: {}", sport);
        return sportService.createSport(sport);
    }

    @PutMapping("/{id}")
    public Sport updateSport(@PathVariable Integer id, @RequestBody Sport sport) {
        log.info("Updating sport with ID: {} with data: {}", id, sport);
        return sportService.updateSport(id, sport);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSport(@PathVariable Integer id) {
        log.info("Deleting sport with ID: {}", id);
        sportService.deleteSport(id);
    }
}
