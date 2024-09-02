package com.gsl.glasgowsocialleague.core.model.session;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.session.Session;
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

    @EmbeddedId
    private SessionParticipantId id;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Transient
    private String accountName;

    @Transient
    private String sessionName;

    public SessionParticipant() {}

    public SessionParticipant(SessionParticipantId id, Session session, Account account, String accountName, String sessionName) {
        this.id = id;
        this.session = session;
        this.account = account;
        this.accountName = accountName;
        this.sessionName = sessionName;
    }
}
