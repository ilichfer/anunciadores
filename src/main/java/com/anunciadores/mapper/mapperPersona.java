package com.anunciadores.mapper;

import com.anunciadores.dto.NotasCursoDTO;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface mapperPersona {
    mapperPersona INSTANCE = Mappers.getMapper(mapperPersona.class);

    PersonaDto EntityToPersonaDto(Persona entity);
    List<PersonaDto> listEntityToConsolidacionDto(List<Persona> entity);
   
}
