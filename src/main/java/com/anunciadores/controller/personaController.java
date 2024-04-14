package com.anunciadores.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.ServicioListResponseDto;
import com.anunciadores.model.*;
import com.anunciadores.repository.IPersonaRepo;
import com.anunciadores.repository.IRolesRepo;
import com.anunciadores.service.interfaces.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.dto.VersiculoDto;
import com.anunciadores.repository.ConsolidacionRepoImpl;
import com.anunciadores.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
public class personaController {

	private Logger LOGGER = LoggerFactory.getLogger(personaController.class);

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IBibliaService bibliaService;
	
	@Autowired
	private IPersonaRepo personaRepoImpl;
	
	@Autowired
	private ConsolidacionRepoImpl consolidacionDao;

	@Autowired
	private IPagoService pagoService;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private IRolesRepo rolesPersonaRepo;

	List<Persona> personasList;
	List<PersonaDto> personasListDto;

	@GetMapping("/listar")
	public String personas(HttpServletResponse response, Model model) {
		personasList = personaService.findAllUsuarios();
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas ");
		model.addAttribute("usuario", true);
		model.addAttribute("asistente", false);
		return "personas";
	}
	
	@GetMapping("/listarAsistentes")
	public String listarAsistentes(HttpServletResponse response, Model model) {
		personasList = personaRepoImpl.buscarAsistentes();
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Asistentes ");
		model.addAttribute("usuario", false);
		model.addAttribute("asistente", true);
		model.addAttribute("admin", true);
		return "personas";
	}

	@GetMapping("/listarAsistentesConsolidacion")
	public String listarAsistentesConsolidacion(HttpServletResponse response, Model model) {
		personasList = personaRepoImpl.buscarAsistentes();
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Asistentes ");
		model.addAttribute("usuario", false);
		model.addAttribute("asistente", true);
		model.addAttribute("admin", true);
		return "personas";
	}
	
	@GetMapping("/listarAsistentesByUsuario")
	public String listarAsistentesByUsuario(HttpServletResponse response, Model model) {
		personasList = personaRepoImpl.buscarAsistentes();
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Asistentes ");
		model.addAttribute("usuario", false);
		model.addAttribute("asistente", true);
		model.addAttribute("admin", false);
		return "personas";
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {

		return ResponseEntity.ok(personaService.findPersonaById(id));

	}

	@PostMapping("/buscarPersona")
	public String buscarPersonaById(@ModelAttribute Persona persona,  Model model) {
		List<Persona> findPersona = new ArrayList<>();
		 findPersona.add(personaService.findPersonaByNombre(persona.getNombre()));

		model.addAttribute("personas", findPersona);
		model.addAttribute("msj", "Personas ");
		model.addAttribute("usuario", true);
		model.addAttribute("asistente", false);
		return "personas";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Persona persona, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarEmail(persona.getEmail());
		VersiculoDto dia = bibliaService.findVerseDay();
		String url = "redirect:/404.html";
		model.addAttribute("dia", dia);
		if (per == null || per.getEmail() == null) {
			Persona personaSave = personaService.save(persona);
			model.addAttribute("persona", persona);
			url = "redirect:/listar";
		}
		return url;
	}
	
	@PostMapping("/saveOut")
	public String saveOut(@ModelAttribute Persona persona, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarEmail(persona.getEmail());
		VersiculoDto dia = bibliaService.findVerseDay();
		String url = "login";
		model.addAttribute("dia", dia);
		model.addAttribute("msj", "usuario ya existe");
		if (per == null || per.getEmail() == null) {
			Persona personaSave = personaService.save(persona);
			model.addAttribute("persona", persona);
			model.addAttribute("msj", " usuario creado correctamente");
		}
		return url;
	}
	
	@GetMapping("/consutarEmail")
	public ResponseEntity<PersonaDto> consutarEmail(@RequestParam String email, HttpServletResponse response,
			Model model) throws JsonMappingException, JsonProcessingException {
		PersonaDto person = new PersonaDto();
		person = personaService.buscarEmail(email);
		model.addAttribute("admin", person.isAdmin());
		model.addAttribute("user", person.isUser());
		model.addAttribute("persona", person);

		return new ResponseEntity<PersonaDto>(person, null, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/cargarMenu")
	public String cargarMenu(@RequestParam String email, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		
		PersonaDto per = personaService.buscarEmail(email);
		VersiculoDto dia =bibliaService.findVerseDay();
		model.addAttribute("persona", per);
		model.addAttribute("admin", per.isAdmin());
		model.addAttribute("user", per.isUser());
		model.addAttribute("dia", dia);
		model.addAttribute("msj", " el usuario o contraseña es incorrecto");
		
		String url = "login";
		if (per != null) {
			url = "index";
		}
		return url;
	}
	
	
	@PostMapping("/saveAsistente")
	public String saveAsistente(@ModelAttribute Persona persona, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarByDocumento(persona.getDocumento());
		VersiculoDto dia = bibliaService.findVerseDay();
		String url = "registerAsistente";
		model.addAttribute("dia", dia);
		model.addAttribute("msj", "ya existe un asistente con el mismo numero de documento");
		if (per == null || per.getEmail() == null) {
			Persona personaSave = personaService.saveAsistente(persona);
			model.addAttribute("persona", persona);
			url = "redirect:/listarAsistentes";
		}
		return url;
	}
	@PostMapping("/saveAsistenteConsolidacion")
	public String saveAsistenteConsolidacion(@ModelAttribute Persona persona,@ModelAttribute Consolidacion consolidacion, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarByDocumento(persona.getDocumento());
		VersiculoDto dia = bibliaService.findVerseDay();
		String url = "registerAsistenteConsolidacion";
		model.addAttribute("dia", dia);
		model.addAttribute("msj", "ya existe un asistente con el mismo numero de documento");
		if (per == null || per.getNombre() == null) {
			Persona personaSave = personaService.saveAsistenteConsolidacion(persona, consolidacion);

			model.addAttribute("persona", persona);
			url = "redirect:https://web.facebook.com/IgAnunciadoresCristo";
		}
		return url;
	}

	@GetMapping("/eliminar")
	public String deleteProductoById(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
		String retorno = personaService.delete(persona);
		if (retorno.equalsIgnoreCase("usuario")) {
			return "redirect:/listar";
		}
		return "redirect:/listarAsistentes";

	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@PostMapping("/access")
	public String login(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarEmail(persona.getEmail());
		VersiculoDto dia =bibliaService.findVerseDay();
//		model.addAttribute("persona", per);
		model.addAttribute("dia", dia);
		model.addAttribute("msj", " el usuario o contraseña es incorrecto");
		
		String url = "index";
		if (per != null
				&& (persona.getEmail().equals(per.getEmail()) && persona.getPassword().equals(per.getPassword()))) {
			url = "index";
		}
		return url;
	}

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) throws JsonMappingException, JsonProcessingException {
		System.out.println("entro a login");
		System.out.println(user.toString());
		return "login";
	}
	
	@PostMapping("/login2")
	public String login2(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
//		usuarioService.loadUserByUsername(persona.getEmail());
		PersonaDto per = personaService.buscarEmail(persona.getEmail());
//		User user = new User(persona.getNombre(), persona.getPassword(), authorities)
		VersiculoDto dia =bibliaService.findVerseDay();
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			//model.addAttribute("programacion", listProgramacionMinisterio);
			model.addAttribute("programacionMin", listProgramacionMinisterio);
		}else{
			model.addAttribute("programacionMin", null);
		}

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonPersona = ow.writeValueAsString(per);
		LOGGER.info("la persona que ingreso es:  " +jsonPersona);
		//kafkaTemplate.send("dev-topic",jsonPersona);
		model.addAttribute("persona", per);
//		model.addAttribute("admin", per.isAdmin());
//		model.addAttribute("user", per.isUser());
		model.addAttribute("dia", dia);
		model.addAttribute("msj", " el usuario o contraseña es incorrecto");
		String url = "login";
		String Passw = personaService.encriptar(persona.getPassword());
		if (per != null
				&& (persona.getEmail().equals(per.getEmail()) && Passw.equals(per.getPassword()))) {
			url = "index";
		}
		return url;
	}

	@GetMapping("/personasCurso")
	public String personasCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, @RequestParam Integer valorCurso, Model model) {
		personasList = personaService.findAllByCurso(idCurso);
		List<PersonaDto> listPersonasConsolidacion = personaService.buscarConsolidacion(personasList, idCurso);	
		Curso cursoMostrar = new Curso();
		cursoMostrar = cursoService.findCursoById(idCurso);
		boolean consolidacion =false;
		if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
			consolidacion =true;
		}
		model.addAttribute("personas", listPersonasConsolidacion);
		model.addAttribute("idCurso", idCurso);
		model.addAttribute("nombreCurso", nombreCurso);
		model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("consolidacion", consolidacion);
		return "personasCurso";
	}

	@GetMapping("/buscarPersonasSinCurso")
	public String buscarPersonasSinCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
		personasList = personaService.buscarTodosSinCurso(idCurso);
		
		model.addAttribute("personas", personasList);
		model.addAttribute("msj", "Personas las cuales se puede agregar al curso: " + nombreCurso);
		model.addAttribute("titulo", "Lista de Personas no inscritas");
		model.addAttribute("add", true);
		model.addAttribute("delete", false);
		model.addAttribute("nombreCurso", nombreCurso);
		model.addAttribute("idCurso", idCurso);
//		model.addAttribute("consolidacion", false);
		model.addAttribute("pago", false);
		return "personasSinCurso";
	}

	@GetMapping("/eliminarPersonasCurso")
	public String eliminarPersonasCurso(@RequestParam int idPersona, @RequestParam int idCurso,
			@RequestParam String nombreCurso, Model model) {
		personaService.eliminarPersonaCurso(idPersona, idCurso);
		personasList = personaService.findAllByCurso(idCurso);
		List<PersonaDto> listPersonasConsolidacion= personaService.buscarConsolidacion(personasList,0);
		model.addAttribute("personas", listPersonasConsolidacion);
		model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("idCurso", idCurso);
		model.addAttribute("consolidacion", false);
		return "personasCurso";
	}

	@GetMapping("/eliminarPersonasMinisterio")
	public String eliminarPersonasMinisterio(@RequestParam int idPersona, @RequestParam int idMinisterio,
										@RequestParam String nombreMinisterio, Model model) {
		personaService.eliminarPersonaMinisterio(idPersona, idMinisterio);
		List<PersonaDto> personasList = servicioService.findPersonaByidMnisterio(idMinisterio);
		boolean consolidacion =false;

		model.addAttribute("personas", personasList);
		model.addAttribute("nombreMinisterio", nombreMinisterio);
		model.addAttribute("msj", "Personas inscritas al Ministerio " + nombreMinisterio);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("idMinisterio", idMinisterio);
		model.addAttribute("consolidacion", false);
		return "personasMinisterio";
	}

	@GetMapping("/agregarPersonasCurso")
	public String AgregarPersonasCurso(@RequestParam int idPersona, @RequestParam int idCurso,
			@RequestParam String nombreCurso, Model model) {
		personaService.agregarPersonaCurso(idPersona, idCurso);
		personasList = personaService.findAllByCurso(idCurso);
		List<PersonaDto> listPersonasConsolidacion= personaService.buscarConsolidacion(personasList,0);
		model.addAttribute("personas", listPersonasConsolidacion);
		model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("idCurso", idCurso);
		model.addAttribute("consolidacion", false);
		return "personasCurso";
	}

	@GetMapping("/autoInscripcionCurso")
	public String autoInscripcionCurso(@RequestParam int idPersona, @RequestParam int idCurso,
									   @RequestParam String nombreCurso, Model model) {
		personaService.agregarPersonaCurso(idPersona, idCurso);
		personasList = personaService.findAllByCurso(idCurso);
		List<PersonaDto> listPersonasConsolidacion= personaService.buscarConsolidacion(personasList,0);

		List<CursoDto> CursosList =  cursoService.findCursosDtoByIdPersona(idPersona);

		model.addAttribute("consolidar", true);
		model.addAttribute("cursos", CursosList);
		return "cursosUsuario";
	}

	@GetMapping("/")
	public String inicio(Model model, @AuthenticationPrincipal User user)
			throws JsonMappingException, JsonProcessingException {
		

		if (user!= null) {
			VersiculoDto dia = bibliaService.findVerseDay();
			model.addAttribute("dia", dia);
//	        var personas = personaService.findAll();
//	        log.info("ejecutando el controlador Spring MVC");
//	        log.info("usuario que hizo login:" + user);
//	        model.addAttribute("personas", personas);
			return "index";
		}
		return "login";
	}
	
	@GetMapping("/asignarPadre")
	public String asignarPadre(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		persona = personaService.findPersonaById(persona.getId());
		List<Persona> listtConsolidacion = personaRepoImpl.listarConsolidacion();
		
		model.addAttribute("id", persona.getId());
		model.addAttribute("nombre", persona.getNombre());
	    model.addAttribute("asistentes", listtConsolidacion);
		return "asignarPadres";
	}
	
	@PostMapping("/updatePass")
	public String updatePass(@ModelAttribute Persona persona, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per = personaService.buscarByDocumento(persona.getDocumento());
		String url = "recoverPass";
		if (per.getEmail() != null) {
			per.setPassword(persona.getPassword());
			persona = personaService.personaDtoToEntity(per);
			Persona personaSave = personaService.savePassword(persona);
			model.addAttribute("persona", persona);
			model.addAttribute("msjOk", " Su contraseña ha sido actualizada correctamente");
			model.addAttribute("msjError", null);
		}else {
		model.addAttribute("msjOk", null);
		model.addAttribute("msjError", " el usuario no se encuentra registrado");
		}
		return url;
	}

	@GetMapping("/listarRoles")
	public String rolesPersonas(HttpServletResponse response, Model model) {
		personasListDto = personaService.findAllUsuariosRol();
		model.addAttribute("personas", personasListDto);
		return "permisos";
	}

	@GetMapping("/editarRol")
	public String asignarRol(@ModelAttribute PersonaDto personaDto, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		Persona persona = personaService.findPersonaById(personaDto.getId());
		List<Rol> roles = rolesPersonaRepo.findAll();
		model.addAttribute("persona", persona);
		model.addAttribute("roles", roles);

		return "editar-rol";
	}

	@PostMapping("/editarRoles")
	public String editarRoles(@RequestParam int idPersona,@RequestParam int idRol, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {

		personaService.findUsuariosRol(idPersona,idRol);
		personasListDto = personaService.findAllUsuariosRol();
		model.addAttribute("personas", personasListDto);
		return "permisos";
	}

	@GetMapping("/listarPersonasJson")
	public ResponseEntity<List<Persona>> listarProductosJson(HttpServletResponse response,Model model)  {
		personasList = personaService.findAllUsuarios();

		return new ResponseEntity<>(personasList,  HttpStatus.ACCEPTED);
	}

}
