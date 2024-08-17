package com.gsl.glasgowsocialleague.core.mapper;

import com.gsl.glasgowsocialleague.core.model.game.GameEntity;
import com.gsl.glasgowsocialleague.web.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {


    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);


    GameDto toDto(GameEntity gameEntity);


    GameEntity toEntity(GameDto gameDto);
}
