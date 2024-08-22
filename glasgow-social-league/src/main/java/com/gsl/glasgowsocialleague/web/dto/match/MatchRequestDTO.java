package com.gsl.glasgowsocialleague.web.dto.match;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchRequestDTO {

    private Integer id;

    @NotNull
    private Integer sessionId;

    @NotNull
    private UUID playerOneId;

    @NotNull
    private UUID playerTwoId;

    private Integer scorePlayerOne;

    private Integer scorePlayerTwo;

    private UUID winnerId;

    private String details;

    @NotNull
    private UUID createdById;

    private UUID lastUpdatedById;

    @NotNull
    private Integer seasonId;
}
