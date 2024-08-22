package com.anunciadores.repository;

import com.anunciadores.dto.ItemCombo;
import com.anunciadores.model.ParametrosCombos;
import com.anunciadores.model.Tdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IParametrosRepo extends JpaRepository<ParametrosCombos, Integer>{


    public List<ParametrosCombos> findByGrupo(@Param("grupo") String grupo);

}
