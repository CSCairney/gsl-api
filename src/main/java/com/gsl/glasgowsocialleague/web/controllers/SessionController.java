package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.service.SessionParticipantService;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.web.dto.sessionParticipants.SessionParticipantResponseDTO;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.SessionMapper;
import com.gsl.glasgowsocialleague.web.mapper.SessionParticipantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessions")
@Slf4j
public class SessionController {

    private final SessionService sessionService;
    private final SessionParticipantService sessionParticipantService;
    private final SessionMapper sessionMapper;
    private final SessionParticipantMapper sessionParticipantMapper;

    @Autowired
    public SessionController(
            SessionService sessionService,
            SessionMapper sessionMapper,
            SessionParticipantService sessionParticipantService,
            SessionParticipantMapper sessionParticipantMapper) {
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
        this.sessionParticipantService = sessionParticipantService;
        this.sessionParticipantMapper = sessionParticipantMapper;
    }

    @GetMapping
    public Page<SessionResponseDTO> getAllSessions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer sportId,
            @RequestParam(defaultValue = "false") boolean includeParticipants) { // New parameter
        log.info("Fetching sessions with pagination - page: {}, size: {}, sportId: {}, includeParticipants: {}", page, size, sportId, includeParticipants);

        Pageable pageable = PageRequest.of(page, size);
        Page<Session> sessions = sessionService.getAllSessions(pageable, sportId);

        // Convert sessions to DTOs
        Page<SessionResponseDTO> sessionResponseDTOS = sessions.map(sessionMapper::toDto);

        // If includeParticipants is true, fetch and set participants for each session
        if (includeParticipants) {
            sessionResponseDTOS.forEach(sessionDTO -> {
                List<SessionParticipantResponseDTO> participants = sessionParticipantService.getParticipantsBySessionId(sessionDTO.getId())
                        .stream()
                        .map(sessionParticipantMapper::toDto)
                        .collect(Collectors.toList());
                sessionDTO.setParticipants(participants); // Add a setParticipants method in the DTO
            });
        }

        return sessionResponseDTOS;
    }


    @GetMapping("/{id}")
    public Optional<SessionResponseDTO> getSessionById(@PathVariable Integer id) {
        log.info("Fetching session with ID: {}", id);
        return sessionService.getSessionById(id)
                .map(sessionMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseDTO createSession(@RequestBody SessionRequestDTO sessionRequestDTO) {
        log.info("Creating a new session: {}", sessionRequestDTO);
        Session session = sessionMapper.toEntity(sessionRequestDTO);
        Session createdSession = sessionService.createSession(session);
        return sessionMapper.toDto(createdSession);
    }

    @PutMapping("/{id}")
    public SessionResponseDTO updateSession(@PathVariable Integer id, @RequestBody SessionRequestDTO sessionRequestDTO) {
        log.info("Updating session with ID: {} with data: {}", id, sessionRequestDTO);
        Session session = sessionMapper.toEntity(sessionRequestDTO);
        Session updatedSession = sessionService.updateSession(id, session);
        return sessionMapper.toDto(updatedSession);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable Integer id) {
        log.info("Deleting session with ID: {}", id);
        sessionService.deleteSession(id);
    }
}
