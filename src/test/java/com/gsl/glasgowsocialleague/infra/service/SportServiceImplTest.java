package com.gsl.glasgowsocialleague.infra.service;


import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.infra.gateway.SportGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class SportServiceImplTest {

    @Mock
    private SportGateway sportGateway;

    @InjectMocks
    private SportServiceImpl sportService;

    @Test
    void testGetSportById() {
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");

        when(sportGateway.findById(1)).thenReturn(Optional.of(sport));

        Optional<Sport> result = sportService.getSportById(1);

        assertTrue(result.isPresent());
        assertEquals("Football", result.get().getName());
    }

    @Test
    void testCreateSport() {
        Sport sport = new Sport();
        sport.setName("Basketball");

        when(sportGateway.save(sport)).thenReturn(sport);

        Sport createdSport = sportService.createSport(sport);

        assertNotNull(createdSport);
        assertEquals("Basketball", createdSport.getName());
    }

    @Test
    void testUpdateSport() {
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");

        when(sportGateway.findById(1)).thenReturn(Optional.of(sport));
        when(sportGateway.save(sport)).thenReturn(sport);

        Sport updatedSport = sportService.updateSport(1, sport);

        assertNotNull(updatedSport);
        assertEquals("Football", updatedSport.getName());
    }

    @Test
    void testDeleteSport() {
        Sport sport = new Sport();
        sport.setId(1);

        when(sportGateway.findById(1)).thenReturn(Optional.of(sport));

        sportService.deleteSport(1);

        verify(sportGateway, times(1)).delete(sport);
    }
}
