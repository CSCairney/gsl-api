package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.web.dto.GameDto;

import java.util.List;

public interface GameService {
    List<GameDto> getAllGames();

    GameDto getGameById(Long id);

    void createGame(GameDto gameDto);

    void updateGame(Long id, GameDto gameDto);

    void deleteGame(Long id);
}
