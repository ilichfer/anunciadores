package com.anunciadores.service.interfaces;

import com.anunciadores.dto.MensajesDTO;
import com.anunciadores.model.Sugerencia;

import java.util.List;


public interface IMensajeService {
	public List<MensajesDTO> buscarMensaje(Integer idPersona);
	public List<MensajesDTO> buscarTodosMensaje(Integer idPersona);
	public MensajesDTO buscarMensajeXId(Integer idMensaje);

	public MensajesDTO guardarMensaje(MensajesDTO mensaje);

	public void enviarTodosMensajes(MensajesDTO mensaje);
}
