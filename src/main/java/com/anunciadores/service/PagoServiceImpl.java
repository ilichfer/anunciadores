package com.anunciadores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anunciadores.model.Pago;
import com.anunciadores.repository.IPagoRepo;
import com.anunciadores.repository.PagoRepoImpl;
import com.anunciadores.service.interfaces.IPagoService;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private IPagoRepo PagoRepository;
	
	@Autowired
	private PagoRepoImpl pagoRepo;

	@Override
	public List<Pago> findAll() {
		return PagoRepository.findAll();
	}

	@Override
	public Pago save(Pago pago) {
		
		return PagoRepository.save(pago);
	}

	@Override
	public List<Pago> findPagosByIdCurso(int idpersona,int idcurso) {
		List<Pago> ListaPago = pagoRepo.findPagosByIdCurso(idpersona,idcurso);
		return ListaPago;
	}

	@Override
	public Pago delete(Pago Pago) {
		PagoRepository.deleteById(Pago.getId());
		return Pago;
	}

}
