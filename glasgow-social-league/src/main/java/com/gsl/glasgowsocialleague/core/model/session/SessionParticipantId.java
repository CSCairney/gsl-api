package com.gsl.glasgowsocialleague.core.model.session;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class SessionParticipantId implements Serializable {

    private Integer sessionId;
    private UUID accountId;

    public SessionParticipantId() {}

    public SessionParticipantId(Integer sessionId, UUID accountId) {
        this.sessionId = sessionId;
        this.accountId = accountId;
    }
}
