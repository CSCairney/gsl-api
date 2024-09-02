package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.sport.Rule;
import com.gsl.glasgowsocialleague.core.service.RuleService;
import com.gsl.glasgowsocialleague.infra.gateway.RuleGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RuleServiceImpl implements RuleService {

    private final RuleGateway ruleGateway;

    @Autowired
    public RuleServiceImpl(RuleGateway ruleGateway) {
        this.ruleGateway = ruleGateway;
    }

    @Override
    public List<Rule> getAllRules() {
        log.info("Fetching all rules");
        return ruleGateway.findAll();
    }

    @Override
    public Optional<Rule> getRuleById(Integer id) {
        log.info("Fetching rule with ID: {}", id);
        return ruleGateway.findById(id);
    }

    @Override
    public Rule createRule(Rule rule) {
        log.info("Creating new rule: {}", rule);
        return ruleGateway.save(rule);
    }

    @Override
    public Rule updateRule(Integer id, Rule ruleDetails) {
        log.info("Updating rule with ID: {}", id);
        Rule rule = ruleGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Rule not found with ID: {}", id);
                    return new RuntimeException("Rule not found with id " + id);
                });

        rule.setRuleText(ruleDetails.getRuleText());
        rule.setSport(ruleDetails.getSport());

        Rule updatedRule = ruleGateway.save(rule);
        log.info("Rule updated successfully with ID: {}", id);
        return updatedRule;
    }

    @Override
    public void deleteRule(Integer id) {
        log.info("Deleting rule with ID: {}", id);
        Rule rule = ruleGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Rule not found with ID: {}", id);
                    return new RuntimeException("Rule not found with id " + id);
                });
        ruleGateway.delete(rule);
        log.info("Rule deleted successfully with ID: {}", id);
    }
}
