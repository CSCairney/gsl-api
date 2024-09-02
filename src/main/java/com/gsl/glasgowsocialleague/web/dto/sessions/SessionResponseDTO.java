package com.gsl.glasgowsocialleague.web.dto.sessions;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class SessionResponseDTO {

    private Integer id;
    private Integer sportId;
    private OffsetDateTime date;
    private UUID createdBy;
    private UUID lastUpdatedBy;
    private Integer seasonId;
}

