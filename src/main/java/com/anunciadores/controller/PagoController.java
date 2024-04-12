package com.anunciadores.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PagoDto;
import com.anunciadores.dto.ReportePagoDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Pago;
import com.anunciadores.service.interfaces.ICursoService;
import com.anunciadores.service.interfaces.IPagoService;

@Controller
@RequestMapping
public class PagoController {
	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private ICursoService cursoService;

	List<Pago> pagoList;

	@GetMapping("/listarPagos")
	public String Cursos(HttpServletResponse response, Model model) {
		pagoList = pagoService.findAll();
		model.addAttribute("cursos", pagoList);
		return "cursos";
	}

	@GetMapping("/buscarPagosCurso")
	public String buscarPagosCurso(@RequestParam int idPersona, @RequestParam int idCurso, Model model) {

		Curso curso = cursoService.findCursoById(idCurso);
		pagoList = pagoService.findPagosByIdCurso(idPersona, idCurso);
		int pagoTotal = 0;
		int adeuda = 0;
		boolean  validarPago=false;
		
		for (Pago pago : pagoList) {
			pagoTotal = pagoTotal +pago.getValor();
		}
		
		adeuda =curso.getValorTotal() -pagoTotal;
		if (adeuda > 0) {
			validarPago=true;
		}
		model.addAttribute("pagos", pagoList);
		model.addAttribute("curso", curso);
		model.addAttribute("pagoTotal", pagoTotal);
		model.addAttribute("adeuda", adeuda);
		model.addAttribute("validarPago", validarPago);
		model.addAttribute("idPersona", idPersona);
		model.addAttribute("idCurso", idCurso);
		return "pagos";

	}

	@PostMapping("/savePago")
	public String save(@ModelAttribute Pago pago, Model model) throws ParseException{
		String url = "redirect:/404.html";
		if (pago != null) {
			
			Date myDate = new Date();
			pago.setFechaPago(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
			Pago pagoSave = pagoService.save(pago);
			List<Curso> CursosList = cursoService.findAll();
			model.addAttribute("cursos", CursosList);
			url = "cursos";
		}
		return url;
	}

	@GetMapping("/eliminarPago")
	public String deleteProductoById(@ModelAttribute Pago pago, HttpServletResponse response, Model model) {
		pagoService.delete(pago);
		return "redirect:/listarCursos";

	}

	@PostMapping("/editarPago")
	public String editarCursoById(@ModelAttribute CursoDto curso, HttpServletResponse response, Model model)
			throws ParseException {
		Curso cursoMostrar = new Curso();
//		pagoService.save(curso);
		System.out.println("fechaInicio para modificacion " + curso.getFechaInicio());
		System.out.println("fechaFin para modificacion " + curso.getFechaFin());
		cursoMostrar.setId(curso.getId());
//		cursoMostrar.setFechaInicio(pagoService.ParseFecha(curso.getFechaInicio()));
//		cursoMostrar.setFechaFin(pagoService.ParseFecha(curso.getFechaFin()));
		cursoMostrar.setNombreCurso(curso.getNombreCurso());
		cursoMostrar.setValorTotal(curso.getValorTotal());

//		curso.setFechaInicio(pagoService.formatFecha(curso.getFechaInicio()));
//		curso.setFechaFin(pagoService.formatFecha(curso.getFechaFin()));
		model.addAttribute("curso", curso);

		return "edit-curso";

	}
	
	
	@GetMapping("/reportePagosCursos")
	public String reportePagosCursos(Model model) {

 		List<PagoDto> pagoList = pagoService.reportePagosCursos(1);
		int pagoTotal = 0;
		model.addAttribute("pagos", pagoList);
		model.addAttribute("pagoTotal", pagoTotal);
		return "pagos";

	}
	
	@GetMapping("/reportePagos")
	public String reportePagos(HttpServletResponse response, Model model) {
		List<ReportePagoDto> pagosList = pagoService.reportePagos();
		model.addAttribute("pagos", pagosList);
		return "reportePagos";
	}

}