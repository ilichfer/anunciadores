package com.anunciadores.service.interfaces;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Consolidacion;
import com.anunciadores.model.EstudioPersona;
import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Persona;

import java.util.List;


public interface IMenuService {
	public List<PermisosMenu> findAllPermisosMenu(int idPersona);
}
