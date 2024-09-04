package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    Page<Session> getAllSessions(Pageable pageable, Integer sportId);
    Optional<Session> getSessionById(Integer id);
    Session createSession(Session session);
    Session updateSession(Integer id, Session sessionDetails);
    void deleteSession(Integer id);
}
