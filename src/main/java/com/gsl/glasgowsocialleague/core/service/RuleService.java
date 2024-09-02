package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.sport.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleService {
    List<Rule> getAllRules();

    Optional<Rule> getRuleById(Integer id);

    Rule createRule(Rule rule);

    Rule updateRule(Integer id, Rule ruleDetails);

    void deleteRule(Integer id);
}
