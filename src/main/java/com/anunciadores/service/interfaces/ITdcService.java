package com.anunciadores.service.interfaces;

import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Tdc;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface ITdcService {
	public Tdc save(Date fechaCreacion, Tdc tdc);

	public Tdc saveTcdImage(String urlCloudflare, Integer idPersona );

	public Tdc getById(int id);

	public Tdc getTdcById(int id);

	public List<TdcDto> getAll();

	public List<TdcDto> getTdcByFecha(Date fecha);

	public boolean getTdcByFechaAndPersona(Date fecha, int idPersona);

	public List<TdcReporteDto> findAllBetweenDates(Date fechaStart, Date fechaEnd) throws ParseException;

	public TdcReporteDto findAllBetweenDatesAndPerson(Integer idPersona) throws ParseException;

	List<TdcDto> findAllBetweenDatesByPersona(Date fechaStart, Date fechaEnd, int idPersona);

	List<TdcDto> findAlltcdByPersona(int idPersona) throws ParseException;


	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException;
}
