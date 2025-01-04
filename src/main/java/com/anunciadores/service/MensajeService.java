package com.anunciadores.service;

import com.anunciadores.dto.MensajesDTO;
import com.anunciadores.mapper.mapperMensaje;
import com.anunciadores.model.Mensajes;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Sugerencia;
import com.anunciadores.repository.IMensajesRepo;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.ISugerenciaRepo;
import com.anunciadores.service.interfaces.IMensajeService;
import com.anunciadores.service.interfaces.ISugerenciaService;
import com.anunciadores.util.UtilDate;
import org.mapstruct.AfterMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService implements IMensajeService {
    private IMensajesRepo mensajesRepo;

    @Autowired
    private UtilDate utilDate;

    @Autowired
    private mapperMensaje mapperMensaje;

    @Autowired
    private IPersonaRepo personaRepo;

    public MensajeService(IMensajesRepo mensajesRepo) {
        this.mensajesRepo = mensajesRepo;
    }

    @Override
    public List<MensajesDTO> buscarMensaje(Integer idPersona) {
       return mapperMensaje.listEntityToMensajesDTO(mensajesRepo.findMensajesByIdPersona(idPersona));
    }

    @Override
    public List<MensajesDTO> buscarTodosMensaje(Integer idPersona) {
        List<MensajesDTO> lista =  mapperMensaje.listEntityToMensajesDTO(mensajesRepo.findMensajesByIdPersona(idPersona));
        for (MensajesDTO dto :lista) {
            if (dto.getFechaRegistro() != null){
                dto.setFechaMostrar(utilDate.convertDateToString(dto.getFechaRegistro()));
            }
        }
        return lista;
    }

    @Override
    public MensajesDTO buscarMensajeXId(Integer idMensaje) {
        Mensajes mensajeSave = mensajesRepo.findById(idMensaje).get();
        mensajeSave.setActivo(false);
        return mapperMensaje.EntityToMensajesDTO(mensajesRepo.save(mensajeSave));

    }

    @Override
    public MensajesDTO guardarMensaje(MensajesDTO mensaje) {

        return mapperMensaje.EntityToMensajesDTO(mensajesRepo.save(mapperMensaje.MensajesDTOToEntity(mensaje)));

    }

    @Override
    public void enviarTodosMensajes(MensajesDTO mensaje) {
        List<Persona> personas = personaRepo.findUsuarios();
        for (Persona per : personas) {
            mensaje.setDestinatario(per);
            mensajesRepo.save(mapperMensaje.MensajesDTOToEntity(mensaje));
        }
    }
}
