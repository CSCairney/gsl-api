package com.gsl.glasgowsocialleague.web.dto.sessions;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class SessionRequestDTO {

    @NotNull
    private Integer sportId;

    private OffsetDateTime date;

    @NotNull
    private UUID createdBy;

    private UUID lastUpdatedBy;

    @NotNull
    private Integer seasonId;
}

