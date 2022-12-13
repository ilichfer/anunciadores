package com.anunciadores.service.interfaces;

import java.util.List;

import com.anunciadores.model.Actividad;
import com.anunciadores.model.Mesa;
import com.anunciadores.model.Persona;


public interface IMesasService {
	public List<Actividad> listarActiviades();

	public List<Persona> BuscarPersonasSinMesa(Mesa Mesa);

	public void delete(Mesa Mesa);
}
