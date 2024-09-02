package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.SessionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionService sessionService, SessionMapper sessionMapper) {
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping
    public List<SessionResponseDTO> getAllSessions() {
        log.info("Fetching all sessions");
        return sessionService.getAllSessions()
                .stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
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
