package com.gsl.glasgowsocialleague.core.model.team;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "team_matches")
public class TeamMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_matches_id_gen")
    @SequenceGenerator(name = "team_matches_id_gen", sequenceName = "team_matches_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("now()")
    @Column(name = "match_date")
    private OffsetDateTime matchDate;

    @ColumnDefault("0")
    @Column(name = "score_team_one")
    private Integer scoreTeamOne;

    @ColumnDefault("0")
    @Column(name = "score_team_two")
    private Integer scoreTeamTwo;

    @Column(name = "details", length = Integer.MAX_VALUE)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "last_updated_by")
    private Account lastUpdatedBy;

}