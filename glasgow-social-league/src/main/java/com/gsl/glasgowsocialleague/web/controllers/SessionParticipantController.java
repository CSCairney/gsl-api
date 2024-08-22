package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.service.SessionParticipantService;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.SessionParticipantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/session-participants")
@Slf4j
public class SessionParticipantController {

    private final SessionParticipantService sessionParticipantService;
    private final SessionParticipantMapper sessionParticipantMapper;

    @Autowired
    public SessionParticipantController(SessionParticipantService sessionParticipantService, SessionParticipantMapper sessionParticipantMapper) {
        this.sessionParticipantService = sessionParticipantService;
        this.sessionParticipantMapper = sessionParticipantMapper;
    }

    @GetMapping("/session/{sessionId}")
    public List<SessionParticipantResponseDTO> getParticipantsBySessionId(@PathVariable Integer sessionId) {
        log.info("Fetching participants for session with ID: {}", sessionId);
        return sessionParticipantService.getParticipantsBySessionId(sessionId)
                .stream()
                .map(sessionParticipantMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/account/{accountId}")
    public List<SessionParticipantResponseDTO> getSessionsByAccountId(@PathVariable UUID accountId) {
        log.info("Fetching sessions for account with ID: {}", accountId);
        return sessionParticipantService.getSessionsByAccountId(accountId)
                .stream()
                .map(sessionParticipantMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionParticipantResponseDTO addParticipantToSession(@RequestBody SessionParticipantRequestDTO sessionParticipantRequestDTO) {
        log.info("Adding a participant to the session: {}", sessionParticipantRequestDTO);

        SessionParticipant sessionParticipant = sessionParticipantMapper.toEntity(sessionParticipantRequestDTO);
        SessionParticipant createdParticipant = sessionParticipantService.addParticipantToSession(sessionParticipant);

        return sessionParticipantMapper.toDto(createdParticipant);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeParticipantFromSession(@RequestBody SessionParticipantRequestDTO sessionParticipantRequestDTO) {
        log.info("Removing a participant from the session: {}", sessionParticipantRequestDTO);
        SessionParticipant sessionParticipant = sessionParticipantMapper.toEntity(sessionParticipantRequestDTO);
        sessionParticipantService.removeParticipantFromSession(sessionParticipant.getId());
    }
}
