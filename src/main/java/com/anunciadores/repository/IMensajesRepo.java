package com.anunciadores.repository;

import com.anunciadores.model.PermisosMenu;
import com.anunciadores.model.Mensajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMensajesRepo extends JpaRepository<Mensajes, Integer>{

    @Query("select m from Mensajes m where m.remitente.id = :idPersona" +
            " and m.activo = true ")
    public List<Mensajes> findByIdPersona(int idPersona);

    @Query("select m from Mensajes m where m.destinatario.id = :idPersona")
    public List<Mensajes> findMensajesByIdPersona(int idPersona);

    @Query("select m from Mensajes m where m.destinatario.id = :idPersona" +
            " and m.activo = true ")
    public List<Mensajes> mesajesSinLeerByIdPersona(int idPersona);

}
