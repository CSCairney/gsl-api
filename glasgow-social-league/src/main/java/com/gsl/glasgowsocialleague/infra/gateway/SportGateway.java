package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportGateway extends JpaRepository<Sport, Integer> {
    // You can add custom queries here if needed
}
