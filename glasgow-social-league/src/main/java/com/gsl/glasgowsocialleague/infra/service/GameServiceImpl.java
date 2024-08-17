//package com.gsl.glasgowsocialleague.infra.service;
//
//import com.gsl.glasgowsocialleague.core.mapper.GameMapper;
//import com.gsl.glasgowsocialleague.core.model.game.GameEntity;
//import com.gsl.glasgowsocialleague.core.service.GameService;
//import com.gsl.glasgowsocialleague.infra.gateway.GameGateway;
//import com.gsl.glasgowsocialleague.web.dto.GameDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class GameServiceImpl implements GameService {
//
//    private final GameGateway gameGateway;
//    private final GameMapper gameMapper = GameMapper.INSTANCE;
//
//    @Autowired
//    public GameServiceImpl(GameGateway gameGateway) {
//        this.gameGateway = gameGateway;
//    }
//
//    @Override
//    public List<GameDto> getAllGames() {
//        List<GameEntity> games = gameGateway.findAll();
//        return games.stream()
//                .map(gameMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public GameDto getGameById(Long id) {
//        Optional<GameEntity> gameEntity = gameGateway.findById(id);
//        return gameEntity.map(gameMapper::toDto).orElseThrow(() ->
//                new RuntimeException("Game not found with id: " + id));
//    }
//
//    @Override
//    public void createGame(GameDto gameDto) {
//        gameGateway.save(gameMapper.toEntity(gameDto));
//    }
//
//    @Override
//    public void updateGame(Long id, GameDto gameDto) {
//        if (!gameGateway.existsById(id)) {
//            throw new RuntimeException("Game not found with id: " + id);
//        }
//        gameDto.setId(id);
//        gameGateway.save(gameMapper.toEntity(gameDto));
//    }
//
//    @Override
//    public void deleteGame(Long id) {
//        if (!gameGateway.existsById(id)) {
//            throw new RuntimeException("Game not found with id: " + id);
//        }
//        gameGateway.deleteById(id);
//    }
//}
