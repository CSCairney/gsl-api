package com.gsl.glasgowsocialleague.core.model.league;

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
public class LeagueParticipantId implements java.io.Serializable {
    private static final long serialVersionUID = -3376114609394426424L;
    @NotNull
    @Column(name = "league_id", nullable = false)
    private Integer leagueId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LeagueParticipantId entity = (LeagueParticipantId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.leagueId, entity.leagueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, leagueId);
    }

}