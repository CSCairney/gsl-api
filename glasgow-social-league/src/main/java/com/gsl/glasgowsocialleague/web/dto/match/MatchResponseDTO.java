package com.gsl.glasgowsocialleague.web.dto.match;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchResponseDTO {
    private Integer id;

    private Integer sessionId;

    private UUID playerOneId;

    private UUID playerTwoId;

    private Integer scorePlayerOne;

    private Integer scorePlayerTwo;

    private UUID winnerId;

    private String details;

    private UUID createdById;

    private UUID lastUpdatedById;

    private Integer seasonId;
}

