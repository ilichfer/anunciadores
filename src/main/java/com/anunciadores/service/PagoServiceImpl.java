package com.anunciadores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Pago;
import com.anunciadores.repository.IPagoRepo;
import com.anunciadores.repository.PagoRepoImpl;
import com.anunciadores.service.interfaces.IPagoService;

@Service
public class PagoServiceImpl implements IPagoService {


	@Autowired
	private IPagoRepo PagoRepository;
	
	@Autowired
	private PagoRepoImpl pagoDao;

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
		List<Pago> ListaPago = pagoDao.findPagosByIdCurso(idpersona,idcurso);
		return ListaPago;
	}

	@Override
	public Pago delete(Pago Pago) {
		PagoRepository.deleteById(Pago.getId());
		return Pago;
	}

	@Override
	public List<PagoDto> reportePagosCursos(int idCurso) {
		return pagoDao.reportePagosCursos(idCurso);
	}

	@Override
	public List<ReportePagoDto> reportePagos() {
		return pagoDao.reportePagos();
		
	}

}
