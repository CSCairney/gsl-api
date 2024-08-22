package com.gsl.glasgowsocialleague.web.mapper;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.season.Season;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.web.dto.season.SeasonRequestDTO;
import com.gsl.glasgowsocialleague.web.dto.season.SeasonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    @Autowired
    private AccountService accountService;

    public Season toEntity(SeasonRequestDTO dto) {
        Season season = new Season();

        Account championAccount = dto.getChampionAccountId() != null ?
                accountService.getAccountById(dto.getChampionAccountId())
                        .orElseThrow(() -> new RuntimeException("Account not found with id " + dto.getChampionAccountId())) : null;

        season.setStartDate(dto.getStartDate());
        season.setEndDate(dto.getEndDate());
        season.setChampionAccount(championAccount);

        return season;
    }

    public SeasonResponseDTO toDto(Season entity) {
        SeasonResponseDTO dto = new SeasonResponseDTO();

        dto.setId(entity.getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setChampionAccountId(entity.getChampionAccount() != null ? entity.getChampionAccount().getId() : null);

        return dto;
    }
}
