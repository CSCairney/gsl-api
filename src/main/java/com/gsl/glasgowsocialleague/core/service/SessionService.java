package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.session.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<Session> getAllSessions();
    Optional<Session> getSessionById(Integer id);
    Session createSession(Session session);
    Session updateSession(Integer id, Session sessionDetails);
    void deleteSession(Integer id);
}
