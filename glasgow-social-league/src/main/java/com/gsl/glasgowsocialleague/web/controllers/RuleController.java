package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.sport.Rule;
import com.gsl.glasgowsocialleague.core.service.RuleService;
import com.gsl.glasgowsocialleague.core.service.SportService;
import com.gsl.glasgowsocialleague.web.dto.rules.RuleRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.rules.RuleResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@Slf4j
public class RuleController {

    private final RuleService ruleService;
    private final SportService sportService;

    @Autowired
    public RuleController(RuleService ruleService, SportService sportService) {
        this.ruleService = ruleService;
        this.sportService = sportService;
    }

    @GetMapping
    public List<RuleResponseDTO> getAllRules() {
        log.info("Fetching all rules");
        return ruleService.getAllRules().stream().map(rule -> {
            RuleResponseDTO dto = new RuleResponseDTO();
            BeanUtils.copyProperties(rule, dto);
            dto.setSportId(rule.getSport().getId());
            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public RuleResponseDTO getRuleById(@PathVariable Integer id) {
        log.info("Fetching rule with ID: {}", id);
        Rule rule = ruleService.getRuleById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found with id " + id));

        RuleResponseDTO dto = new RuleResponseDTO();
        BeanUtils.copyProperties(rule, dto);
        dto.setSportId(rule.getSport().getId());
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RuleResponseDTO createRule(@RequestBody RuleRequestDTO ruleRequestDTO) {
        log.info("Creating a new rule: {}", ruleRequestDTO);
        Rule rule = new Rule();
        BeanUtils.copyProperties(ruleRequestDTO, rule);
        rule.setSport(sportService.getSportById(ruleRequestDTO.getSportId())
                .orElseThrow(() -> new RuntimeException("Sport not found with id " + ruleRequestDTO.getSportId())));
        rule = ruleService.createRule(rule);

        RuleResponseDTO dto = new RuleResponseDTO();
        BeanUtils.copyProperties(rule, dto);
        dto.setSportId(rule.getSport().getId());
        return dto;
    }

    @PutMapping("/{id}")
    public RuleResponseDTO updateRule(@PathVariable Integer id, @RequestBody RuleRequestDTO ruleRequestDTO) {
        log.info("Updating rule with ID: {} with data: {}", id, ruleRequestDTO);
        Rule rule = new Rule();
        BeanUtils.copyProperties(ruleRequestDTO, rule);
        rule.setSport(sportService.getSportById(ruleRequestDTO.getSportId())
                .orElseThrow(() -> new RuntimeException("Sport not found with id " + ruleRequestDTO.getSportId())));
        rule = ruleService.updateRule(id, rule);

        RuleResponseDTO dto = new RuleResponseDTO();
        BeanUtils.copyProperties(rule, dto);
        dto.setSportId(rule.getSport().getId());
        return dto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRule(@PathVariable Integer id) {
        log.info("Deleting rule with ID: {}", id);
        ruleService.deleteRule(id);
    }
}
