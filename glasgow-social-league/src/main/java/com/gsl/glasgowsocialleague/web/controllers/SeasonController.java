package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.service.SeasonService;
import com.gsl.glasgowsocialleague.web.dto.season.SeasonRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.season.SeasonResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.SeasonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seasons")
@Slf4j
public class SeasonController {

    private final SeasonService seasonService;
    private final SeasonMapper seasonMapper;

    @Autowired
    public SeasonController(SeasonService seasonService, SeasonMapper seasonMapper) {
        this.seasonService = seasonService;
        this.seasonMapper = seasonMapper;
    }

    @GetMapping
    public List<SeasonResponseDTO> getAllSeasons() {
        log.info("Fetching all seasons");
        return seasonService.getAllSeasons()
                .stream()
                .map(seasonMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<SeasonResponseDTO> getSeasonById(@PathVariable Integer id) {
        log.info("Fetching season with ID: {}", id);
        return seasonService.getSeasonById(id)
                .map(seasonMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeasonResponseDTO createSeason(@RequestBody SeasonRequestDTO seasonRequestDTO) {
        log.info("Creating a new season: {}", seasonRequestDTO);
        Season season = seasonMapper.toEntity(seasonRequestDTO);
        Season createdSeason = seasonService.createSeason(season);
        return seasonMapper.toDto(createdSeason);
    }

    @PutMapping("/{id}")
    public SeasonResponseDTO updateSeason(@PathVariable Integer id, @RequestBody SeasonRequestDTO seasonRequestDTO) {
        log.info("Updating season with ID: {} with data: {}", id, seasonRequestDTO);
        Season season = seasonMapper.toEntity(seasonRequestDTO);
        Season updatedSeason = seasonService.updateSeason(id, season);
        return seasonMapper.toDto(updatedSeason);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeason(@PathVariable Integer id) {
        log.info("Deleting season with ID: {}", id);
        seasonService.deleteSeason(id);
    }
}
