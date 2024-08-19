package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.core.service.SportService;
import com.gsl.glasgowsocialleague.web.dto.sports.SportRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sports.SportResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
@Slf4j
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public List<SportResponseDTO> getAllSports() {
        log.info("Fetching all sports");
        return sportService.getAllSports().stream().map(sport -> {
            SportResponseDTO dto = new SportResponseDTO();
            BeanUtils.copyProperties(sport, dto);
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public SportResponseDTO getSportById(@PathVariable Integer id) {
        log.info("Fetching sport with ID: {}", id);
        Sport sport = sportService.getSportById(id)
                .orElseThrow(() -> new RuntimeException("Sport not found with id " + id));

        SportResponseDTO dto = new SportResponseDTO();
        BeanUtils.copyProperties(sport, dto);
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SportResponseDTO createSport(@RequestBody SportRequestDTO sportRequestDTO) {
        log.info("Creating a new sport: {}", sportRequestDTO);
        Sport sport = new Sport();
        BeanUtils.copyProperties(sportRequestDTO, sport);
        sport = sportService.createSport(sport);

        SportResponseDTO dto = new SportResponseDTO();
        BeanUtils.copyProperties(sport, dto);
        return dto;
    }

    @PutMapping("/{id}")
    public SportResponseDTO updateSport(@PathVariable Integer id, @RequestBody SportRequestDTO sportRequestDTO) {
        log.info("Updating sport with ID: {} with data: {}", id, sportRequestDTO);
        Sport sport = new Sport();
        BeanUtils.copyProperties(sportRequestDTO, sport);
        sport = sportService.updateSport(id, sport);

        SportResponseDTO dto = new SportResponseDTO();
        BeanUtils.copyProperties(sport, dto);
        return dto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSport(@PathVariable Integer id) {
        log.info("Deleting sport with ID: {}", id);
        sportService.deleteSport(id);
    }
}
