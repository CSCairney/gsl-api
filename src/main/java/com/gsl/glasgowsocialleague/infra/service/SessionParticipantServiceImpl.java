package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipantId;
import com.gsl.glasgowsocialleague.core.service.SessionParticipantService;
import com.gsl.glasgowsocialleague.infra.gateway.SessionParticipantGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SessionParticipantServiceImpl implements SessionParticipantService {

    private final SessionParticipantGateway sessionParticipantGateway;

    @Autowired
    public SessionParticipantServiceImpl(SessionParticipantGateway sessionParticipantGateway) {
        this.sessionParticipantGateway = sessionParticipantGateway;
    }

    @Override
    public List<SessionParticipant> getParticipantsBySessionId(Integer sessionId) {
        log.info("Fetching participants for session with ID: {}", sessionId);
        return sessionParticipantGateway.findByIdSessionId(sessionId);
    }

    @Override
    public List<SessionParticipant> getSessionsByAccountId(UUID accountId) {
        log.info("Fetching sessions for account with ID: {}", accountId);
        return sessionParticipantGateway.findByIdAccountId(accountId);
    }

    @Override
    public SessionParticipant addParticipantToSession(SessionParticipant sessionParticipant) {
        log.info("Adding participant to session: {}", sessionParticipant);
        return sessionParticipantGateway.save(sessionParticipant);
    }

    @Override
    public void removeParticipantFromSession(SessionParticipantId sessionParticipantId) {
        log.info("Removing participant from session: {}", sessionParticipantId);
        sessionParticipantGateway.deleteById(sessionParticipantId);
    }
}
