package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.sport.Sport;

import java.util.List;
import java.util.Optional;

public interface SportService {
    List<Sport> getAllSports();

    Optional<Sport> getSportById(Integer id);

    Sport createSport(Sport sport);

    Sport updateSport(Integer id, Sport sportDetails);

    void deleteSport(Integer id);
}
