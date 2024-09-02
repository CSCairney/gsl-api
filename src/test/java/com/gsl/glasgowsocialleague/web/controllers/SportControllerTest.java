package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.core.service.SportService;
import com.gsl.glasgowsocialleague.web.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(SportController.class)
class SportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportService sportService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void testGetAllSports() throws Exception {
        List<Sport> sports = new ArrayList<>();
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");
        sports.add(sport);

        when(sportService.getAllSports()).thenReturn(sports);

        mockMvc.perform(get("/sports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Football"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void testGetSportById() throws Exception {
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");

        when(sportService.getSportById(1)).thenReturn(Optional.of(sport));

        mockMvc.perform(get("/sports/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Football"));
    }
}
