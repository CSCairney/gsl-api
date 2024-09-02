package com.gsl.glasgowsocialleague.core.model.league;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "league_participants")
public class LeagueParticipant {
    @SequenceGenerator(name = "league_participants_id_gen", sequenceName = "events_id_seq", allocationSize = 1)
    @EmbeddedId
    private LeagueParticipantId id;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}