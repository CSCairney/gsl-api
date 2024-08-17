package com.gsl.glasgowsocialleague.core.model.sport;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rules_id_gen")
    @SequenceGenerator(name = "rules_id_gen", sequenceName = "rules_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "rule_text", nullable = false, length = Integer.MAX_VALUE)
    private String ruleText;

}