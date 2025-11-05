package com.anunciadores.service;

import com.anunciadores.dto.*;
import com.anunciadores.enums.ECombos;
import com.anunciadores.mapper.mapperParametros;
import com.anunciadores.model.*;
import com.anunciadores.model.PersonaMinisterio;
import com.anunciadores.repository.*;
import com.anunciadores.service.interfaces.IReserveHourService;
import com.anunciadores.service.interfaces.IServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReserveHourServiceImpl implements IReserveHourService {

	private Logger LOGGER = LoggerFactory.getLogger(ReserveHourServiceImpl.class);

	@Override
	public List<TimeSlot> reserveSlot() {
		List<TimeSlot> slots = new ArrayList<>();
		LocalDate today = LocalDate.now();
		LocalTime start = LocalTime.of(7, 0); // ⏰ Inicio: 7:00 AM
		int totalBlocks = 48;

		for (int i = 0; i < totalBlocks; i++) {
			LocalTime blockStart = start.plusMinutes(i * 30);
			LocalDate blockDate = today;

			TimeSlot slot = new TimeSlot();
			slot.setDiaSiguiente(false);
			// Si la hora pasa de medianoche, ajustamos la fecha al día siguiente
			if (blockStart.isBefore(start)) {
				blockDate = today.plusDays(1);
				slot.setDiaSiguiente(true);
			}

			slot.setDate(blockDate);
			slot.setStartTime(blockStart);
			slot.setReserved(false);
			slots.add(slot);

			// Verifica si ya existe el bloque
			/*boolean exists = timeSlotRepository.existsByDateAndStartTime(blockDate, blockStart);
			if (!exists) {
				TimeSlot slot = new TimeSlot();
				slot.setDate(blockDate);
				slot.setStartTime(blockStart);
				slot.setReserved(false);
				slots.add(slot);
			}
			*/

		}



		//return timeSlotRepository.saveAll(slots);
		return slots;
	}

}
