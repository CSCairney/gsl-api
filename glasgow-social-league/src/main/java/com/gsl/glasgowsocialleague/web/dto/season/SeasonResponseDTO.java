package com.gsl.glasgowsocialleague.web.dto.season;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class SeasonResponseDTO {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID championAccountId;
}

