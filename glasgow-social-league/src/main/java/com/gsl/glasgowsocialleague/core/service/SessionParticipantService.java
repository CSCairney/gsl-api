package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipantId;

import java.util.List;
import java.util.UUID;

public interface SessionParticipantService {
    List<SessionParticipant> getParticipantsBySessionId(Integer sessionId);
    List<SessionParticipant> getSessionsByAccountId(UUID accountId);
    SessionParticipant addParticipantToSession(SessionParticipant sessionParticipant);
    void removeParticipantFromSession(SessionParticipantId sessionParticipantId);
}
