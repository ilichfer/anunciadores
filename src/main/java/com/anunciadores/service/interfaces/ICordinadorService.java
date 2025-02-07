package com.anunciadores.service.interfaces;

import com.anunciadores.dto.CoordinadorDTO;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Tdc;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface ICordinadorService {

	public List<CoordinadorDTO> findAllBetweenDates(Date fechaStart, Date fechaEnd);
}
