  package com.anunciadores.controller;
  import com.anunciadores.dto.CursoDto;
  import com.anunciadores.dto.PermisosDto;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.dto.ServicioListResponseDto;
  import com.anunciadores.dto.VersiculoSaveDto;
  import com.anunciadores.model.Consolidacion;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.NotasCurso;
  import com.anunciadores.model.PermisosMenu;
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.Rol;
  import com.anunciadores.repository.ConsolidacionRepoImpl;
  import com.anunciadores.repository.IParamMenuRepo;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import com.fasterxml.jackson.databind.ObjectWriter;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Optional;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.security.core.annotation.AuthenticationPrincipal;
  import org.springframework.security.core.userdetails.User;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
  @Controller
  @RequestMapping
  public class personaController {
/*  46 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.controller.personaController.class);
    
    @Autowired
    private IPersonaService personaService;
    
    @Autowired
    private IMenuService menuService;
    
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
    private IParamMenuRepo paramMenuRepo;
    
    @Autowired
    private RolesRepoImpl rolesDao;
    @Autowired
    private UtilDate utilDate;
    List<Persona> personasList;
    List<PersonaDto> personasListDto;
    
    @GetMapping({"/listar"})
    public String personas(HttpServletResponse response, Model model) {
/*  93 */     this.personasList = this.personaService.findAllUsuarios();
/*  94 */     model.addAttribute("personas", this.personasList);
/*  95 */     model.addAttribute("msj", "Personas ");
/*  96 */     model.addAttribute("usuario", Boolean.valueOf(true));
/*  97 */     model.addAttribute("asistente", Boolean.valueOf(false));
/*  98 */     return "personas";
    }
    
    @GetMapping({"/listarAsistentes"})
    public String listarAsistentes(HttpServletResponse response, Model model) {
/* 103 */     this.personasList = this.personaRepoImpl.buscarAsistentes();
/* 104 */     model.addAttribute("personas", this.personasList);
/* 105 */     model.addAttribute("msj", "Asistentes ");
/* 106 */     model.addAttribute("usuario", Boolean.valueOf(false));
/* 107 */     model.addAttribute("asistente", Boolean.valueOf(true));
/* 108 */     model.addAttribute("admin", Boolean.valueOf(true));
/* 109 */     return "personas";
    }
    
    @GetMapping({"/listarAsistentesConsolidacion"})
    public String listarAsistentesConsolidacion(HttpServletResponse response, Model model) {
/* 114 */     this.personasList = this.personaRepoImpl.buscarAsistentes();
/* 115 */     model.addAttribute("personas", this.personasList);
/* 116 */     model.addAttribute("msj", "Asistentes ");
/* 117 */     model.addAttribute("usuario", Boolean.valueOf(false));
/* 118 */     model.addAttribute("asistente", Boolean.valueOf(true));
/* 119 */     model.addAttribute("admin", Boolean.valueOf(true));
/* 120 */     return "personas";
    }
    
    @GetMapping({"/listarAsistentesByUsuario"})
    public String listarAsistentesByUsuario(HttpServletResponse response, Model model) {
/* 125 */     this.personasList = this.personaRepoImpl.buscarAsistentes();
/* 126 */     model.addAttribute("personas", this.personasList);
/* 127 */     model.addAttribute("msj", "Asistentes ");
/* 128 */     model.addAttribute("usuario", Boolean.valueOf(false));
/* 129 */     model.addAttribute("asistente", Boolean.valueOf(true));
/* 130 */     model.addAttribute("admin", Boolean.valueOf(false));
/* 131 */     return "personas";
    }
  
    
    @GetMapping({"/buscar/{id}"})
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
/* 137 */     return ResponseEntity.ok(this.personaService.findPersonaById(id));
    }
  
    
    @PostMapping({"/buscarPersona"})
    public String buscarPersonaById(@ModelAttribute Persona persona, Model model) {
/* 143 */     List<Persona> findPersona = new ArrayList<>();
/* 144 */     findPersona.add(this.personaService.findPersonaByNombre(persona.getNombre()));
      
/* 146 */     model.addAttribute("personas", findPersona);
/* 147 */     model.addAttribute("msj", "Personas ");
/* 148 */     model.addAttribute("usuario", Boolean.valueOf(true));
/* 149 */     model.addAttribute("asistente", Boolean.valueOf(false));
/* 150 */     return "personas";
    }
  
    
    @PostMapping({"/save"})
    public String save(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 156 */     PersonaDto per = this.personaService.buscarEmail(persona.getEmail());
/* 157 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 158 */     String url = "redirect:/404.html";
/* 159 */     model.addAttribute("dia", dia);
/* 160 */     if (per == null || per.getEmail() == null) {
/* 161 */       Persona personaSave = this.personaService.save(persona);
/* 162 */       model.addAttribute("persona", persona);
/* 163 */       url = "redirect:/listar";
      } 
/* 165 */     return url;
    }
  
  
    
    @PostMapping({"/saveOut"})
    public String saveOut(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 172 */     String url = "registerOut";
/* 173 */     model.addAttribute("persona", persona);
/* 174 */     PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 175 */     if (per != null && per.getDocumento() != null) {
/* 176 */       model.addAttribute("msj", "numero de documento ya registrado");
/* 177 */       return url;
      } 
/* 179 */     if (per == null || per.getEmail() == null) {
/* 180 */       persona.setEstado(Boolean.valueOf(true));
/* 181 */       Persona personaSave = this.personaService.save(persona);
/* 182 */       model.addAttribute("msj", null);
/* 183 */       model.addAttribute("msjCreate", " usuario creado correctamente");
/* 184 */       url = "ingreso";
      } 
/* 186 */     return url;
    }
  
  
    
    @PostMapping({"/actualizarPerfil"})
    public String actualizarPerfil(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 193 */     String url = "perfil";
/* 194 */     Persona personaSave = this.personaService.update(persona);
/* 195 */     model.addAttribute("personaSave", persona);
/* 196 */     return url;
    }
  
    
    @GetMapping({"/consutarEmail"})
    public ResponseEntity<PersonaDto> consutarEmail(@RequestParam String email, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 202 */     PersonaDto person = new PersonaDto();
/* 203 */     person = this.personaService.buscarEmail(email);
/* 204 */     model.addAttribute("admin", Boolean.valueOf(person.isAdmin()));
/* 205 */     model.addAttribute("user", Boolean.valueOf(person.isUser()));
/* 206 */     model.addAttribute("persona", person);
      
/* 208 */     return new ResponseEntity(person, null, HttpStatus.ACCEPTED);
    }
  
    
    @GetMapping({"/consutarDoc"})
    public ResponseEntity<PersonaDto> consutarDoc(@RequestParam int doc, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 214 */     PersonaDto person = new PersonaDto();
/* 215 */     String fechaCoor = null;
/* 216 */     person = this.personaService.buscarByDocumento(Integer.valueOf(doc));
/* 217 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(this.utilDate.cargarfechaActualBogotaDate());
      
/* 219 */     if (listProgramacionMinisterio.size() > 0) {
/* 220 */       person.setCoordinadorActual(this.servicioService.validateCoordinadorByFechaAndIdPersona(((ServicioListResponseDto)listProgramacionMinisterio.get(0)).getFechaServcio(), person.getId()).booleanValue());
      } else {
/* 222 */       person.setCoordinadorActual(false);
      } 
      
/* 225 */     return new ResponseEntity(person, null, HttpStatus.ACCEPTED);
    }
  
    
    @GetMapping({"/cargarMenu"})
    public String cargarMenu(@RequestParam String email, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 231 */     PersonaDto per = this.personaService.buscarEmail(email);
/* 232 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 233 */     model.addAttribute("persona", per);
/* 234 */     model.addAttribute("admin", Boolean.valueOf(per.isAdmin()));
/* 235 */     model.addAttribute("user", Boolean.valueOf(per.isUser()));
/* 236 */     model.addAttribute("dia", dia);
/* 237 */     model.addAttribute("msj", " el usuario o contraseña es incorrecto");
      
/* 239 */     String url = "login";
/* 240 */     if (per != null) {
/* 241 */       url = "index";
      }
/* 243 */     return url;
    }
  
  
    
    @PostMapping({"/saveAsistente"})
    public String saveAsistente(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 250 */     PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 251 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 252 */     String url = "registerAsistente";
/* 253 */     model.addAttribute("dia", dia);
/* 254 */     model.addAttribute("msj", "ya existe un asistente con el mismo numero de documento");
/* 255 */     if (per == null || per.getEmail() == null) {
/* 256 */       Persona personaSave = this.personaService.saveAsistente(persona);
/* 257 */       model.addAttribute("persona", persona);
/* 258 */       url = "redirect:/listarAsistentes";
      } 
/* 260 */     return url;
    }
    
    @PostMapping({"/saveAsistenteConsolidacion"})
    public String saveAsistenteConsolidacion(@ModelAttribute Persona persona, @ModelAttribute Consolidacion consolidacion, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 265 */     PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 266 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 267 */     String url = "registerAsistenteConsolidacion";
/* 268 */     model.addAttribute("dia", dia);
/* 269 */     model.addAttribute("msj", "ya existe un asistente con el mismo numero de documento");
/* 270 */     if (per == null || per.getNombre() == null) {
/* 271 */       Persona personaSave = this.personaService.saveAsistenteConsolidacion(persona, consolidacion);
        
/* 273 */       model.addAttribute("persona", persona);
/* 274 */       url = "redirect:https://web.facebook.com/IgAnunciadoresCristo";
      } 
/* 276 */     return url;
    }
    
    @GetMapping({"/eliminar"})
    public String deleteProductoById(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
/* 281 */     String retorno = this.personaService.delete(persona);
/* 282 */     return "redirect:/listar";
    }
    
    @GetMapping({"/habilitarPersona"})
    public String habilitarPersona(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
/* 287 */     this.personaService.habilitar(persona);
/* 288 */     this.personasList = this.personaRepoImpl.findPeopleDisabled();
/* 289 */     model.addAttribute("personas", this.personasList);
/* 290 */     model.addAttribute("msj", "Personas deshabilitadas ");
/* 291 */     model.addAttribute("usuario", Boolean.valueOf(false));
/* 292 */     model.addAttribute("asistente", Boolean.valueOf(true));
/* 293 */     model.addAttribute("admin", Boolean.valueOf(true));
/* 294 */     return "personasDeshabilitadas";
    }
  
    
    @GetMapping({"/greeting"})
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 300 */     model.addAttribute("name", name);
/* 301 */     return "index";
    }
    
    @PostMapping({"/access"})
    public String login(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 306 */     PersonaDto per = this.personaService.buscarEmail(persona.getEmail());
/* 307 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
      
/* 309 */     model.addAttribute("dia", dia);
/* 310 */     model.addAttribute("msj", " el usuario o contraseña es incorrecto");
      
/* 312 */     String url = "index";
/* 313 */     if (per != null && persona
/* 314 */       .getEmail().equals(per.getEmail()) && persona.getPassword().equals(per.getPassword())) {
/* 315 */       url = "index";
      }
/* 317 */     return url;
    }
    
    @GetMapping({"/login"})
    public String login(@AuthenticationPrincipal User user) throws JsonMappingException, JsonProcessingException {
/* 322 */     System.out.println("entro a login");
/* 323 */     System.out.println(user.toString());
/* 324 */     return "login";
    }
    
    @PostMapping({"login2"})
    public String login2(@ModelAttribute Persona persona, HttpServletResponse response, HttpServletRequest request, Model model) throws JsonMappingException, JsonProcessingException {
      try {
/* 330 */       PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 331 */       VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 332 */       List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(this.utilDate.cargarfechaActualBogotaDate());
/* 333 */       List<PersonaDto> listadoCumpleanosMes = this.personaService.findBirthdayByMonth();
/* 334 */       List<PersonaDto> listadoCumpleanosDiario = this.personaService.getBirthDay(listadoCumpleanosMes);
/* 335 */       model.addAttribute("cumpleanos", listadoCumpleanosMes);
/* 336 */       if (!listadoCumpleanosDiario.isEmpty()) {
/* 337 */         model.addAttribute("cumpleanosDiario", listadoCumpleanosDiario);
        } else {
/* 339 */         model.addAttribute("cumpleanosDiario", null);
        } 
  
        
/* 343 */       if (listProgramacionMinisterio.size() > 0) {
/* 344 */         Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 345 */         SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 346 */         model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 347 */         model.addAttribute("coordinador", cor);
/* 348 */         model.addAttribute("fechaCoordinador", (cor != null) ? dt1.format(cor.getFechaServicio()) : null);
        } else {
/* 350 */         model.addAttribute("programacionMin", null);
        } 
/* 352 */       HttpSession misession = request.getSession(true);
/* 353 */       misession.setAttribute("idPersona", Integer.valueOf(per.getId()));
        
/* 355 */       ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 356 */       String jsonPersona = ow.writeValueAsString(per);
/* 357 */       this.LOGGER.info("la persona que ingreso es:  " + jsonPersona);
        
/* 359 */       model.addAttribute("persona", per);
  
        
/* 362 */       model.addAttribute("dia", dia);
/* 363 */       model.addAttribute("msj", " el usuario o contraseña es incorrecto");
/* 364 */       String url = "login";
/* 365 */       String Passw = this.personaService.encriptar(persona.getPassword());
/* 366 */       if (per != null && persona
/* 367 */         .getDocumento().equals(per.getDocumento()) && Passw.equals(per.getPassword())) {
/* 368 */         url = "index";
        }
/* 370 */       return url;
/* 371 */     } catch (Exception e) {
/* 372 */       model.addAttribute("msj", e.getMessage());
/* 373 */       return "login";
      } 
    }
    
    @GetMapping({"/personasCurso"})
    public String personasCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, @RequestParam Integer valorCurso, Model model) {
/* 379 */     this.personasList = this.personaService.findAllByCurso(idCurso);
      
/* 381 */     Curso cursoMostrar = new Curso();
/* 382 */     cursoMostrar = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 383 */     boolean consolidacion = false;
/* 384 */     if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
/* 385 */       consolidacion = true;
      }
/* 387 */     model.addAttribute("personas", this.personasList);
/* 388 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 389 */     model.addAttribute("nombreCurso", nombreCurso);
/* 390 */     model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
/* 391 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 392 */     model.addAttribute("add", Boolean.valueOf(false));
/* 393 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 394 */     model.addAttribute("consolidacion", Boolean.valueOf(consolidacion));
/* 395 */     return "personasCurso";
    }
    
    @GetMapping({"/notasCurso"})
    public String notasCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 400 */     List<PersonaDto> personasListDTO = new ArrayList<>();
/* 401 */     personasListDTO = this.cursoService.buscarNotasXPersonas(idCurso, this.personaService.findAllByCurso(idCurso));
/* 402 */     Curso cursoMostrar = new Curso();
/* 403 */     cursoMostrar = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 404 */     boolean consolidacion = false;
/* 405 */     if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
/* 406 */       consolidacion = true;
      }
/* 408 */     model.addAttribute("personas", personasListDTO);
/* 409 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 410 */     model.addAttribute("nombreCurso", nombreCurso);
/* 411 */     model.addAttribute("msj", "Notas de personas inscritas al curso: " + nombreCurso);
/* 412 */     model.addAttribute("titulo", "Tabla de notas");
/* 413 */     model.addAttribute("add", Boolean.valueOf(false));
/* 414 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 415 */     model.addAttribute("consolidacion", Boolean.valueOf(consolidacion));
/* 416 */     return "notasPersonasCurso";
    }
    
    @GetMapping({"/registrarNotasPersona"})
    public String registrarNotasPersona(@RequestParam int idCurso, @RequestParam int idPersona, Model model) {
/* 421 */     this.personasList = this.personaService.findAllByCurso(idCurso);
      
/* 423 */     Curso cursoMostrar = new Curso();
/* 424 */     Persona persona = this.personaRepoImpl.findById(Integer.valueOf(idPersona)).get();
/* 425 */     cursoMostrar = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 426 */     NotasCurso notas = this.cursoService.findNotasByCurso(idCurso, idPersona);
/* 427 */     if (notas == null) {
/* 428 */       notas = new NotasCurso();
      }
      
/* 431 */     boolean consolidacion = false;
/* 432 */     if (cursoMostrar.getNombreCurso().contentEquals("padres espirituales")) {
/* 433 */       consolidacion = true;
      }
      
/* 436 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 437 */     model.addAttribute("curso", cursoMostrar);
/* 438 */     model.addAttribute("persona", persona);
      
/* 440 */     model.addAttribute("nota", notas);
/* 441 */     return "register-notas-curso";
    }
    
    @GetMapping({"/buscarPersonasSinCurso"})
    public String buscarPersonasSinCurso(@RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 446 */     this.personasList = this.personaService.buscarTodosSinCurso(idCurso);
      
/* 448 */     model.addAttribute("personas", this.personasList);
/* 449 */     model.addAttribute("msj", "Personas las cuales se puede agregar al curso: " + nombreCurso);
/* 450 */     model.addAttribute("titulo", "Lista de Personas no inscritas");
/* 451 */     model.addAttribute("add", Boolean.valueOf(true));
/* 452 */     model.addAttribute("delete", Boolean.valueOf(false));
/* 453 */     model.addAttribute("nombreCurso", nombreCurso);
/* 454 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
      
/* 456 */     model.addAttribute("pago", Boolean.valueOf(false));
/* 457 */     return "personasSinCurso";
    }
  
    
    @GetMapping({"/eliminarPersonasCurso"})
    public String eliminarPersonasCurso(@RequestParam int idPersona, @RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 463 */     this.personaService.eliminarPersonaCurso(idPersona, idCurso);
/* 464 */     this.personasList = this.personaService.findAllByCurso(idCurso);
      
/* 466 */     model.addAttribute("personas", this.personasList);
/* 467 */     model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
/* 468 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 469 */     model.addAttribute("add", Boolean.valueOf(false));
/* 470 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 471 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 472 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 473 */     return "personasCurso";
    }
  
    
    @GetMapping({"/eliminarPersonasMinisterio"})
    public String eliminarPersonasMinisterio(@RequestParam int idPersona, @RequestParam int idMinisterio, @RequestParam String nombreMinisterio, Model model) {
/* 479 */     this.personaService.eliminarPersonaMinisterio(idPersona, idMinisterio);
/* 480 */     List<PersonaDto> personasList = this.servicioService.findPersonaByidMnisterio(idMinisterio);
/* 481 */     boolean consolidacion = false;
      
/* 483 */     model.addAttribute("personas", personasList);
/* 484 */     model.addAttribute("nombreMinisterio", nombreMinisterio);
/* 485 */     model.addAttribute("msj", "Personas inscritas al Ministerio " + nombreMinisterio);
/* 486 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 487 */     model.addAttribute("add", Boolean.valueOf(false));
/* 488 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 489 */     model.addAttribute("idMinisterio", Integer.valueOf(idMinisterio));
/* 490 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 491 */     return "personasMinisterio";
    }
  
    
    @GetMapping({"/agregarPersonasCurso"})
    public String AgregarPersonasCurso(@RequestParam int idPersona, @RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 497 */     this.personaService.agregarPersonaCurso(idPersona, idCurso);
/* 498 */     this.personasList = this.personaService.findAllByCurso(idCurso);
      
/* 500 */     model.addAttribute("personas", this.personasList);
/* 501 */     model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
/* 502 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 503 */     model.addAttribute("add", Boolean.valueOf(false));
/* 504 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 505 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 506 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 507 */     return "personasCurso";
    }
  
    
    @PostMapping({"/agregarListaPersonasCurso"})
    public String agregarListaPersonasCurso(@RequestParam(required = false) List<Integer> idPersonas, @RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 513 */     if (idPersonas == null || idPersonas.isEmpty()) {
/* 514 */       return "redirect:/buscarPersonasSinCurso?idCurso=" + idCurso + "&nombreCurso=" + nombreCurso;
      }
/* 516 */     idPersonas.forEach(idp -> this.personaService.agregarPersonaCurso(idp.intValue(), idCurso));
/* 517 */     this.personasList = this.personaService.findAllByCurso(idCurso);
      
/* 519 */     model.addAttribute("personas", this.personasList);
/* 520 */     model.addAttribute("msj", "Personas inscritas al curso: " + nombreCurso);
/* 521 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 522 */     model.addAttribute("add", Boolean.valueOf(false));
/* 523 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 524 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/* 525 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 526 */     return "personasCurso";
    }
  
    
    @GetMapping({"/autoInscripcionCurso"})
    public String autoInscripcionCurso(@RequestParam int idPersona, @RequestParam int idCurso, @RequestParam String nombreCurso, Model model) {
/* 532 */     this.personaService.agregarPersonaCurso(idPersona, idCurso);
/* 533 */     this.personasList = this.personaService.findAllByCurso(idCurso);
/* 534 */     List<PersonaDto> listPersonasConsolidacion = this.personaService.buscarConsolidacion(this.personasList, 0);
      
/* 536 */     List<CursoDto> CursosList = this.cursoService.findCursosDtoByIdPersona(Integer.valueOf(idPersona));
      
/* 538 */     model.addAttribute("consolidar", Boolean.valueOf(true));
/* 539 */     model.addAttribute("cursos", CursosList);
/* 540 */     return "cursosUsuario";
    }
  
  
  
    
    @GetMapping({"/"})
    public String inicio(Model model, @AuthenticationPrincipal User user) throws JsonMappingException, JsonProcessingException, ParseException {
/* 548 */     if (user != null) {
/* 549 */       VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 550 */       model.addAttribute("dia", dia);
  
  
  
        
/* 555 */       return "index";
      } 
/* 557 */     return "login";
    }
    
    @GetMapping({"/asignarPadre"})
    public String asignarPadre(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 562 */     persona = this.personaService.findPersonaById(persona.getId());
/* 563 */     List<Persona> listtConsolidacion = this.personaRepoImpl.listarConsolidacion();
      
/* 565 */     model.addAttribute("id", persona.getId());
/* 566 */     model.addAttribute("nombre", persona.getNombre());
/* 567 */     model.addAttribute("asistentes", listtConsolidacion);
/* 568 */     return "asignarPadres";
    }
  
    
    @PostMapping({"/updatePass"})
    public String updatePass(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 574 */     PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 575 */     String url = "recoverPass";
/* 576 */     if (per.getEmail() != null) {
/* 577 */       per.setPassword(persona.getPassword());
/* 578 */       persona = this.personaService.personaDtoToEntity(per);
/* 579 */       Persona personaSave = this.personaService.savePassword(persona);
/* 580 */       model.addAttribute("persona", persona);
/* 581 */       model.addAttribute("msjOk", " Su contraseña ha sido actualizada correctamente");
/* 582 */       model.addAttribute("msjError", null);
      } else {
/* 584 */       model.addAttribute("msjOk", null);
/* 585 */       model.addAttribute("msjError", " el usuario no se encuentra registrado");
      } 
/* 587 */     return url;
    }
    
    @GetMapping({"/listarRoles"})
    public String rolesPersonas(HttpServletResponse response, Model model) {
/* 592 */     this.personasListDto = this.personaService.findAllUsuariosRol();
/* 593 */     model.addAttribute("personas", this.personasListDto);
/* 594 */     return "permisos";
    }
    
    @GetMapping({"/editarRol"})
    public String asignarRol(@ModelAttribute PersonaDto personaDto, @RequestParam String descripcionRol, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 599 */     Persona persona = this.personaService.findPersonaById(Integer.valueOf(personaDto.getId()));
/* 600 */     List<Rol> roles = this.rolesPersonaRepo.findAll();
/* 601 */     List<Rol> rolActual = this.rolesDao.buscarRoles(Integer.valueOf(personaDto.getId()));
/* 602 */     List<PermisosMenu> permisosMenu = this.menuService.findAllPermisosMenu(persona.getId().intValue());
/* 603 */     model.addAttribute("persona", persona);
/* 604 */     model.addAttribute("roles", roles);
/* 605 */     model.addAttribute("idRol", (!roles.isEmpty() && ((Rol)roles.get(0)).getDescripcion().equals(descripcionRol)) ? roles.get(0) : roles.get(1));
/* 606 */     model.addAttribute("permisos", permisosMenu);
/* 607 */     model.addAttribute("descripcionRol", descripcionRol);
      
/* 609 */     return "editar-rol";
    }
    
    @GetMapping({"/actualizarPermiso"})
    public String actualizarPermiso(@ModelAttribute PermisosMenu permisos, @RequestParam int idPersona, @RequestParam String descRol, Model model) {
/* 614 */     Optional<PermisosMenu> menuUpdate = this.permisosMenuRepo.findByIdPersonaAndNombreBotonMenu(permisos.getIdPersona(), permisos.getNombreBotonMenu());
/* 615 */     if (menuUpdate.isPresent()) {
/* 616 */       ((PermisosMenu)menuUpdate.get()).setEstado(permisos.getEstado());
/* 617 */       PermisosMenu permisosMenu1 = (PermisosMenu)this.permisosMenuRepo.save(menuUpdate.get());
      } else {
/* 619 */       PermisosMenu pemNew = new PermisosMenu();
/* 620 */       pemNew.setIdPersona(permisos.getIdPersona());
/* 621 */       pemNew.setMenu(permisos.getMenu());
/* 622 */       pemNew.setEstado(permisos.getEstado());
/* 623 */       pemNew.setNombreBotonMenu(permisos.getNombreBotonMenu());
/* 624 */       pemNew.setMenu(this.paramMenuRepo.findByNombreBotonMenu(permisos.getNombreBotonMenu()));
/* 625 */       PermisosMenu permisosMenu1 = (PermisosMenu)this.permisosMenuRepo.save(pemNew);
      } 
      
/* 628 */     Persona persona = this.personaService.findPersonaById(Integer.valueOf(idPersona));
/* 629 */     List<Rol> roles = this.rolesPersonaRepo.findAll();
/* 630 */     List<Rol> rolActual = this.rolesDao.buscarRoles(Integer.valueOf(idPersona));
/* 631 */     List<PermisosMenu> permisosMenu = this.menuService.findAllPermisosMenu(persona.getId().intValue());
/* 632 */     model.addAttribute("persona", persona);
/* 633 */     model.addAttribute("roles", roles);
/* 634 */     model.addAttribute("idRol", roles.get(0));
/* 635 */     model.addAttribute("permisos", permisosMenu);
/* 636 */     model.addAttribute("descripcionRol", descRol);
/* 637 */     return "editar-rol";
    }
  
    
    @PostMapping({"/editarRoles"})
    public String editarRoles(@ModelAttribute("permisos") PermisosDto permisos, @RequestParam int idPersona, @RequestParam int idRol, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 643 */     this.personaService.findUsuariosRol(idPersona, idRol);
/* 644 */     this.personasListDto = this.personaService.findAllUsuariosRol();
/* 645 */     model.addAttribute("personas", this.personasListDto);
/* 646 */     return "permisos";
    }
    
    @GetMapping({"/listarPersonasJson"})
    public ResponseEntity<List<Persona>> listarProductosJson(HttpServletResponse response, Model model) {
/* 651 */     this.personasList = this.personaService.findAllUsuarios();
      
/* 653 */     return new ResponseEntity(this.personasList, HttpStatus.ACCEPTED);
    }
    
    @GetMapping({"/disabledPeople"})
    public String disabledPeople(HttpServletResponse response, Model model) {
/* 658 */     this.personasList = this.personaRepoImpl.findPeopleDisabled();
/* 659 */     model.addAttribute("personas", this.personasList);
/* 660 */     model.addAttribute("msj", "Personas deshabilitadas ");
/* 661 */     model.addAttribute("habilitar", Boolean.valueOf(true));
/* 662 */     model.addAttribute("usuario", Boolean.valueOf(false));
/* 663 */     model.addAttribute("asistente", Boolean.valueOf(true));
/* 664 */     model.addAttribute("admin", Boolean.valueOf(true));
/* 665 */     return "personasDeshabilitadas";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\personaController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */