package com.anunciadores.controller;

import java.math.BigDecimal;
import java.text.ParseException;
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

		Curso curso = cursoService.findProductoById(idCurso);
		pagoList = pagoService.findPagosByIdCurso(idPersona, idCurso);
		int pagoTotal = 0;
		BigDecimal big = new BigDecimal(0);
		if (big.compareTo(BigDecimal.ZERO) == 1) {
			System.out.println("==================== entro a metodo zero");
		}
		
		for (Pago pago : pagoList) {
			pagoTotal = pagoTotal +pago.getValor();
		}
		model.addAttribute("pagos", pagoList);
		model.addAttribute("curso", curso);
		model.addAttribute("pagoTotal", pagoTotal);
		return "pagos";

	}

	@PostMapping("/savePago")
	public String save(@ModelAttribute Pago pago) throws ParseException{
		String url = "redirect:/404.html";
		if (pago != null) {
			Pago pagoSave = pagoService.save(pago);
			url = "redirect:/index2.html";
		}
		return url;
	}

	@PostMapping("/eliminarPago")
	public String deleteProductoById(@ModelAttribute Pago pago, HttpServletResponse response, Model model) {
		pagoService.delete(pago);
		return "redirect:/listar";

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

}