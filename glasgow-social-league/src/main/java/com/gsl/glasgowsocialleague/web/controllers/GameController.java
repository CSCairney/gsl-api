package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.service.GameService;
import com.gsl.glasgowsocialleague.web.dto.GameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Slf4j
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDto> getAllGames() {
        log.info("Fetching all games");
        return gameService.getAllGames();
    }

    @PostMapping
    public void createGame(@RequestBody GameDto gameDto) {
        log.info("Creating a new game with details: {}", gameDto);
        gameService.createGame(gameDto);
    }

    @GetMapping("/{id}")
    public GameDto getGame(@PathVariable Long id) {
        log.info("Fetching game with ID: {}", id);
        return gameService.getGameById(id);
    }

    @PutMapping("/{id}")
    public void updateGame(@PathVariable Long id, @RequestBody GameDto gameDto) {
        log.info("Updating game with ID: {} with new details: {}", id, gameDto);
        gameService.updateGame(id, gameDto);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        log.info("Deleting game with ID: {}", id);
        gameService.deleteGame(id);
    }
}
