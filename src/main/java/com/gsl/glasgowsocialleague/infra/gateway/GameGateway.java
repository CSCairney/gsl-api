package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.game.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameGateway extends JpaRepository<GameEntity, Long> {

    // Custom query methods can be defined here

    // Example: Find games by sport ID
    List<GameEntity> findBySportId(Long sportId);

    // Example: Find games by a specific date range
    List<GameEntity> findByDateBetween(java.time.LocalDate startDate, java.time.LocalDate endDate);
}
