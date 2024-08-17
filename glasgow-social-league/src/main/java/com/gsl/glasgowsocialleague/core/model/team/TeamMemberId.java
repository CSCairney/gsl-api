package com.gsl.glasgowsocialleague.core.model.team;

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
public class TeamMemberId implements java.io.Serializable {
    private static final long serialVersionUID = 1408876270434139487L;
    @NotNull
    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamMemberId entity = (TeamMemberId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.teamId, entity.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, teamId);
    }

}