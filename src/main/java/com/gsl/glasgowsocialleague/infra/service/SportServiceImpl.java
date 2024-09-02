package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;
import com.gsl.glasgowsocialleague.core.service.SportService;
import com.gsl.glasgowsocialleague.infra.gateway.SportGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SportServiceImpl implements SportService {

    private final SportGateway sportGateway;

    @Autowired
    public SportServiceImpl(SportGateway sportGateway) {
        this.sportGateway = sportGateway;
    }

    @Override
    public List<Sport> getAllSports() {
        log.info("Fetching all sports");
        return sportGateway.findAll();
    }

    @Override
    public Optional<Sport> getSportById(Integer id) {
        log.info("Fetching sport with ID: {}", id);
        return sportGateway.findById(id);
    }

    @Override
    public Sport createSport(Sport sport) {
        log.info("Creating new sport: {}", sport);
        return sportGateway.save(sport);
    }

    @Override
    public Sport updateSport(Integer id, Sport sportDetails) {
        log.info("Updating sport with ID: {}", id);
        Sport sport = sportGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Sport not found with ID: {}", id);
                    return new RuntimeException("Sport not found with id " + id);
                });

        sport.setName(sportDetails.getName());
        sport.setDescription(sportDetails.getDescription());

        Sport updatedSport = sportGateway.save(sport);
        log.info("Sport updated successfully with ID: {}", id);
        return updatedSport;
    }

    @Override
    public void deleteSport(Integer id) {
        log.info("Deleting sport with ID: {}", id);
        Sport sport = sportGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Sport not found with ID: {}", id);
                    return new RuntimeException("Sport not found with id " + id);
                });
        sportGateway.delete(sport);
        log.info("Sport deleted successfully with ID: {}", id);
    }
}
