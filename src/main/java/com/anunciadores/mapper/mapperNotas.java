package com.anunciadores.mapper;

import com.anunciadores.dto.NotasCursoDTO;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.model.Persona;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface mapperNotas {
    mapperNotas INSTANCE = Mappers.getMapper(mapperNotas.class);

    NotasCursoDTO EntityToNotasCursoDTO(NotasCurso entity);

}
