package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import com.gsl.glasgowsocialleague.core.service.SessionService;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.sessions.SessionResponseDTO;
import com.gsl.glasgowsocialleague.web.mapper.SessionMapper;
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
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionService sessionService, SessionMapper sessionMapper) {
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping
    public Page<SessionResponseDTO> getAllSessions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer sportId) {
        log.info("Fetching sessions with pagination - page: {}, size: {}, sportId: {}", page, size, sportId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Session> sessions = sessionService.getAllSessions(pageable, sportId);

        return sessions.map(sessionMapper::toDto);
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
