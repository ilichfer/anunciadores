package com.anunciadores.repository;

import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPermisosRepo extends JpaRepository<PermisosMenu, Integer>{

    @Query()
    public List<PermisosMenu> findByIdPersona(int idPersona);

    public PermisosMenu findByIdPersonaAndNombreBotonMenu(int idPersona,String nombreBoton);

}
