package com.gsl.glasgowsocialleague.core.model.team;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "team_members")
public class TeamMember {
    @SequenceGenerator(name = "team_members_id_gen", sequenceName = "team_matches_id_seq", allocationSize = 1)
    @EmbeddedId
    private TeamMemberId id;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}