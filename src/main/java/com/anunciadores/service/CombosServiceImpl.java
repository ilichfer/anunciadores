package com.anunciadores.service;

import com.anunciadores.dto.ItemCombo;
import com.anunciadores.dto.ListasCombos;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.enums.ECombos;
import com.anunciadores.mapper.mapperParametros;
import com.anunciadores.model.ParametrosCombos;
import com.anunciadores.model.Tdc;
import com.anunciadores.repository.IParametrosRepo;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.ITdcRepo;
import com.anunciadores.service.interfaces.ICombos;
import com.anunciadores.service.interfaces.ITdcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CombosServiceImpl implements ICombos {


	@Autowired
	private IParametrosRepo parametrosRepo;

	@Autowired
	private mapperParametros mapperParametros;

	private final Logger log = LoggerFactory.getLogger(CombosServiceImpl.class);

	@Override
	public ListasCombos listarParametros() {
		ListasCombos listas = new ListasCombos();
		List<ItemCombo> listaGenero = mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.GENERO.toString()));
		List<ItemCombo> listaestadoCivil = mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.ESTADOCIVIL.toString()));
		List<ItemCombo> listaEscolaridad = mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.ESCOLARIDAD.toString()));
		List<ItemCombo> listaDocumentos = mapperParametros.listEntitytoListDto(parametrosRepo.findByGrupo(ECombos.DOCUMENTO.toString()));
		listas.setListaGenero(listaGenero);
		listas.setEstadoCivil(listaestadoCivil);
		listas.setListaEscolaridad(listaEscolaridad);
		listas.setListaDocuemntos(listaDocumentos);
		return listas;
	}
}
