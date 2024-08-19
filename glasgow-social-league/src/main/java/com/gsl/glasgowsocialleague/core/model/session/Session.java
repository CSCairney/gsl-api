package com.gsl.glasgowsocialleague.core.model.session;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.model.sport.Sport; // Assuming Sport is another entity
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessions_id_gen")
    @SequenceGenerator(name = "sessions_id_gen", sequenceName = "sessions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Assuming there is a Sport entity
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ColumnDefault("now()")
    @Column(name = "date")
    private OffsetDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private Account createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "last_updated_by", referencedColumnName = "id")
    private Account lastUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;
}
