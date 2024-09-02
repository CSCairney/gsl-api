package com.gsl.glasgowsocialleague.web.mapper;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.core.service.SeasonService;
import com.gsl.glasgowsocialleague.core.service.SportService;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class SessionMapper {

    @Autowired
    private SportService sportService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SeasonService seasonService;

    public Session toEntity(SessionRequestDTO dto) {
        Session session = new Session();

        // Fetching related entities and setting them to the session
        Sport sport = sportService.getSportById(dto.getSportId())
                .orElseThrow(() -> new RuntimeException("Sport not found with id " + dto.getSportId()));
        Account createdBy = accountService.getAccountById(dto.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("Account not found with id " + dto.getCreatedBy()));
        Account lastUpdatedBy = dto.getLastUpdatedBy() != null ?
                accountService.getAccountById(dto.getLastUpdatedBy())
                        .orElseThrow(() -> new RuntimeException("Account not found with id " + dto.getLastUpdatedBy())) : null;
        Season season = seasonService.getSeasonById(dto.getSeasonId())
                .orElseThrow(() -> new RuntimeException("Season not found with id " + dto.getSeasonId()));

        session.setSport(sport);
        session.setDate(dto.getDate() != null ? dto.getDate() : OffsetDateTime.now());
        session.setCreatedBy(createdBy);
        session.setLastUpdatedBy(lastUpdatedBy);
        session.setSeason(season);

        return session;
    }

    public SessionResponseDTO toDto(Session entity) {
        SessionResponseDTO dto = new SessionResponseDTO();

        dto.setId(entity.getId());
        dto.setSportId(entity.getSport().getId());
        dto.setDate(entity.getDate());
        dto.setCreatedBy(entity.getCreatedBy().getId());
        dto.setLastUpdatedBy(entity.getLastUpdatedBy() != null ? entity.getLastUpdatedBy().getId() : null);
        dto.setSeasonId(entity.getSeason().getId());

        return dto;
    }
}

