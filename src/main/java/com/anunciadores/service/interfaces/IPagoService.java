package com.anunciadores.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.anunciadores.model.Pago;


public interface IPagoService {
	public List<Pago> findAll();

	public Pago save(Pago pago);

	public List<Pago> findPagosByIdCurso(int idpersona,int idCurso);
	
	public Pago delete(Pago pago);
	
}
