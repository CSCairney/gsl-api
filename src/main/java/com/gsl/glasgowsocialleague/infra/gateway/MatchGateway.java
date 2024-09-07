package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchGateway extends JpaRepository<Match, Integer> {
    List<Match> findBySessionId(Integer sessionId);

    @Query(value = "SELECT * FROM matches WHERE sport_id = :sportId LIMIT :amount", nativeQuery = true)
    List<Match> findBySportIdWithLimit(@Param("sportId") Integer sportId, @Param("amount") Integer amount);

    @Query(value = "SELECT * FROM matches WHERE sport_id = :sportId", nativeQuery = true)
    List<Match> findBySportId(@Param("sportId") Integer sportId);
}
