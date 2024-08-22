package com.gsl.glasgowsocialleague.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class LoginResponse {
    private UUID id;

    private String token;

    private String username;

    private String email;


    public LoginResponse(String token, String username, String email, UUID id) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.id = id;

    }
}

