package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionParticipantGateway extends JpaRepository<SessionParticipant, SessionParticipantId> {
    List<SessionParticipant> findByIdSessionId(Integer sessionId);
    List<SessionParticipant> findByIdAccountId(UUID accountId);
}
