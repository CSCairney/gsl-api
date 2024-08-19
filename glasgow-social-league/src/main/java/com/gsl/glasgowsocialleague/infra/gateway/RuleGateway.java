package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.sport.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleGateway extends JpaRepository<Rule, Integer> {
    List<Rule> findBySportId(Integer sportId);
}

