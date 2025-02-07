package com.anunciadores.service;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.mapper.mapperCordinador;
import com.anunciadores.model.Coordinador;
import com.anunciadores.model.Tdc;
import com.anunciadores.repository.ICoordinadorRepo;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.ITdcRepo;
import com.anunciadores.service.interfaces.ICordinadorService;
import com.anunciadores.service.interfaces.ITdcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CordinadorServiceImpl implements ICordinadorService {

	private final Logger log = LoggerFactory.getLogger(CordinadorServiceImpl.class);

	@Autowired
	private ICoordinadorRepo coordinadorRepo;

	@Autowired
	private mapperCordinador mapperCordinador;

	@Override
	public List<CoordinadorDTO> findAllBetweenDates(Date fechaStart, Date fechaEnd) {
		List<CoordinadorDTO> informesCoordinadores = mapperCordinador.listEntitytoCoordinadorDTO(coordinadorRepo.buscarHisInformes(fechaStart, fechaEnd));
		return informesCoordinadores;
	}
}
