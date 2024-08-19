package com.gsl.glasgowsocialleague.web.dto.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleResponseDTO {

    private Integer id;
    private String ruleText;
    private Integer sportId;
}

