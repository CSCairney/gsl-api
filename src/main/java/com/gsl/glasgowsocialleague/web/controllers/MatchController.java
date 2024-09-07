package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.match.Match;
import com.gsl.glasgowsocialleague.core.service.MatchService;
import com.gsl.glasgowsocialleague.web.dto.match.MatchRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.match.MatchResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.MatchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matches")
@Slf4j
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @GetMapping
    public List<MatchResponseDTO> getAllMatches(
            @RequestParam(required = false) Integer sportId,
            @RequestParam(defaultValue = "30") Integer amount
    ) {
        log.info("Fetching all matches");
        return matchService.getAllMatches(sportId, amount)
                .stream()
                .map(matchMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<MatchResponseDTO> getMatchById(@PathVariable Integer id) {
        log.info("Fetching match with ID: {}", id);
        return matchService.getMatchById(id)
                .map(matchMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatchResponseDTO createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        log.info("Creating a new match: {}", matchRequestDTO);
        Match match = matchMapper.toEntity(matchRequestDTO);
        Match createdMatch = matchService.createMatch(match);
        return matchMapper.toDto(createdMatch);
    }

    @PutMapping
    public MatchResponseDTO updateMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        log.info("Updating match with ID: {} with data: {}", matchRequestDTO.getId(), matchRequestDTO);
        Match match = matchMapper.toEntity(matchRequestDTO);
        Match updatedMatch = matchService.updateMatch(match.getId(), match);
        return matchMapper.toDto(updatedMatch);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable Integer id) {
        log.info("Deleting match with ID: {}", id);
        matchService.deleteMatch(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<MatchResponseDTO> getMatchesBySessionId(@PathVariable Integer sessionId) {
        log.info("Fetching matches for session ID: {}", sessionId);
        return matchService.getMatchesBySessionId(sessionId)
                .stream()
                .map(matchMapper::toDto)
                .collect(Collectors.toList());
    }
}
