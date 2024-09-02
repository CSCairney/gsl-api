package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.season.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    List<Season> getAllSeasons();
    Optional<Season> getSeasonById(Integer id);
    Season createSeason(Season season);
    Season updateSeason(Integer id, Season seasonDetails);
    void deleteSeason(Integer id);
}
