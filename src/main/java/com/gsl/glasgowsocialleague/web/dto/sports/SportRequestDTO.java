package com.gsl.glasgowsocialleague.web.dto.sports;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SportRequestDTO {

    @NotNull
    @Size(max = 100)
    private String name;

    private String description;
}

