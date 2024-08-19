package com.gsl.glasgowsocialleague.core.model.session;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class SessionParticipantId implements java.io.Serializable {
    private static final long serialVersionUID = 3419425124935441746L;
    @NotNull
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SessionParticipantId entity = (SessionParticipantId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.sessionId, entity.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, sessionId);
    }

}