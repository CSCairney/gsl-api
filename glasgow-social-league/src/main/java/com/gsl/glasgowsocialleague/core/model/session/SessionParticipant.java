package com.gsl.glasgowsocialleague.core.model.session;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "session_participants")
public class SessionParticipant {
    @SequenceGenerator(name = "session_participants_id_gen", sequenceName = "seasons_id_seq", allocationSize = 1)
    @EmbeddedId
    private SessionParticipantId id;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}