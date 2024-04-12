package com.anunciadores.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Pago;


@Service
public class PagoRepoImpl{

	@Autowired
	JdbcTemplate jdbcTemplate;


	public List<Pago> findPagosByIdCurso(int idpersona,Integer idcurso) {
		StringBuilder sql = new StringBuilder();
//		Pago retorno = new Pago();
		List<Pago> pagoList = new ArrayList<Pago>();
		try {
			sql.append("select p.* from pago p \n" + 
					"join curso c on p.id_curso = c.id \n" + 
					"where c.id =" + idcurso +" and p.idpersona = " +idpersona);

			/*retorno = */jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Pago>() {
				@Override
				public Pago extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						pagoList.add(new Pago(rs.getInt("id"), rs.getInt("idpersona"), rs.getString("fecha_pago"),
								rs.getInt("valor"), rs.getInt("id_curso")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagoList;
	}
	
	
	public List<PagoDto> reportePagosCursos(int idCurso) {
		StringBuilder sql = new StringBuilder();
//		Pago retorno = new Pago();
		List<PagoDto> pagoList = new ArrayList<PagoDto>();
		try {
			sql.append("select SUM(pa.valor) as suma, pa.id_curso, pa.idpersona, c.valortotal from curso c " + 
					" join pago pa on c.id = pa.id_curso " + 
					" join persona pe on pa.idpersona = pe.id " + 
					" where pa.id_curso = "+idCurso+
					" GROUP by pa.idpersona " );

			/*retorno = */jdbcTemplate.query(sql.toString(), new ResultSetExtractor<PagoDto>() {
				@Override
				public PagoDto extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						pagoList.add(new PagoDto(rs.getInt("idpersona"),rs.getInt("id_curso"),rs.getInt("valortotal"),rs.getInt("suma")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagoList;
	}
	
	public List<ReportePagoDto> reportePagos() {
		StringBuilder sql = new StringBuilder();
//		Pago retorno = new Pago();
		List<ReportePagoDto> pagoList = new ArrayList<ReportePagoDto>();
		try {
			sql.append("select c.id ,c.nombrecurso ,c.valortotal ,pe.nombre, "
					+ "(select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) as pagos," + 
					" case  when (select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) is null then c.valortotal " + 
					"  when (select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id) is not null "+
				    " then valortotal-(select sum(pa.valor) from pago pa where pa.idpersona = pe.id and pa.id_curso = c.id)"+
				    " else Null"+
				    " end adeuda" + 
					" from curso c "+
					" join inscripciones i on c.id  = i.id_curso " +
					" join persona pe on i.id_persona = pe.id " +
					" order by c.nombrecurso asc " );

			/*retorno = */jdbcTemplate.query(sql.toString(), new ResultSetExtractor<PagoDto>() {
				@Override
				public PagoDto extractData(ResultSet rs) throws SQLException, DataAccessException {

					while (rs.next())
						pagoList.add(new ReportePagoDto(rs.getInt("id"),rs.getString("nombrecurso"),rs.getString("nombre"),rs.getInt("valortotal"),rs.getInt("pagos"),rs.getInt("adeuda")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagoList;
	}

}
