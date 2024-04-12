package com.anunciadores.service.interfaces;

import java.util.List;

import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Pago;


public interface IPagoService {
	public List<Pago> findAll();

	public Pago save(Pago pago);

	public List<Pago> findPagosByIdCurso(int idpersona,int idCurso);
	
	public Pago delete(Pago pago);
	
	public List<PagoDto> reportePagosCursos(int idCurso);
	
	public List<ReportePagoDto> reportePagos();
	
}
