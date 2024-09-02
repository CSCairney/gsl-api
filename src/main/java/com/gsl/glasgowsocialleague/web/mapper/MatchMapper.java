package com.gsl.glasgowsocialleague.web.mapper;

import com.gsl.glasgowsocialleague.core.model.match.Match;
import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.core.service.SeasonService;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.web.dto.match.MatchRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.match.MatchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private SessionService sessionService;

    public Match toEntity(MatchRequestDTO dto) {
        Match match = new Match();

        if (dto.getId() != null) {
            match.setId(dto.getId());
        }

        Account playerOne = accountService.getAccountById(dto.getPlayerOneId())
                .orElseThrow(() -> new RuntimeException("Player One not found with id " + dto.getPlayerOneId()));
        Account playerTwo = accountService.getAccountById(dto.getPlayerTwoId())
                .orElseThrow(() -> new RuntimeException("Player Two not found with id " + dto.getPlayerTwoId()));
        Account winner = dto.getWinnerId() != null ? accountService.getAccountById(dto.getWinnerId())
                .orElseThrow(() -> new RuntimeException("Winner not found with id " + dto.getWinnerId())) : null;
        Account createdBy = accountService.getAccountById(dto.getCreatedById())
                .orElseThrow(() -> new RuntimeException("Created by user not found with id " + dto.getCreatedById()));
        Account lastUpdatedBy = dto.getLastUpdatedById() != null ? accountService.getAccountById(dto.getLastUpdatedById())
                .orElseThrow(() -> new RuntimeException("Last updated by user not found with id " + dto.getLastUpdatedById())) : null;
        Session session = sessionService.getSessionById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found with id " + dto.getSessionId()));
        Season season = seasonService.getSeasonById(dto.getSeasonId())
                .orElseThrow(() -> new RuntimeException("Season not found with id " + dto.getSeasonId()));

        match.setPlayerOne(playerOne);
        match.setPlayerTwo(playerTwo);
        match.setScorePlayerOne(dto.getScorePlayerOne());
        match.setScorePlayerTwo(dto.getScorePlayerTwo());
        match.setWinner(winner);
        match.setDetails(dto.getDetails());
        match.setCreatedBy(createdBy);
        match.setLastUpdatedBy(lastUpdatedBy);
        match.setSession(session);
        match.setSeason(season);

        return match;
    }

    public MatchResponseDTO toDto(Match entity) {
        MatchResponseDTO dto = new MatchResponseDTO();

        dto.setId(entity.getId()); // Ensure the ID is passed in the response DTO
        dto.setPlayerOneId(entity.getPlayerOne() != null ? entity.getPlayerOne().getId() : null);
        dto.setPlayerTwoId(entity.getPlayerTwo() != null ? entity.getPlayerTwo().getId() : null);
        dto.setScorePlayerOne(entity.getScorePlayerOne());
        dto.setScorePlayerTwo(entity.getScorePlayerTwo());
        dto.setWinnerId(entity.getWinner() != null ? entity.getWinner().getId() : null);
        dto.setDetails(entity.getDetails());
        dto.setCreatedById(entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null);
        dto.setLastUpdatedById(entity.getLastUpdatedBy() != null ? entity.getLastUpdatedBy().getId() : null);
        dto.setSessionId(entity.getSession() != null ? entity.getSession().getId() : null);
        dto.setSeasonId(entity.getSeason() != null ? entity.getSeason().getId() : null);

        return dto;
    }
}

