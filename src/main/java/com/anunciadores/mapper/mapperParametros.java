package com.anunciadores.mapper;

import com.anunciadores.dto.ItemCombo;
import com.anunciadores.dto.PersonaConsolidacionDto;
import com.anunciadores.model.ParametrosCombos;
import com.anunciadores.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface mapperParametros {
    mapperParametros INSTANCE = Mappers.getMapper(mapperParametros.class);

    List<ItemCombo> listEntitytoListDto(List<ParametrosCombos> entity);

}
