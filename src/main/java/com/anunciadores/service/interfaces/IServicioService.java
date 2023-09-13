package com.anunciadores.service.interfaces;

import com.anunciadores.dto.MinisterioDto;
import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Ministerio;
import com.anunciadores.model.Tdc;

import java.util.Date;
import java.util.List;


public interface IServicioService {

	public List<Ministerio> getAll();

	public List<MinisterioDto> getPositionByidMinisterio(int idMinisterio);

}
