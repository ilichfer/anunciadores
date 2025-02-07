package com.anunciadores.repository;

import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPermisosRepo extends JpaRepository<PermisosMenu, Integer>{


    @Query()
    public List<PermisosMenu> findByIdPersona(int idPersona);

    public Optional<PermisosMenu> findByIdPersonaAndNombreBotonMenu(int idPersona, String nombreBoton);

    @Query(value = "select p.* from permisos_menu pm" +
            " join persona p on pm.id_persona  = p.id " +
            " where p.id = :idPersona" +
            " and id_menu  = 9", nativeQuery = true )
    public Persona findByIdPersonaAndIdMenu(int idPersona);



}
