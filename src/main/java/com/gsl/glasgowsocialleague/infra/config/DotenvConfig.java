package com.gsl.glasgowsocialleague.infra.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv(ConfigurableEnvironment env) {
        Dotenv dotenv = Dotenv.configure().load();

        dotenv.entries().forEach(entry -> {
            // Add each .env entry to the Spring Environment
            env.getSystemProperties().put(entry.getKey(), entry.getValue());
        });

        return dotenv;
    }
}
