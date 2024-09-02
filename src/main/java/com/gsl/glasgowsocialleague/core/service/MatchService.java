package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.match.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<Match> getAllMatches();
    Optional<Match> getMatchById(Integer id);
    Match createMatch(Match match);
    Match updateMatch(Integer id, Match matchDetails);
    void deleteMatch(Integer id);

    // New method to fetch matches by session ID
    List<Match> getMatchesBySessionId(Integer sessionId);
}
