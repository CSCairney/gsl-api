package com.gsl.glasgowsocialleague.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
public class GameDto {
    // Getters and Setters
    private Long id;
    private Integer sportId;
    private String description;
    private ZonedDateTime date;
    private String createdBy;
    private String lastUpdatedBy;

}
