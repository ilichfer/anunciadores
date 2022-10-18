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
						pagoList.add(new Pago(rs.getInt("id"), rs.getInt("idpersona"), rs.getDate("fechapago"),
								rs.getInt("valor"), rs.getInt("id_curso")));

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagoList;
	}

}
