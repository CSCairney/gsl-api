package com.gsl.glasgowsocialleague.web.dto.rules;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleRequestDTO {

    @NotNull
    private String ruleText;

    @NotNull
    private Integer sportId; // Reference to the sport ID
}

