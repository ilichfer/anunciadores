package com.anunciadores.mapper;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface mapperCordinador {
    mapperCordinador INSTANCE = Mappers.getMapper(mapperCordinador.class);

    CoordinadorDTO EntitytoCoordinadorDTO(Coordinador entity);
    List<CoordinadorDTO> listEntitytoCoordinadorDTO(List<Coordinador> entity);
}
