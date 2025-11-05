package com.anunciadores.controller;


import com.anunciadores.dto.ResponseTelegram;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.model.Persona;
import com.anunciadores.model.TimeSlot;
import com.anunciadores.repository.ITimeSlotRepo;
import com.anunciadores.service.interfaces.IReserveHourService;
import com.anunciadores.service.interfaces.ITelegramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class ReserveHourController {

	@Autowired
	private ITimeSlotRepo timeSlotRepository;

	@Autowired
	private IReserveHourService reserveHourService;
/*
	@PostMapping("/reserve")
	public String reserveSlot(@RequestParam Long slotId, Principal principal) {
		TimeSlot slot = timeSlotRepository.findById(slotId).orElseThrow();
		if (slot.isReserved()) {
			return "redirect:/schedule?error=ocupado";
		}
		slot.setReserved(true);
		slot.setReservedBy(userRepository.findByUsername(principal.getName()));
		timeSlotRepository.save(slot);
		return "redirect:/schedule";
	}

 */

	@GetMapping("/reserveslot")
	public String reserveslot(Model model) throws JsonMappingException, JsonProcessingException {
		List<TimeSlot> slots = reserveHourService.reserveSlot();
		model.addAttribute("slots", slots);
		return "reserve";
	}

}
