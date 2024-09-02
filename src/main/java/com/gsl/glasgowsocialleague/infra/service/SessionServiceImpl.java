package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.infra.gateway.SessionGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    private final SessionGateway sessionGateway;

    @Autowired
    public SessionServiceImpl(SessionGateway sessionGateway) {
        this.sessionGateway = sessionGateway;
    }

    @Override
    public List<Session> getAllSessions() {
        log.info("Fetching all sessions");
        return sessionGateway.findAll();
    }

    @Override
    public Optional<Session> getSessionById(Integer id) {
        log.info("Fetching session with ID: {}", id);
        return sessionGateway.findById(id);
    }

    @Override
    public Session createSession(Session session) {
        log.info("Creating new session: {}", session);
        return sessionGateway.save(session);
    }

    @Override
    public Session updateSession(Integer id, Session sessionDetails) {
        log.info("Updating session with ID: {}", id);
        Session session = sessionGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Session not found with ID: {}", id);
                    return new RuntimeException("Session not found with id " + id);
                });

        session.setSport(sessionDetails.getSport());
        session.setDate(sessionDetails.getDate());
        session.setCreatedBy(sessionDetails.getCreatedBy());
        session.setLastUpdatedBy(sessionDetails.getLastUpdatedBy());
        session.setSeason(sessionDetails.getSeason());

        Session updatedSession = sessionGateway.save(session);
        log.info("Session updated successfully with ID: {}", id);
        return updatedSession;
    }

    @Override
    public void deleteSession(Integer id) {
        log.info("Deleting session with ID: {}", id);
        Session session = sessionGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Session not found with ID: {}", id);
                    return new RuntimeException("Session not found with id " + id);
                });
        sessionGateway.delete(session);
        log.info("Session deleted successfully with ID: {}", id);
    }
}
