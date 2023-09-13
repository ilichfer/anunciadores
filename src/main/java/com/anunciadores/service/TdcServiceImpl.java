package com.anunciadores.service;

import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.ITdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TdcServiceImpl implements ITdcService {

	@Autowired
	private ITdcRepo TdcRepository;

	@Autowired
	private IPersonaRepo personaRepository;

	@Override
	public Tdc save(Tdc tdc) {
		try {
			Tdc newtdc =  TdcRepository.save(tdc);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return tdc;
	}

	@Override
	public String getById(int id) {
		Tdc tdsDto = TdcRepository.getById(id);
		return tdsDto.getTdc();
	}

	@Override
	public Tdc getTdcById(int id) {
		 Tdc tcdDto = TdcRepository.getById(1);
		return tcdDto;
	}

	@Override
	public List<TdcDto> getAll() {
		List<TdcDto> listaDto= new ArrayList<>();
		List<Tdc> lisTdc =TdcRepository.findAll();
		lisTdc.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
		return listaDto;
	}

	@Override
	public List<TdcDto> getTdcByFecha(Date fecha) {
		List<TdcDto> listaDto= new ArrayList<>();
		List<Tdc> lisTdc =TdcRepository.findAllByDate(fecha);
		lisTdc.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
		return listaDto;
	}

	@Override
	public boolean getTdcByFechaAndPersona(Date fecha, int idPersona) {
	try {
		List<Tdc> cantidadTdc = TdcRepository.findAllByDateAndPersona(fecha, idPersona);
		if (cantidadTdc.size() <= 0){
			return true;
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public List<TdcReporteDto> findAllBetweenDates(Date fechaStart, Date fechaEnd) {
		List<TdcReporteDto> listareporte = new ArrayList<>();


		List<Object> objects = TdcRepository.findAllBetweenDates(fechaStart,fechaEnd);
		for (int j = 0; j < objects.size(); j++) {
			Object[] object = (Object[]) objects.get(j);
			TdcReporteDto dto = new TdcReporteDto();
			dto.setNombre(object[0].toString());
			dto.setCantidadEntregados( Integer.parseInt( object[1].toString()));
			dto.setIdPersona( Integer.parseInt( object[2].toString()));
			listareporte.add(dto);
		}

		return listareporte;
	}

	private TdcDto mapTdcDto(Tdc tdc){

		TdcDto dto = new TdcDto();
		dto.setId(tdc.getId());
		dto.setTdc(tdc.getTdc());
		dto.setFechaCreacion(tdc.getFechaCreacion());
		dto.setNombredocumento(tdc.getNombredocumento());
		dto.setPersona(personaRepository.findById(tdc.getIdPersona()).get());

		return dto;
	}

	@Override
	public List<TdcDto> findAllBetweenDatesByPersona(Date fechaStart, Date fechaEnd, int idPersona) {
		List<TdcDto> listaDto = new ArrayList<>();


		List<Tdc> listaTdcPersona = TdcRepository.findAllBetweenDatesByPersona(fechaStart,fechaEnd,1);
		listaTdcPersona.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
		return listaDto;
	}

}
