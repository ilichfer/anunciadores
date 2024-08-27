package com.anunciadores.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.anunciadores.dto.*;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
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

	@Autowired
	private IPermisosRepo permisosMenuRepo;

	@Autowired
	private RolesRepoImpl rolesDao;

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
		PersonaDto per;
		String url = "registerOut";
		model.addAttribute("persona", persona);
		per = personaService.buscarByDocumento(persona.getDocumento());
		if (per != null && per.getDocumento() != null) {
			model.addAttribute("msj", "numero de documento ya registrado");
			return url;
		}
		if (per == null || per.getEmail() == null) {
			Persona personaSave = personaService.save(persona);
			model.addAttribute("msj", null);
			model.addAttribute("msjCreate", " usuario creado correctamente");
			url = "login";
		}
		return url;
	}

	@PostMapping("/actualizarPerfil")
	public String actualizarPerfil(@ModelAttribute Persona persona, HttpServletResponse response, Model model)
			throws JsonMappingException, JsonProcessingException {
		PersonaDto per;
		String url = "perfil";
		Persona personaSave = personaService.update(persona);
		model.addAttribute("personaSave", persona);
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

	@GetMapping("/consutarDoc")
	public ResponseEntity<PersonaDto> consutarDoc(@RequestParam int doc, HttpServletResponse response,
													Model model) throws JsonMappingException, JsonProcessingException {
		PersonaDto person = new PersonaDto();
		String fechaCoor= null;
		person = personaService.buscarByDocumento(doc);
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			person.setCoordinadorActual(servicioService.validateCoordinadorByFechaAndIdPersona(listProgramacionMinisterio.get(0).getFechaServcio(),person.getId()));
		}else {
			person.setCoordinadorActual(false);
		}

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
		try {
		PersonaDto per = personaService.buscarByDocumento(persona.getDocumento());
		VersiculoDto dia = bibliaService.findVerseDay();
		List<ServicioListResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateGroup(Date.valueOf(LocalDate.now()));
		//List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
		if(listProgramacionMinisterio.size()>0) {
			Coordinador cor =servicioService.findCoordinador(listProgramacionMinisterio);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("programacionMin", listProgramacionMinisterio);
			model.addAttribute("coordinador", cor);
			model.addAttribute("fechaCoordinador", cor != null?  dt1.format(cor.getFechaServicio()): null);
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
				&& (persona.getDocumento().equals(per.getDocumento()) && Passw.equals(per.getPassword()))) {
			url = "index";
		}
		return url;
		}catch (Exception e) {
			model.addAttribute("msj", e.getMessage());
			return "login";
		}
	}

	@GetMapping("/personasCurso")
	public String personasCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, @RequestParam Integer valorCurso, Model model) {
		personasList = personaService.findAllByCurso(idCurso);
	//	List<PersonaDto> listPersonasConsolidacion = personaService.buscarConsolidacion(personasList, idCurso);
		Curso cursoMostrar = new Curso();
		cursoMostrar = cursoService.findCursoById(idCurso);
		boolean consolidacion =false;
		if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
			consolidacion =true;
		}
		model.addAttribute("personas", personasList);
		model.addAttribute("idCurso", idCurso);
		model.addAttribute("nombreCurso", nombreCurso);
		model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
		model.addAttribute("titulo", "Lista de Personas inscritas");
		model.addAttribute("add", false);
		model.addAttribute("delete", true);
		model.addAttribute("consolidacion", consolidacion);
		return "personasCurso";
	}

	@GetMapping("/notasPersonasCurso")
	public String notasPersonasCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, @RequestParam Integer valorCurso, Model model) {
		personasList = personaService.findAllByCurso(idCurso);
		//	List<PersonaDto> listPersonasConsolidacion = personaService.buscarConsolidacion(personasList, idCurso);
		Curso cursoMostrar = new Curso();
		cursoMostrar = cursoService.findCursoById(idCurso);
		boolean consolidacion =false;
		if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
			consolidacion =true;
		}
		//model.addAttribute("personas", listPersonasConsolidacion);
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
		//List<PersonaDto> listPersonasConsolidacion= personaService.buscarConsolidacion(personasList,0);
		model.addAttribute("personas", personasList);
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
	public String asignarRol(@ModelAttribute PersonaDto personaDto,@RequestParam String descripcionRol, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
		Persona persona = personaService.findPersonaById(personaDto.getId());
		List<Rol> roles = rolesPersonaRepo.findAll();
		List<Rol> rolActual = rolesDao.buscarRoles(personaDto.getId());
		List<PermisosMenu> permisosMenu = permisosMenuRepo.findByIdPersona(persona.getId());
		model.addAttribute("persona", persona);
		model.addAttribute("roles", roles);
		model.addAttribute("idRol", !roles.isEmpty() && roles.get(0).getDescripcion().equals(descripcionRol)?roles.get(0):roles.get(1));
		model.addAttribute("permisos", permisosMenu);
		model.addAttribute("descripcionRol", descripcionRol);

		return "editar-rol";
	}

	@GetMapping("/actualizarPermiso")
	public String actualizarPermiso(@ModelAttribute PermisosMenu permisos,@RequestParam int idPersona, @RequestParam String descRol, Model model) {
		PermisosMenu menuUpdate = permisosMenuRepo.findByIdPersonaAndNombreBotonMenu(permisos.getIdPersona(), permisos.getNombreBotonMenu());
		menuUpdate.setEstado(permisos.getEstado());
		PermisosMenu permisoActual = permisosMenuRepo.save(menuUpdate);


		Persona persona = personaService.findPersonaById(idPersona);
		List<Rol> roles = rolesPersonaRepo.findAll();
		List<Rol> rolActual = rolesDao.buscarRoles(idPersona);
		List<PermisosMenu> permisosMenu = permisosMenuRepo.findByIdPersona(persona.getId());
		model.addAttribute("persona", persona);
		model.addAttribute("roles", roles);
		model.addAttribute("idRol", roles.get(0));
		model.addAttribute("permisos", permisosMenu);
		model.addAttribute("descripcionRol", descRol);
		return "editar-rol";
	}

	@PostMapping("/editarRoles")
	public String editarRoles(@ModelAttribute("permisos") PermisosDto permisos, @RequestParam int idPersona, @RequestParam int idRol, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {

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
