package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.match.Match;
import com.gsl.glasgowsocialleague.core.service.MatchService;
import com.gsl.glasgowsocialleague.infra.gateway.MatchGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MatchServiceImpl implements MatchService {

    private final MatchGateway matchGateway;

    @Autowired
    public MatchServiceImpl(MatchGateway matchGateway) {
        this.matchGateway = matchGateway;
    }

    @Override
    public List<Match> getAllMatches(Integer sportId, Integer amount) {
        log.info("Fetching matches with sportId: {} and amount: {}", sportId, amount);

        if (sportId != null && amount != null && amount > 0) {
            log.info("Fetching with sportId: {} and amount: {}", sportId, amount);
            return matchGateway.findBySportIdWithLimit(sportId, amount);
        } else if (sportId != null) {
            log.info("Fetching with amount: {}", amount);
            return matchGateway.findBySportId(sportId);
        } else {
            log.info("Fetching all matches");
            return matchGateway.findAll();
        }
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        log.info("Fetching match with ID: {}", id);
        return matchGateway.findById(id);
    }

    @Override
    public Match createMatch(Match match) {
        log.info("Creating new match: {}", match);
        return matchGateway.save(match);
    }

    @Override
    public Match updateMatch(Integer id, Match matchDetails) {
        log.info("Updating match with ID: {}", id);
        Match match = matchGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Match not found with ID: {}", id);
                    return new RuntimeException("Match not found with id " + id);
                });

        match.setPlayerOne(matchDetails.getPlayerOne());
        match.setPlayerTwo(matchDetails.getPlayerTwo());
        match.setScorePlayerOne(matchDetails.getScorePlayerOne());
        match.setScorePlayerTwo(matchDetails.getScorePlayerTwo());
        match.setWinner(matchDetails.getWinner());
        match.setDetails(matchDetails.getDetails());
        match.setCreatedBy(matchDetails.getCreatedBy());
        match.setLastUpdatedBy(matchDetails.getLastUpdatedBy());
        match.setSession(matchDetails.getSession());
        match.setSeason(matchDetails.getSeason());

        Match updatedMatch = matchGateway.save(match);
        log.info("Match updated successfully with ID: {}", id);
        return updatedMatch;
    }

    @Override
    public void deleteMatch(Integer id) {
        log.info("Deleting match with ID: {}", id);
        Match match = matchGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Match not found with ID: {}", id);
                    return new RuntimeException("Match not found with id " + id);
                });
        matchGateway.delete(match);
        log.info("Match deleted successfully with ID: {}", id);
    }

    @Override
    public List<Match> getMatchesBySessionId(Integer sessionId) {
        log.info("Fetching matches for session ID: {}", sessionId);
        return matchGateway.findBySessionId(sessionId);
    }
}
