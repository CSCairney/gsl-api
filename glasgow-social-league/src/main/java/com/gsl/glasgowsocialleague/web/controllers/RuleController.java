package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.sport.Rule;
import com.gsl.glasgowsocialleague.core.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rules")
@Slf4j
public class RuleController {

    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public List<Rule> getAllRules() {
        log.info("Fetching all rules");
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public Optional<Rule> getRuleById(@PathVariable Integer id) {
        log.info("Fetching rule with ID: {}", id);
        return ruleService.getRuleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rule createRule(@RequestBody Rule rule) {
        log.info("Creating a new rule: {}", rule);
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public Rule updateRule(@PathVariable Integer id, @RequestBody Rule rule) {
        log.info("Updating rule with ID: {} with data: {}", id, rule);
        return ruleService.updateRule(id, rule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRule(@PathVariable Integer id) {
        log.info("Deleting rule with ID: {}", id);
        ruleService.deleteRule(id);
    }
}
