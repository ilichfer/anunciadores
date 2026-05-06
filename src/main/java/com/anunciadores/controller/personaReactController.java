  package anunciadores.controller;
  import com.anunciadores.auth.dto.reporRequest;
  import com.anunciadores.auth.dto.updateServiceRequest;
  import com.anunciadores.dto.AddPersonaRequest;
  import com.anunciadores.dto.CoordinadorDTO;
  import com.anunciadores.dto.ImagenDiariaDto;
  import com.anunciadores.dto.Ministry;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.dto.PersonaReactDto;
  import com.anunciadores.dto.PosicionDto;
  import com.anunciadores.dto.ProgramationDto;
  import com.anunciadores.dto.ServiceDTO;
  import com.anunciadores.dto.ServicioResponseDto;
  import com.anunciadores.dto.TdcDto;
  import com.anunciadores.dto.TdcReporteDto;
  import com.anunciadores.dto.UserResponseDto;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Persona;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.Date;
  import java.util.List;
  import javax.servlet.http.HttpServletRequest;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
 
 
 
 
  import org.springframework.web.multipart.MultipartFile;
  import org.springframework.web.server.ResponseStatusException;
  
  @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
  @Controller
  @RequestMapping({"/api"})
  public class personaReactController {
/*  41 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.controller.personaReactController.class);
    
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
    private IMInisteryService ministeryService;
    
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
    
    @Autowired
    private IR2UploadService r2UploadService;
    
    @Autowired
    private ITdcService tdcService;
    List<Persona> personasList;
    List<PersonaDto> personasListDto;
    
    @GetMapping({"/users"})
    public ResponseEntity<List<PersonaReactDto>> users() throws JsonMappingException, JsonProcessingException, ParseException {
/*  98 */     ResponseEntity<List<PersonaReactDto>> rp = null;
/*  99 */     List<PersonaReactDto> listResult = this.personaService.findAllUsers();
/* 100 */     if (listResult.size() > 0) {
/* 101 */       rp = new ResponseEntity(listResult, null, HttpStatus.ACCEPTED);
      } else {
/* 103 */       rp = new ResponseEntity(listResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
      
/* 106 */     return rp;
    }
    
    @GetMapping({"/events"})
    public ResponseEntity<ProgramationDto> events() throws JsonMappingException, JsonProcessingException, ParseException {
/* 111 */     ResponseEntity<ProgramationDto> rp = null;
/* 112 */     ProgramationDto result = this.servicioService.findNextServices(this.utilDate.cargarfechaActualBogotaDate());
      
/* 114 */     if (result != null && result.getMinistries() != null && result.getMinistries().size() > 0) {
/* 115 */       rp = new ResponseEntity(result, null, HttpStatus.ACCEPTED);
      } else {
/* 117 */       rp = new ResponseEntity(result, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
/* 119 */     return rp;
    }
    
    @GetMapping({"/findprog"})
    public ResponseEntity<ProgramationDto> findprog(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) throws JsonMappingException, JsonProcessingException, ParseException {
/* 124 */     ResponseEntity<ProgramationDto> rp = null;
      
/* 126 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 127 */     Date fechaD = sdf.parse(date);
/* 128 */     ProgramationDto result = this.servicioService.findServices(fechaD);
      
/* 130 */     if (result != null && result.getMinistries() != null && result.getMinistries().size() > 0) {
/* 131 */       rp = new ResponseEntity(result, null, HttpStatus.ACCEPTED);
      } else {
        
/* 134 */       rp = new ResponseEntity(result, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
/* 136 */     return rp;
    }
  
    
    @GetMapping({"/ministries"})
    public ResponseEntity<List<Ministry>> ministries() throws JsonMappingException, JsonProcessingException, ParseException {
/* 142 */     ResponseEntity<List<Ministry>> rp = null;
/* 143 */     List<Ministry> listServResult = this.ministeryService.getAllministriesWithPositions();
      
/* 145 */     if (listServResult.size() > 0) {
/* 146 */       rp = new ResponseEntity(listServResult, null, HttpStatus.ACCEPTED);
      } else {
/* 148 */       rp = new ResponseEntity(listServResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
/* 150 */     return rp;
    }
  
    
    @GetMapping({"/ministries/{idMinistry}"})
    public ResponseEntity<Ministry> getMinistries(@PathVariable Integer idMinistry) throws JsonMappingException, JsonProcessingException, ParseException {
/* 156 */     ResponseEntity<Ministry> rp = null;
/* 157 */     Ministry minResult = this.ministeryService.getMinistryWithPositions(idMinistry);
      
/* 159 */     if (minResult != null && minResult.getPositions() != null && minResult.getPositions().size() > 0) {
/* 160 */       rp = new ResponseEntity(minResult, null, HttpStatus.ACCEPTED);
      } else {
/* 162 */       rp = new ResponseEntity(minResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
/* 164 */     return rp;
    }
  
  
    
    @GetMapping({"/ministries/{id}/personas"})
    public ResponseEntity<?> getPersonasByMinisterio(@PathVariable Integer id) {
      try {
/* 172 */       List<PersonaDto> personas = this.servicioService.findPersonaByidMnisterio(id.intValue());
/* 173 */       return ResponseEntity.ok(personas);
/* 174 */     } catch (Exception e) {
/* 175 */       return ResponseEntity.status(500)
/* 176 */         .body("Error al obtener personas del ministerio: " + e.getMessage());
      } 
    }
  
    
    @PostMapping({"/ministries/addposition"})
    public ResponseEntity<?> addPositionToMinistrie(@RequestBody PosicionDto posicionDto) {
      try {
/* 184 */       this.servicioService.savePosicion(posicionDto); return ResponseEntity.noContent().build();
/* 185 */     } catch (ResponseStatusException e) {
/* 186 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason());
      } 
    }
  
  
  
    
    @GetMapping({"/user"})
    public ResponseEntity<?> getUsuarioLogueado(HttpServletRequest request) {
      try {
/* 196 */       String authHeader = request.getHeader("Authorization");
/* 197 */       if (authHeader == null || !authHeader.startsWith("Bearer ")) {
/* 198 */         return ResponseEntity.status(401).body("Token no proporcionado");
        }
/* 200 */       String token = authHeader.substring(7);
        
/* 202 */       UserResponseDto user = this.personaService.getUsuarioDesdeToken(token);
/* 203 */       return ResponseEntity.ok(user);
      }
/* 205 */     catch (Exception e) {
/* 206 */       return ResponseEntity.status(401).body("Token inválido o expirado");
      } 
    }
  
  
    
    @GetMapping({"/schedule/persona/{id}"})
    public ResponseEntity<?> getProgramacionPersona(@PathVariable Integer id) {
      try {
/* 215 */       List<ServicioResponseDto> listServ = this.servicioService.buscarProgramacionMes(id.intValue());
/* 216 */       return ResponseEntity.ok(listServ);
/* 217 */     } catch (Exception e) {
/* 218 */       return ResponseEntity.status(500)
/* 219 */         .body("Error al obtener programación: " + e.getMessage());
      } 
    }
  
  
    
    @DeleteMapping({"/ministeries/{idMinisterio}/personas/{idPersona}"})
    public ResponseEntity<?> deletePersonaFromMinisterie(@PathVariable int idMinisterio, @PathVariable int idPersona) {
/* 227 */     ResponseEntity<List<PersonaDto>> rp = null;
  
      
/* 230 */     this.personaService.eliminarPersonaMinisterio(idPersona, idMinisterio);
/* 231 */     List<PersonaDto> personasList = this.servicioService.findPersonaByidMnisterio(idMinisterio);
      
/* 233 */     if (personasList.size() > 0) {
/* 234 */       rp = new ResponseEntity(personasList, null, HttpStatus.ACCEPTED);
      } else {
/* 236 */       rp = new ResponseEntity(personasList, null, HttpStatus.INTERNAL_SERVER_ERROR);
      } 
/* 238 */     return rp;
    }
  
    
    @PostMapping({"/ministeries/addperson"})
    public ResponseEntity<?> addPersonaToMinisterie(@RequestBody AddPersonaRequest request) {
      try {
/* 245 */       this.ministeryService.agregarPersonasAMinisterio(request.getIdPersona().intValue(), request.getIdMinisterio().intValue());
/* 246 */       return ResponseEntity.noContent().build();
/* 247 */     } catch (ResponseStatusException e) {
/* 248 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason());
      } 
    }
  
    
    @PostMapping({"/updateprog"})
    public ResponseEntity<?> updateprog(@RequestBody updateServiceRequest request) {
      try {
/* 256 */       System.out.println("entro ");
/* 257 */       this.ministeryService.updateService(request);
/* 258 */       return ResponseEntity.noContent().build();
/* 259 */     } catch (ResponseStatusException e) {
/* 260 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason());
/* 261 */     } catch (ParseException e) {
/* 262 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
      } 
    }
  
  
  
  
    
    @PostMapping({"/upload"})
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("idPersona") Integer idPersona) {
      try {
/* 273 */       String imageUrl = this.r2UploadService.uploadImage(file);
  
        
/* 276 */       ImagenDiariaDto imagen = new ImagenDiariaDto();
/* 277 */       imagen.setIdPersona(idPersona.intValue());
/* 278 */       imagen.setFechaCreacion(LocalDate.now());
/* 279 */       imagen.setTdc(imageUrl);
        
/* 281 */       this.tdcService.saveTcdImage(imageUrl, idPersona);
        
/* 283 */       return ResponseEntity.ok(imageUrl);
      }
/* 285 */     catch (IOException e) {
/* 286 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
/* 287 */         .body("Error al subir la imagen");
      } 
    }
  
    
    @PostMapping({"/scheduleByDate"})
    public ResponseEntity<?> scheduleByDate(@RequestBody reporRequest request) throws ParseException {
/* 294 */     ResponseEntity<List<TdcReporteDto>> rp = null;
/* 295 */     if (request.getFechaInicio() != null && request.getFechaFin() != null) {
/* 296 */       System.out.println("Fecha Inicio: " + request.getFechaInicio());
/* 297 */       List<TdcReporteDto> listaTdc = this.tdcService.findAllBetweenDates(this.utilDate.convertStringToDate(request.getFechaInicio()), this.utilDate.convertStringToDate(request.getFechaFin()));
        
/* 299 */       if (listaTdc.size() > 0) {
/* 300 */         rp = new ResponseEntity(listaTdc, null, HttpStatus.ACCEPTED);
        } else {
/* 302 */         rp = new ResponseEntity(listaTdc, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
      } 
/* 305 */     return rp;
    }
  
  
    
    @GetMapping({"/tdcbyPerson/{idPersona}"})
    public ResponseEntity<?> tdcbyPerson(@PathVariable Integer idPersona) throws ParseException {
/* 312 */     ResponseEntity<TdcReporteDto> rp = null;
/* 313 */     if (idPersona != null) {
/* 314 */       TdcReporteDto Tdc = this.tdcService.findAllBetweenDatesAndPerson(idPersona);
        
/* 316 */       if (Tdc != null && Tdc.getIdPersona() != 0) {
/* 317 */         rp = new ResponseEntity(Tdc, null, HttpStatus.ACCEPTED);
        } else {
/* 319 */         rp = new ResponseEntity(Tdc, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
      } 
/* 322 */     return rp;
    }
  
    
    @PostMapping({"/savecordinador"})
    public ResponseEntity<?> saveCordinador(@RequestBody CoordinadorDTO cordinador) {
      ResponseEntity<?> rp;
      try {
/* 330 */       Coordinador corSave = this.servicioService.findCoordinadorByFecha(this.utilDate.convertStringToDate(cordinador.getFechaString()));
/* 331 */       if (corSave == null) {
/* 332 */         Boolean response = this.servicioService.saveCoordinado(cordinador);
/* 333 */         if (response.booleanValue()) {
/* 334 */           rp = new ResponseEntity(Boolean.TRUE, null, HttpStatus.ACCEPTED);
          } else {
            
/* 337 */           rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar cordinador");
          }
        
        } else {
          
/* 342 */         rp = ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, "Ya existe una programación para esta fecha y ministerio."));
        } 
/* 344 */     } catch (Exception e) {
        
/* 346 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar cordinador");
      } 
/* 348 */     return rp;
    }
  
    
    @GetMapping({"/findSchedule"})
    public ResponseEntity<?> consultarMiProgramacion(@RequestParam String fecha, @RequestParam int idMinisterio) throws JsonMappingException, JsonProcessingException, ParseException {
      ResponseEntity<?> rp;
/* 355 */     Date fechaD = this.utilDate.convertStringToDate(fecha);
/* 356 */     List<ServicioResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateAndMinisterio(fechaD, idMinisterio);
      
/* 358 */     if (listProgramacionMinisterio.size() > 0) {
        
/* 360 */       rp = new ResponseEntity(listProgramacionMinisterio, null, HttpStatus.ACCEPTED);
      } else {
        
/* 363 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar cordinador");
      } 
/* 365 */     return rp;
    }
  
  
    
    @PostMapping({"/updatePassword"})
    public ResponseEntity<?> updatePass(@RequestBody Persona persona) throws JsonMappingException, JsonProcessingException {
/* 372 */     ResponseEntity<?> rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar contraseña");
/* 373 */     PersonaDto per = this.personaService.buscarByDocumento(persona.getDocumento());
/* 374 */     if (per.getEmail() != null) {
/* 375 */       per.setPassword(persona.getPassword());
/* 376 */       persona = this.personaService.personaDtoToEntity(per);
/* 377 */       persona.setEstado(Boolean.valueOf(true));
/* 378 */       Persona personaSave = this.personaService.savePassword(persona);
/* 379 */       if (personaSave != null) {
/* 380 */         rp = new ResponseEntity(personaSave, null, HttpStatus.ACCEPTED);
        } else {
          
/* 383 */         rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar contraseña");
        } 
      } 
/* 386 */     return rp;
    }
  
  
    
    @PostMapping({"/saveInformCoordinator"})
    public ResponseEntity<?> saveInformCoordinator(@RequestBody CoordinadorDTO cordinador) throws ParseException {
      ResponseEntity<?> rp;
      try {
/* 395 */       SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 396 */       Persona per = this.personaService.findPersonaById(cordinador.getPersona().getId());
/* 397 */       cordinador.setPersona(per);
  
        
/* 400 */       Boolean save = this.servicioService.saveCoordinadorEntity(cordinador);
        
/* 402 */       if (save.booleanValue()) {
          
/* 404 */         rp = ResponseEntity.status(HttpStatus.ACCEPTED).body("informe guardado exitosamente");
        } else {
          
/* 407 */         rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar informe del coordinador");
        } 
/* 409 */     } catch (Exception e) {
        
/* 411 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar informe del coordinador");
      } 
/* 413 */     return rp;
    }
    
    @GetMapping({"/findBirthday"})
    public ResponseEntity<?> findBirthday() throws JsonMappingException, JsonProcessingException, ParseException {
      ResponseEntity<?> rp;
/* 419 */     List<PersonaDto> listadoCumpleanosMes = this.personaService.findBirthdayByMonth();
/* 420 */     List<PersonaDto> listadoCumpleanosDiario = this.personaService.getBirthDay(listadoCumpleanosMes);
/* 421 */     if (listadoCumpleanosDiario.size() > 0) {
/* 422 */       rp = new ResponseEntity(listadoCumpleanosDiario, null, HttpStatus.ACCEPTED);
      } else {
        
/* 425 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no hay cumpleaños hoy");
      } 
/* 427 */     return rp;
    }
    
    @GetMapping({"/reporteTdcIndividual"})
    public ResponseEntity<?> reporteTdcPersona(@RequestParam int idPersona) throws ParseException {
      ResponseEntity<?> rp;
/* 433 */     List<TdcDto> listTCD = this.tdcService.findAlltcdByPersona(idPersona);
  
      
/* 436 */     if (listTCD.size() > 0) {
/* 437 */       rp = new ResponseEntity(listTCD, null, HttpStatus.ACCEPTED);
      } else {
        
/* 440 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no hay reporte");
      } 
/* 442 */     return rp;
    }
    
    @GetMapping({"/findBirthDaysMOnth"})
    public ResponseEntity<?> findBirthDaysMOnth() throws ParseException {
      ResponseEntity<?> rp;
/* 448 */     List<PersonaDto> listadoCumpleanosMes = this.personaService.findBirthdayByMonth();
      
/* 450 */     if (listadoCumpleanosMes.size() > 0) {
/* 451 */       rp = new ResponseEntity(listadoCumpleanosMes, null, HttpStatus.ACCEPTED);
      } else {
        
/* 454 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no hay cumpleaños este mes");
      } 
/* 456 */     return rp;
    }
  
    
    @GetMapping({"/personas/{idpersona}/toggle-active"})
    public ResponseEntity<?> toggleActive(@PathVariable Integer idpersona, @RequestParam boolean active) {
      ResponseEntity<?> rp;
/* 463 */     Persona retorno = this.personaService.toggleActive(idpersona, Boolean.valueOf(active));
/* 464 */     if (retorno.getId() != null) {
/* 465 */       rp = new ResponseEntity(retorno, null, HttpStatus.ACCEPTED);
      } else {
        
/* 468 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("so se puedo actualizar el estado del usuario");
      } 
/* 470 */     return rp;
    }
  
  
  
    
    @GetMapping({"/findTcdPerson"})
    public ResponseEntity<?> findTcdPerson(@RequestParam Integer idPersona, @RequestParam String fechaInicio, @RequestParam String fechaFin) throws ParseException {
      ResponseEntity<?> rp;
/* 479 */     List<TdcDto> listTCD = this.tdcService.findAllBetweenDatesByPersona(this.utilDate.convertStringToDate(fechaInicio), this.utilDate.convertStringToDate(fechaFin), idPersona.intValue());
      
/* 481 */     if (listTCD != null && listTCD.size() > 0) {
/* 482 */       rp = new ResponseEntity(listTCD, null, HttpStatus.ACCEPTED);
      } else {
        
/* 485 */       rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("so se encontro tcd del usuario");
      } 
/* 487 */     return rp;
    }
    
    @PostMapping({"/saveService"})
    public ResponseEntity<?> saveService(@RequestBody List<ServiceDTO> request) {
      ResponseEntity<?> rp;
      try {
/* 494 */       List<Persona> listamultiple = this.servicioService.saveProgram(request);
        
/* 496 */       rp = new ResponseEntity(listamultiple, null, HttpStatus.ACCEPTED);
      }
/* 498 */     catch (ResponseStatusException e) {
/* 499 */       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason());
      } 
/* 501 */     return rp;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\personaReactController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */