package com.gsl.glasgowsocialleague.core.model.statistic;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_id_gen")
    @SequenceGenerator(name = "statistics_id_gen", sequenceName = "statistics_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @ColumnDefault("0")
    @Column(name = "total_matches")
    private Integer totalMatches;

    @ColumnDefault("0")
    @Column(name = "wins")
    private Integer wins;

    @ColumnDefault("0")
    @Column(name = "losses")
    private Integer losses;

    @ColumnDefault("0")
    @Column(name = "points_scored")
    private Integer pointsScored;

    @ColumnDefault("0")
    @Column(name = "points_conceded")
    private Integer pointsConceded;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "created_by")
    private Account createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "last_updated_by")
    private Account lastUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "season_id")
    private Season season;

}