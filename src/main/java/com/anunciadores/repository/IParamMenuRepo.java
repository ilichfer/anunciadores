package com.anunciadores.repository;

import com.anunciadores.model.ParamMenu;
import com.anunciadores.model.ParamSubMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParamMenuRepo extends JpaRepository<ParamMenu, Integer>{

    public ParamMenu findByNombreBotonMenu(String nombreBoton);
}
