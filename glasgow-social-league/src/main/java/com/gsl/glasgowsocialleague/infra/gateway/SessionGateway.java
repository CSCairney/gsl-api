package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionGateway extends JpaRepository<Session, Integer> {
    List<Session> findBySportId(Integer sportId);
    List<Session> findBySeasonId(Integer seasonId);
}
