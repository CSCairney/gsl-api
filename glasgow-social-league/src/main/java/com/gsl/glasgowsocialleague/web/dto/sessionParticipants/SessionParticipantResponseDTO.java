package com.gsl.glasgowsocialleague.web.dto.sessionParticipants;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SessionParticipantResponseDTO {

    private Integer sessionId;
    private UUID accountId;
}

