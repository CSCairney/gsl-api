package com.gsl.glasgowsocialleague.core.model.event;

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
public class EventParticipantId implements java.io.Serializable {
    private static final long serialVersionUID = 6767641907700308902L;
    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventParticipantId entity = (EventParticipantId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.accountId, entity.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, accountId);
    }

}