package com.anunciadores.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.util.ResourceUtils;

@RestController
@RequestMapping("/reportes")
public class ReporteIpoController {



	@GetMapping("/reportepdf")
	public ResponseEntity<InputStreamResource> generateReport() throws Exception {
		try {

	
			/*
			 * ruta de archivo a cargar
			 */
			String propiedad = null;
			propiedad = "REPORTE_IPO";
			String rootPath = "C:\\Users\\ifvalbuena\\Documents\\";
			String documento = rootPath + "report.jrxml";
			System.err.println(documento);

			File file = ResourceUtils.getFile(documento);

			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			/*
			 * conexion con BD
			 */
			propiedad = "DATASOURCE";
			String datasource = "jdbc:mysql://156.67.72.1:3306/u217299043_desarrollo";

			propiedad = "USER";
			String user = "u217299043_gaby";

			propiedad = "PASSWORD";
			String password = "123456Gr.";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection(datasource, user, password);

			/*
			 * envio de parametros para consultas sql
			 */
			Map<String, Object> parameters = new HashMap<>();

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conexion);

			byte[] output = JasperExportManager.exportReportToPdf(jasperPrint);

			InputStream targetStream = ByteSource.wrap(output).openStream();
		
			ByteArrayInputStream stream = (ByteArrayInputStream) targetStream;
			HttpHeaders headersResul = new HttpHeaders();
			headersResul.add("Content-Disposition", "attachment; filename=reporteMVO.pdf");

			return ResponseEntity.ok().headers(headersResul).body(new InputStreamResource(stream));

		} catch (Throwable e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}


}
