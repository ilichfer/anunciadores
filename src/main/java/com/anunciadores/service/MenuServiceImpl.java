package com.anunciadores.service;

import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Pago;
import com.anunciadores.model.ParamMenu;
import com.anunciadores.model.PermisosMenu;
import com.anunciadores.repository.IParamMenuRepo;
import com.anunciadores.repository.IPermisosRepo;
import com.anunciadores.repository.PagoRepoImpl;
import com.anunciadores.service.interfaces.IMenuService;
import com.anunciadores.service.interfaces.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {


	@Autowired
	private IPermisosRepo permisosMenuRepo;
	
	@Autowired
	private IParamMenuRepo paramMenuRepo;


	@Override
	public List<PermisosMenu> findAllPermisosMenu(int idPersona) {
		List<PermisosMenu> listaResposePermisos = new ArrayList<>();

		List<PermisosMenu> listaPersmisos = permisosMenuRepo.findByIdPersona(idPersona);

		List<ParamMenu> listaMenu = paramMenuRepo.findAll();
		listaMenu.forEach( p -> listaResposePermisos.add(comparaListas(listaPersmisos,p)));

		return listaResposePermisos;
	}

	private PermisosMenu comparaListas (List<PermisosMenu> permiso,ParamMenu opcionMenu){
		PermisosMenu permisoNoencontrado = new PermisosMenu();
		for (PermisosMenu perm:permiso) {
			if (perm.getNombreBotonMenu().equals(opcionMenu.getNombreBotonMenu())){
				return perm;
			}
			permisoNoencontrado.setNombreBotonMenu(opcionMenu.getNombreBotonMenu());
			permisoNoencontrado.setIdPersona(perm.getIdPersona());
			permisoNoencontrado.setEstado("false");
			permisoNoencontrado.setMenu(opcionMenu);
		}
		return permisoNoencontrado;
	}
}
