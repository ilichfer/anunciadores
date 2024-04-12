package com.anunciadores.service.interfaces;

import com.anunciadores.dto.TdcDto;
import com.anunciadores.dto.TdcReporteDto;
import com.anunciadores.model.Tdc;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface ITdcService {
	public Tdc save(Tdc tdc);

	public Tdc getById(int id);

	public Tdc getTdcById(int id);

	public List<TdcDto> getAll();

	public List<TdcDto> getTdcByFecha(Date fecha);

	public boolean getTdcByFechaAndPersona(Date fecha, int idPersona);

	public List<TdcReporteDto> findAllBetweenDates(Date fechaStart, Date fechaEnd);

	List<TdcDto> findAllBetweenDatesByPersona(Date fechaStart, Date fechaEnd, int idPersona);

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException;
}
