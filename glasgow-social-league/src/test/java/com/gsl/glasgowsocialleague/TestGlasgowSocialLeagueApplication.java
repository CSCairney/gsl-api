package com.gsl.glasgowsocialleague;

import org.springframework.boot.SpringApplication;

public class TestGlasgowSocialLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.from(GlasgowSocialLeagueApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
