package com.gsl.glasgowsocialleague.web.dto.sessionParticipants;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SessionParticipantRequestDTO {

    @NotNull
    private Integer sessionId;

    @NotNull
    private UUID accountId;
}

