package com.anunciadores.mapper;

import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface mapperConsolidacion {
    mapperConsolidacion INSTANCE = Mappers.getMapper(mapperConsolidacion.class);

    PersonaConsolidacionDto EntitytoConsolidacionDto(Persona entity);
    List<PersonaConsolidacionDto> liitEntitytoConsolidacionDto(List<Persona> entity);
}
