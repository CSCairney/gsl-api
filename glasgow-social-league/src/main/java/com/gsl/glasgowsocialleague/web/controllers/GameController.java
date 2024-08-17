package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.service.GameService;
import com.gsl.glasgowsocialleague.web.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    public void createGame(@RequestBody GameDto gameDto) {
        gameService.createGame(gameDto);
    }

    @GetMapping("/{id}")
    public GameDto getGame(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    @PutMapping("/{id}")
    public void updateGame(@PathVariable Long id, @RequestBody GameDto gameDto) {
        gameService.updateGame(id, gameDto);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
