package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.service.SeasonService;
import com.gsl.glasgowsocialleague.infra.gateway.SeasonGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SeasonServiceImpl implements SeasonService {

    private final SeasonGateway seasonGateway;

    @Autowired
    public SeasonServiceImpl(SeasonGateway seasonGateway) {
        this.seasonGateway = seasonGateway;
    }

    @Override
    public List<Season> getAllSeasons() {
        log.info("Fetching all seasons");
        return seasonGateway.findAll();
    }

    @Override
    public Optional<Season> getSeasonById(Integer id) {
        log.info("Fetching season with ID: {}", id);
        return seasonGateway.findById(id);
    }

    @Override
    public Season createSeason(Season season) {
        log.info("Creating new season: {}", season);
        return seasonGateway.save(season);
    }

    @Override
    public Season updateSeason(Integer id, Season seasonDetails) {
        log.info("Updating season with ID: {}", id);
        Season season = seasonGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Season not found with ID: {}", id);
                    return new RuntimeException("Season not found with id " + id);
                });

        season.setStartDate(seasonDetails.getStartDate());
        season.setEndDate(seasonDetails.getEndDate());
        season.setChampionAccount(seasonDetails.getChampionAccount());

        Season updatedSeason = seasonGateway.save(season);
        log.info("Season updated successfully with ID: {}", id);
        return updatedSeason;
    }

    @Override
    public void deleteSeason(Integer id) {
        log.info("Deleting season with ID: {}", id);
        Season season = seasonGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Season not found with ID: {}", id);
                    return new RuntimeException("Season not found with id " + id);
                });
        seasonGateway.delete(season);
        log.info("Season deleted successfully with ID: {}", id);
    }
}
