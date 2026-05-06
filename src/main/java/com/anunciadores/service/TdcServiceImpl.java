package com.anunciadores.service;

import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.ITdcService;
import com.anunciadores.util.UtilDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

@Configuration
@EnableScheduling
@Service
public class TdcServiceImpl implements ITdcService {

	private final Logger log = LoggerFactory.getLogger(TdcServiceImpl.class);

	@Autowired
	private ITdcRepo TdcRepository;

	@Autowired
	private IPersonaRepo personaRepository;

	@Autowired
	private UtilDate utilDate;

	@Override
	@Transactional
	public Tdc save(Date fechaCreacion, Tdc tdc) {
		try {
			List<Tdc> tdcDto = TdcRepository.findAllByDateAndPersona(fechaCreacion, tdc.getIdPersona());
			if (tdcDto.isEmpty() || tdcDto.size() <= 0  ){
				Tdc newtdc =  TdcRepository.save(tdc);
			}else{
				throw new RuntimeException();
			}

		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return tdc;
	}

	@Override
	public Tdc saveTcdImage( String urlCloudflare, Integer idPersona) {
		   try {
			   Tdc saveTcd = new Tdc();
			   java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd"));
			   saveTcd.setFechaCreacion(sqlDate);
			   saveTcd.setIdPersona(idPersona);
			   saveTcd.setUrlImage(urlCloudflare);

			   return TdcRepository.save(saveTcd);

		   } catch (Exception e) {
			   e.printStackTrace();
			   throw new RuntimeException(e);
		   }
	}

	@Override
	public Tdc getById(int id) {
		Optional<Tdc> tdsDto = TdcRepository.findById(id);
		if (tdsDto.isPresent()){
			return tdsDto.get();
		}
		return new Tdc();
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
		log.info("fecha a buscar: "+fecha);
		log.info("idPersona a buscar: "+idPersona);
		List<Tdc> cantidadTdc = TdcRepository.findAllByDateAndPersona(fecha, idPersona);
		log.info("cantidadTdc: " + cantidadTdc);
		if (cantidadTdc.size() <= 0){
			return true;
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public List<TdcReporteDto> findAllBetweenDates(Date fechaStart, Date fechaEnd) throws ParseException {
		List<TdcReporteDto> listareporte = new ArrayList<>();


		List<Object> objects = TdcRepository.findAllBetweenDates(fechaStart,fechaEnd);
		for (int j = 0; j < objects.size(); j++) {
			Object[] object = (Object[]) objects.get(j);
			TdcReporteDto dto = new TdcReporteDto();
			dto.setNombre(object[0].toString());
			dto.setCantidadEntregados( Integer.parseInt( object[1].toString()));
			dto.setIdPersona( Integer.parseInt( object[2].toString()));
			dto.setPorcentajeCumplimiento(calcularPorcentajeCumplimiento( Integer.parseInt( object[1].toString())));
			listareporte.add(dto);
		}

		return listareporte;
	}

	@Override
	public TdcReporteDto findAllBetweenDatesAndPerson( Integer idPersona) throws ParseException {
		TdcReporteDto dto = new TdcReporteDto();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateBog = utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(dateBog, dtf);

		int monthDays = ld.lengthOfMonth();
		int year = ld.getYear();
		int month = ld.getMonthValue();

		String fechainicial = year + "-" + month + "-1";
		String fechaFinal = year + "-" + month + "-" + monthDays;

		Date date1 = sdf.parse(fechainicial);
		Date date2 = sdf.parse(fechaFinal);

		Optional<Object> objects = TdcRepository.findAllBetweenDatesAndPerson(date1,date2,idPersona);

		Object[] object = (Object[]) objects.get();
				if(object[0] != null) {

				dto.setNombre(object[0].toString());
				dto.setCantidadEntregados(Integer.parseInt(object[1].toString()));
				dto.setIdPersona(Integer.parseInt(object[2].toString()));
				dto.setPorcentajeCumplimiento(calcularPorcentajeCumplimiento(Integer.parseInt(object[1].toString())));
			}

		return dto;
	}

	private Double calcularPorcentajeCumplimiento(int cantidadRegistros) throws ParseException {
		// 1. Obtener la fecha del primer registro

		String dateBog = utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(dateBog, dtf);

		int diaDelMes = ld.getDayOfMonth();


		// 3. Contar cuántos registros reales existen en ese rango
		//long registrosRealizados = repository.countByUsuarioId(usuarioId);

		// 4. Calcular porcentaje
		return (double) cantidadRegistros / diaDelMes * 100;
	}




	private TdcDto mapTdcDto(Tdc tdc){

		TdcDto dto = new TdcDto();
		dto.setId(tdc.getId());
		dto.setTdc(tdc.getTdc());
		dto.setFechaCreacion(tdc.getFechaCreacion());
		dto.setNombredocumento(tdc.getNombredocumento());
		dto.setUrlImage(tdc.getUrlImage());
		try {
			dto.setPersona(personaRepository.findById(tdc.getIdPersona()).get());
		}catch (Exception e){
			e.printStackTrace();
		}


		return dto;
	}

	@Override
	public List<TdcDto> findAllBetweenDatesByPersona(Date fechaStart, Date fechaEnd, int idPersona) {
		List<TdcDto> listaDto = new ArrayList<>();


		List<Tdc> listaTdcPersona = TdcRepository.findAllBetweenDatesByPersona(fechaStart,fechaEnd,idPersona);
		listaTdcPersona.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
		return listaDto;
	}

	@Override
	public List<TdcDto> findAlltcdByPersona(int idPersona) throws ParseException {
		List<TdcDto> listaDto = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateBog = utilDate.cargarFechaBogotaConParametro("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(dateBog, dtf);

		int monthDays = ld.lengthOfMonth();
		int year = ld.getYear();
		int month = ld.getMonthValue();

		String fechainicial = year + "-" + month + "-1";
		String fechaFinal = year + "-" + month + "-" + monthDays;

		Date date1 = sdf.parse(fechainicial);
		Date date2 = sdf.parse(fechaFinal);




		List<Tdc> listaTdcPersona = TdcRepository.findAllBetweenDatesByPersona(date1,date2,idPersona);
		listaTdcPersona.forEach(tdc -> listaDto.add(mapTdcDto(tdc)));
		return listaDto;
	}

	@Override
	public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}



	@Scheduled(fixedRate = 21600000) // 6 horas en milisegundos
	public void buscarTDCRepetidos() throws ParseException {
		Date fechaactual = utilDate.cargarfechaActualBogotaDate();

		List<Persona> listP = personaRepository.findUsuarios();
		for (Persona p:listP) {
			List<Tdc> tdcPersona = 	TdcRepository.findAllByDateAndPersona(fechaactual, p.getId());
			if(tdcPersona.size() > 1){
				for (int i = 1; i < tdcPersona.size() ; i++) {
					TdcRepository.delete(tdcPersona.get(i));
				}
			}
		}
	}



}
