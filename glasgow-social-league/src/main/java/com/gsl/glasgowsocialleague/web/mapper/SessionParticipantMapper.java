package com.gsl.glasgowsocialleague.web.mapper;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipantId;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionParticipantMapper {

    private final AccountService accountService;
    private final SessionService sessionService;

    @Autowired
    public SessionParticipantMapper(AccountService accountService, SessionService sessionService) {
        this.accountService = accountService;
        this.sessionService = sessionService;
    }

    public SessionParticipant toEntity(SessionParticipantRequestDTO dto) {
        SessionParticipant sessionParticipant = new SessionParticipant();

        Account account = accountService.getAccountById(dto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found with id " + dto.getAccountId()));

        Session session = sessionService.getSessionById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found with id " + dto.getSessionId()));

        sessionParticipant.setId(new SessionParticipantId(dto.getSessionId(), dto.getAccountId()));
        sessionParticipant.setAccount(account);
        sessionParticipant.setSession(session);

        return sessionParticipant;
    }

    public SessionParticipantResponseDTO toDto(SessionParticipant entity) {
        SessionParticipantResponseDTO dto = new SessionParticipantResponseDTO();

        dto.setSessionId(entity.getSession().getId());
        dto.setAccountId(entity.getAccount().getId());

        return dto;
    }
}

