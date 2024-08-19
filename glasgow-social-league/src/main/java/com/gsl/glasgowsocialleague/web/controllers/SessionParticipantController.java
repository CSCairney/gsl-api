package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.session.SessionParticipant;
import com.gsl.glasgowsocialleague.core.model.session.SessionParticipantId;
import com.gsl.glasgowsocialleague.core.service.SessionParticipantService;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    public SessionParticipantController(SessionParticipantService sessionParticipantService) {
        this.sessionParticipantService = sessionParticipantService;
    }

    @GetMapping("/session/{sessionId}")
    public List<SessionParticipantResponseDTO> getParticipantsBySessionId(@PathVariable Integer sessionId) {
        log.info("Fetching participants for session with ID: {}", sessionId);
        return sessionParticipantService.getParticipantsBySessionId(sessionId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/account/{accountId}")
    public List<SessionParticipantResponseDTO> getSessionsByAccountId(@PathVariable UUID accountId) {
        log.info("Fetching sessions for account with ID: {}", accountId);
        return sessionParticipantService.getSessionsByAccountId(accountId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionParticipantResponseDTO addParticipantToSession(@RequestBody SessionParticipantRequestDTO sessionParticipantRequestDTO) {
        log.info("Adding a participant to the session: {}", sessionParticipantRequestDTO);
        SessionParticipant sessionParticipant = new SessionParticipant();
        SessionParticipantId id = new SessionParticipantId();
        id.setSessionId(sessionParticipantRequestDTO.getSessionId());
        id.setAccountId(sessionParticipantRequestDTO.getAccountId());
        sessionParticipant.setId(id);
        sessionParticipant.setAccount(null);  // Set the account entity based on your application's logic

        SessionParticipant createdParticipant = sessionParticipantService.addParticipantToSession(sessionParticipant);
        return convertToDto(createdParticipant);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeParticipantFromSession(@RequestBody SessionParticipantRequestDTO sessionParticipantRequestDTO) {
        log.info("Removing a participant from the session: {}", sessionParticipantRequestDTO);
        SessionParticipantId sessionParticipantId = new SessionParticipantId();
        sessionParticipantId.setSessionId(sessionParticipantRequestDTO.getSessionId());
        sessionParticipantId.setAccountId(sessionParticipantRequestDTO.getAccountId());
        sessionParticipantService.removeParticipantFromSession(sessionParticipantId);
    }

    private SessionParticipantResponseDTO convertToDto(SessionParticipant sessionParticipant) {
        SessionParticipantResponseDTO sessionParticipantResponseDTO = new SessionParticipantResponseDTO();
        BeanUtils.copyProperties(sessionParticipant.getId(), sessionParticipantResponseDTO);
        return sessionParticipantResponseDTO;
    }
}
