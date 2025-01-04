package com.anunciadores.mapper;

import com.anunciadores.dto.MensajesDTO;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Mensajes;
import com.anunciadores.model.Persona;
import com.anunciadores.util.UtilDate;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface mapperMensaje {

    @Autowired
    UtilDate utilDate = new UtilDate();
    mapperMensaje INSTANCE = Mappers.getMapper(mapperMensaje.class);
    MensajesDTO EntityToMensajesDTO(Mensajes entity);

    Mensajes MensajesDTOToEntity(MensajesDTO dto);

    List<MensajesDTO> listEntityToMensajesDTO(List<Mensajes> entity);
   
}
