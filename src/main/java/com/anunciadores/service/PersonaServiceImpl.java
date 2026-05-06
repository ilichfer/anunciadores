  package  com.anunciadores.service;
  import com.anunciadores.dto.AsignacionServicioDTO;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.dto.PersonaReactDto;
  import com.anunciadores.dto.ServicioResponseDto;
  import com.anunciadores.dto.UserResponseDto;
  import com.anunciadores.model.Consolidacion;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.EstudioPersona;
  import com.anunciadores.model.InscripcionActividad;
  import com.anunciadores.model.Inscripciones;
  import com.anunciadores.model.Pago;
  import com.anunciadores.model.ParamMenu;
  import com.anunciadores.model.PermisosMenu;
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.Rol;
  import com.anunciadores.model.RolPersona;
  import com.anunciadores.model.Servicio;
  import com.anunciadores.model.inscripcionConsolidacion;
  import com.anunciadores.repository.IConsolidacionRepo;
  import com.anunciadores.repository.IParamMenuRepo;
  import com.anunciadores.repository.IPermisosSubMenuRepo;
  import com.anunciadores.repository.InscripcionRepo;
  import java.math.BigInteger;
  import java.security.MessageDigest;
  import java.security.NoSuchAlgorithmException;
  import java.time.LocalDate;
  import java.time.ZonedDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.ArrayList;
  import java.util.Date;
  import java.util.List;
  import java.util.Optional;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.data.domain.Sort;
  
  @Service
  @Transactional
  public class PersonaServiceImpl implements IPersonaService {
/*  40 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.service.PersonaServiceImpl.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    private IPersonaRepo personaRepository;
    
    @Autowired
    private IConsolidacionRepo iConsolidacionRepo;
    
    @Autowired
    private RolesRepoImpl rolesDao;
    
    @Autowired
    private IRolesRepo rolesRepo;
    
    @Autowired
    private IPermisosRepo permisosRepo;
    
    @Autowired
    private IMensajesRepo mensajesRepo;
    
    @Autowired
    private IServicioRepo servicioRepo;
    
    @Autowired
    private IMinisterioRepo ministerioRepo;
    
    @Autowired
    private ICoordinadorRepo coordinadorRepo;
    
    @Autowired
    private IParamMenuRepo paramMenuRepo;
    
    @Autowired
    private IPermisosSubMenuRepo permisosSubMenuRepo;
    
    @Autowired
    private IRolesPersonaRepo rolesPersonaRepository;
    
    @Autowired
    private IEstudiosPersonaRepo estudiosPersonaRepository;
    
    @Autowired
    private IMenuService menuService;
    
    @Autowired
    private InscripcionRepo inscripcionesRepository;
    
    @Autowired
    private ConsolidacionRepoImpl consolidacionDao;
    
    @Autowired
    private IPagoService pagoService;
    
    @Autowired
    private ICursoService cursoService;
    
    @Autowired
    private InscripcionActividadRepo inscripcionActividadRepository;
    
    @Autowired
    private mapperMensaje mapperMensaje;
    
    @Autowired
    private UtilDate utilDate;
    
    @Autowired
    private JwtService jwtService;
    
    List<Persona> listPersonas;
    List<PersonaDto> listPersonasDto;
    
    public List<Persona> findAllUsuarios() {
/* 115 */     return this.personaRepository.findUsuarios();
    }
  
    
    public Persona save(Persona persona) {
/* 120 */     RolPersona rolPersona = new RolPersona();
/* 121 */     persona.setPassword(encriptar(persona.getPassword()));
/* 122 */     Persona personaSave = (Persona)this.personaRepository.save(persona);
      
/* 124 */     rolPersona.setIdPersona(personaSave.getId().intValue());
/* 125 */     rolPersona.setIdRol(2);
/* 126 */     this.rolesPersonaRepository.save(rolPersona);
      
/* 128 */     return personaSave;
    }
  
    
    public Persona update(Persona persona) {
/* 133 */     Persona pUpdate = this.personaRepository.findByDocumento(persona.getDocumento());
/* 134 */     persona.setPassword((pUpdate.getPassword() != null) ? pUpdate.getPassword() : "");
/* 135 */     return (Persona)this.personaRepository.save(persona);
    }
  
    
    public Persona findPersonaById(Integer id) {
/* 140 */     Optional<Persona> persona = this.personaRepository.findById(id);
/* 141 */     return persona.get();
    }
  
    
    public Persona findPersonaByNombre(String nombre) {
/* 146 */     Persona persona = this.personaRepository.findByNombre(nombre);
/* 147 */     return persona;
    }
  
    
    public String delete(Persona persona) {
/* 152 */     Persona personaSave = new Persona();
/* 153 */     personaSave = this.personaRepository.findById(persona.getId()).get();
/* 154 */     personaSave.setEstado(Boolean.valueOf(false));
/* 155 */     this.personaRepository.save(personaSave);
/* 156 */     return "asistente";
    }
  
  
  
    
    public Persona toggleActive(Integer idPersona, Boolean estado) {
/* 163 */     Optional<Persona> personaSave = this.personaRepository.findById(idPersona);
/* 164 */     ((Persona)personaSave.get()).setEstado(estado);
/* 165 */     Persona personaUpdate = (Persona)this.personaRepository.save(personaSave.get());
/* 166 */     return personaUpdate;
    }
  
  
    
    public String habilitar(Persona persona) {
/* 172 */     Persona personaSave = new Persona();
/* 173 */     personaSave = this.personaRepository.findById(persona.getId()).get();
/* 174 */     personaSave.setEstado(Boolean.valueOf(true));
/* 175 */     this.personaRepository.save(personaSave);
/* 176 */     return "asistente";
    }
  
    
    public PersonaDto buscarEmail(String email) {
/* 181 */     Persona per = new Persona();
/* 182 */     PersonaDto personadto = new PersonaDto();
      try {
/* 184 */       per = this.personaRepository.findByEmail(email);
  
        
/* 187 */       if (per != null) {
/* 188 */         personadto.setNombre(per.getNombre());
/* 189 */         personadto.setApellido(per.getApellido());
/* 190 */         personadto.setDocumento(per.getDocumento());
/* 191 */         personadto.setTipodocumento(per.getTipodocumento());
/* 192 */         personadto.setEmail(per.getEmail());
/* 193 */         personadto.setId(per.getId().intValue());
/* 194 */         personadto.setFechanacimiento(per.getFechanacimiento());
/* 195 */         personadto.setTelefono(per.getTelefono());
/* 196 */         personadto.setPassword(per.getPassword());
/* 197 */         personadto.setRoles(new ArrayList());
          
/* 199 */         List<RolPersona> rol = new ArrayList<>();
          
/* 201 */         List<Rol> roles = this.rolesDao.buscarRoles(Integer.valueOf(personadto.getId()));
          
/* 203 */         for (Rol rolAsignado : roles) {
/* 204 */           if (rolAsignado.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
/* 205 */             personadto.getRoles().add(rolAsignado);
/* 206 */             personadto.setAdmin(true);
/* 207 */             personadto.setUser(false); continue;
            } 
/* 209 */           personadto.getRoles().add(rolAsignado);
/* 210 */           personadto.setAdmin(false);
/* 211 */           personadto.setUser(true);
          
          }
        
        }
      
      }
/* 218 */     catch (Exception e) {
/* 219 */       e.printStackTrace();
      } 
/* 221 */     return personadto;
    }
  
  
    
    public List<Persona> findAllByCurso(int idCurso) {
/* 227 */     this.listPersonas = this.personaRepository.findPersonaByCurso(Integer.valueOf(idCurso));
/* 228 */     return this.listPersonas;
    }
  
  
  
  
  
  
  
    
    public List<Persona> buscarTodosSinCurso(int idCurso) {
/* 239 */     return this.personaRepository.findPersonaSinCurso(Integer.valueOf(idCurso));
    }
  
  
    
    public void eliminarPersonaCurso(int idPersona, int idCurso) {
      try {
/* 246 */       this.personaRepository.deletePersonaConCurso(idPersona, idCurso);
/* 247 */     } catch (Exception e) {
/* 248 */       e.printStackTrace();
      } 
    }
  
  
  
    
    public void eliminarPersonaMinisterio(int idPersona, int idMinisterio) {
      try {
/* 257 */       this.personaRepository.deletePersonaMinisterio(idPersona, idMinisterio);
/* 258 */     } catch (Exception e) {
/* 259 */       e.printStackTrace();
      } 
    }
  
  
  
    
    public void agregarPersonaCurso(int idPersona, int idCurso) {
      try {
/* 268 */       Inscripciones inscripcion = new Inscripciones();
/* 269 */       inscripcion.setIdCurso(idCurso);
/* 270 */       inscripcion.setIdPersona(idPersona);
        
/* 272 */       Inscripciones inscripciones1 = (Inscripciones)this.inscripcionesRepository.save(inscripcion);
      }
/* 274 */     catch (Exception e) {
/* 275 */       e.printStackTrace();
      } 
    }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
    public void agregarPersonaActividad(int idPersona, int idActividad) {
      try {
/* 300 */       InscripcionActividad inscripcion = new InscripcionActividad();
/* 301 */       inscripcion.setIdActividad(idActividad);
/* 302 */       inscripcion.setIdPersona(idPersona);
        
/* 304 */       this.inscripcionActividadRepository.save(inscripcion);
      }
/* 306 */     catch (Exception e) {
/* 307 */       e.printStackTrace();
      } 
    }
  
  
    
    public PersonaDto buscarByDocumento(Integer doc) {
/* 314 */     Persona per = new Persona();
/* 315 */     PersonaDto personadto = new PersonaDto();
  
      
      try {
/* 319 */       per = this.personaRepository.findByDocumento(doc);
/* 320 */       if (per != null && per.getDocumento() != null) {
/* 321 */         personadto.setNombre(per.getNombre());
/* 322 */         personadto.setApellido(per.getApellido());
/* 323 */         personadto.setDocumento(per.getDocumento());
/* 324 */         personadto.setTipodocumento(per.getTipodocumento());
/* 325 */         personadto.setEmail(per.getEmail());
/* 326 */         personadto.setId(per.getId().intValue());
/* 327 */         personadto.setFechanacimiento(per.getFechanacimiento());
/* 328 */         personadto.setTelefono(per.getTelefono());
/* 329 */         personadto.setPassword((per.getPassword() != null) ? per.getPassword() : "");
/* 330 */         personadto.setRoles(new ArrayList());
/* 331 */         List<RolPersona> rol = new ArrayList<>();
          
/* 333 */         List<Rol> roles = this.rolesDao.buscarRoles(Integer.valueOf(personadto.getId()));
/* 334 */         List<PermisosMenu> permisos = null;
          
/* 336 */         for (Rol rolAsignado : roles) {
/* 337 */           if (rolAsignado.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
  
              
/* 340 */             permisos = this.menuService.findAllPermisosMenu(personadto.getId());
/* 341 */             personadto.getRoles().add(rolAsignado);
/* 342 */             personadto.setAdmin(true);
/* 343 */             personadto.setUser(false);
/* 344 */             personadto.setPermisosMenu(permisos);
            } else {
/* 346 */             List<ParamMenu> menuList = this.paramMenuRepo.findAll();
/* 347 */             personadto.getRoles().add(rolAsignado);
/* 348 */             personadto.setAdmin(false);
/* 349 */             personadto.setUser(true);
/* 350 */             personadto.setPermisosMenu(contruirPermisosServidor(menuList));
            } 
/* 352 */           personadto.setMensajes(this.mapperMensaje.listEntityToMensajesDTO(this.mensajesRepo.mesajesSinLeerByIdPersona(personadto.getId())));
  
            
/* 355 */           personadto.setAsignacion(validateServiceByPersonAndDate(per.getId().intValue()));
          } 
        } else {
/* 358 */         personadto = new PersonaDto();
        }
      
/* 361 */     } catch (Exception e) {
/* 362 */       this.LOGGER.error("[buscarByDocumento] " + e.getMessage());
/* 363 */       e.printStackTrace();
/* 364 */       personadto = new PersonaDto();
/* 365 */       personadto.setId(1);
/* 366 */       throw new RuntimeException("[buscarByDocumento]" + e.getMessage());
      } 
/* 368 */     return personadto;
    }
  
    
    private void compararPermisos(List<PermisosMenu> permisos, int idPersona) {
/* 373 */     List<ParamMenu> menuList = this.paramMenuRepo.findAll();
/* 374 */     List<PermisosMenu> permisosCompletos = contruirPermisosServidor(menuList);
/* 375 */     List<PermisosMenu> permisosActivos = new ArrayList<>();
      
/* 377 */     for (PermisosMenu min : permisosCompletos) {
/* 378 */       for (int i = 0; i < permisos.size(); i++) {
/* 379 */         if (((PermisosMenu)permisos.get(i)).getMenu().getNombreBotonMenu() == min.getMenu().getNombreBotonMenu()) {
/* 380 */           permisosActivos.add(min);
          }
        } 
      } 
      
/* 385 */     permisosCompletos.removeAll(permisosActivos);
      
/* 387 */     if (!permisosCompletos.isEmpty()) {
/* 388 */       for (PermisosMenu permSave : permisosCompletos) {
/* 389 */         PermisosMenu perm = new PermisosMenu();
/* 390 */         perm.setIdPersona(idPersona);
/* 391 */         perm.setNombreBotonMenu(permSave.getMenu().getNombreBotonMenu());
/* 392 */         perm.setEstado("false");
/* 393 */         perm.setMenu(permSave.getMenu());
/* 394 */         this.permisosRepo.save(perm);
        } 
      }
    }
    
    private List<PermisosMenu> contruirPermisosServidor(List<ParamMenu> menuList) {
/* 400 */     List<PermisosMenu> listPermisosIniciales = new ArrayList<>();
/* 401 */     String estadoInicial = "false";
      
/* 403 */     for (ParamMenu boton : menuList) {
/* 404 */       PermisosMenu permisoInicial = new PermisosMenu();
/* 405 */       permisoInicial.setIdPersona(1);
        
/* 407 */       permisoInicial.setEstado(estadoInicial);
/* 408 */       permisoInicial.setNombreBotonMenu(boton.getNombreBotonMenu());
/* 409 */       permisoInicial.setMenu(boton);
/* 410 */       listPermisosIniciales.add(permisoInicial);
      } 
/* 412 */     return listPermisosIniciales;
    }
  
    
    public Persona saveAsistente(Persona persona) {
/* 417 */     persona.setPassword("");
/* 418 */     persona.setDiscapacidad((persona.getDiscapacidad() != null) ? persona.getDiscapacidad().booleanValue() : false);
/* 419 */     persona.setPerteneceMinoria((persona.getPerteneceMinoria() != null) ? persona.getPerteneceMinoria().booleanValue() : false);
/* 420 */     return (Persona)this.personaRepository.save(persona);
    }
  
    
    public Persona saveAsistenteConsolidacion(Persona persona, Consolidacion consolidacion) {
/* 425 */     persona.setPassword("");
/* 426 */     persona.setConsolidacion(Boolean.valueOf(true));
/* 427 */     persona.setDiscapacidad((persona.getDiscapacidad() != null) ? persona.getDiscapacidad().booleanValue() : false);
/* 428 */     persona.setPerteneceMinoria((persona.getPerteneceMinoria() != null) ? persona.getPerteneceMinoria().booleanValue() : false);
/* 429 */     persona = (Persona)this.personaRepository.save(persona);
/* 430 */     consolidacion.setIdPersona(persona.getId().intValue());
/* 431 */     consolidacion.setAceptaConsolidacion(Boolean.valueOf((persona.getConsolidacion() != null) ? persona.getConsolidacion().booleanValue() : false));
/* 432 */     this.iConsolidacionRepo.save(consolidacion);
/* 433 */     return persona;
    }
  
    
    public List<PersonaDto> buscarConsolidacion(List<Persona> listaPersonas, int idCurso) {
/* 438 */     List<PersonaDto> listaPersonasConsolidacion = new ArrayList<>();
/* 439 */     listaPersonas.forEach(p -> listaPersonasConsolidacion.add(agregarConsolidacion(p, idCurso)));
/* 440 */     return listaPersonasConsolidacion;
    }
    
    public PersonaDto agregarConsolidacion(Persona persona, int idCurso) {
/* 444 */     PersonaDto perConsolidacion = new PersonaDto();
/* 445 */     perConsolidacion.setNombre(persona.getNombre());
/* 446 */     perConsolidacion.setApellido(persona.getApellido());
/* 447 */     perConsolidacion.setDocumento(persona.getDocumento());
/* 448 */     perConsolidacion.setEmail(persona.getEmail());
/* 449 */     perConsolidacion.setFechanacimiento(persona.getFechanacimiento());
/* 450 */     perConsolidacion.setId(persona.getId().intValue());
/* 451 */     perConsolidacion.setTelefono(persona.getTelefono());
/* 452 */     perConsolidacion.setTipodocumento(persona.getTipodocumento());
      
/* 454 */     inscripcionConsolidacion inscripcionConsolidacion = this.consolidacionDao.listarConsolidacionByPersona(persona.getId().intValue());
/* 455 */     if (inscripcionConsolidacion != null) {
/* 456 */       perConsolidacion.setConsolidacion(true);
      } else {
/* 458 */       perConsolidacion.setConsolidacion(false);
      } 
/* 460 */     if (idCurso != 0) {
/* 461 */       List<Pago> pagoList = this.pagoService.findPagosByIdCurso(persona.getId().intValue(), idCurso);
/* 462 */       Curso curso = new Curso();
/* 463 */       curso = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 464 */       int pagoTotal = 0;
/* 465 */       int adeuda = 0;
        
/* 467 */       for (Pago pago : pagoList) {
/* 468 */         pagoTotal += pago.getValor();
        }
/* 470 */       if (pagoTotal >= curso.getValorTotal()) {
/* 471 */         perConsolidacion.setValidarPago(true);
        }
      } 
  
  
  
      
/* 478 */     return perConsolidacion;
    }
  
    
    public String encriptar(String Pass) {
      try {
/* 484 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 485 */       byte[] messageDigest = md.digest(Pass.getBytes());
/* 486 */       BigInteger number = new BigInteger(1, messageDigest);
/* 487 */       String hashtext = number.toString(16);
        
/* 489 */       while (hashtext.length() < 32) {
/* 490 */         hashtext = "0" + hashtext;
        }
/* 492 */       return hashtext;
/* 493 */     } catch (NoSuchAlgorithmException e) {
/* 494 */       throw new RuntimeException(e);
      } 
    }
  
    
    public Persona personaDtoToEntity(PersonaDto dto) {
/* 500 */     Persona per = new Persona();
      try {
/* 502 */       per.setNombre(dto.getNombre());
/* 503 */       per.setApellido(dto.getApellido());
/* 504 */       per.setDocumento(dto.getDocumento());
/* 505 */       per.setTipodocumento(dto.getTipodocumento());
/* 506 */       per.setEmail(dto.getEmail());
/* 507 */       per.setId(Integer.valueOf(dto.getId()));
/* 508 */       per.setFechanacimiento(dto.getFechanacimiento());
/* 509 */       per.setTelefono(dto.getTelefono());
/* 510 */       per.setPassword(dto.getPassword());
/* 511 */     } catch (Exception e) {
/* 512 */       e.printStackTrace();
      } 
/* 514 */     return per;
    }
  
    
    public Persona savePassword(Persona persona) {
/* 519 */     persona.setPassword(encriptar(persona.getPassword()));
/* 520 */     return (Persona)this.personaRepository.save(persona);
    }
  
    
    public List<PersonaDto> findAllUsuariosRol() {
/* 525 */     List<PersonaDto> listDto = new ArrayList<>();
/* 526 */     List<Persona> personas = this.personaRepository.findUsuarios();
/* 527 */     personas.forEach(p -> listDto.add(mapPersonaDto(p)));
/* 528 */     return listDto;
    }
  
    
    public List<PersonaDto> findBirthdayByMonth() {
/* 533 */     List<PersonaDto> listDto = new ArrayList<>();
      
/* 535 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 536 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 537 */     String fechaActual = nowInBogota.format(formatter);
      
/* 539 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 540 */     LocalDate ld = LocalDate.parse(fechaActual, dtf);
      
/* 542 */     int month = ld.getMonthValue();
/* 543 */     Integer diaactual = Integer.valueOf(ld.getDayOfMonth());
/* 544 */     List<Object> obj = new ArrayList();
/* 545 */     List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/* 546 */     obj = this.personaRepository.buscarCumpleaños(month);
/* 547 */     obj.forEach(p -> listDto.add(mapCumplePersonaDto(p, diaactual.toString())));
/* 548 */     return listDto;
    }
  
    
    public List<PersonaDto> getBirthDay(List<PersonaDto> listDto) {
/* 553 */     List<PersonaDto> listcCumpleActual = new ArrayList<>();
      
/* 555 */     for (PersonaDto dto : listDto) {
/* 556 */       if (dto.isCumpleActual()) {
/* 557 */         listcCumpleActual.add(dto);
        }
      } 
/* 560 */     return listcCumpleActual;
    }
  
    
    public List<PersonaReactDto> findAllUsers() {
/* 565 */     List<PersonaReactDto> listpersonResult = new ArrayList<>();
/* 566 */     List<Persona> listp = this.personaRepository.findAll(Sort.by(Sort.Direction.ASC, new String[] { "nombre" }));
/* 567 */     listp.forEach(p -> listpersonResult.add(mapPersonaReactDto(p)));
/* 568 */     return listpersonResult;
    }
  
  
  
    
    public UserResponseDto getUsuarioDesdeToken(String token) {
/* 575 */     if (!this.jwtService.esTokenValido(token)) {
/* 576 */       throw new RuntimeException("Token inválido o expirado");
      }
  
      
/* 580 */     String cedula = this.jwtService.getCedulaDesdeToken(token);
  
      
/* 583 */     Persona persona = this.personaRepository.findByDocumento(Integer.valueOf(Integer.parseInt(cedula)));
/* 584 */     if (persona == null) {
/* 585 */       throw new RuntimeException("Usuario no encontrado");
      }
/* 587 */     UserResponseDto user = new UserResponseDto(persona);
/* 588 */     user.setMinistry(this.ministerioRepo.findNameMinisterio(user.getId().intValue()));
      
/* 590 */     return user;
    }
  
    
    private PersonaReactDto mapPersonaReactDto(Persona persona) {
/* 595 */     PersonaReactDto dto = new PersonaReactDto();
      try {
/* 597 */       dto.setId(persona.getId().intValue());
/* 598 */       dto.setName(persona.getNombre() + " " + persona.getNombre());
/* 599 */       dto.setEmail(persona.getEmail());
/* 600 */       dto.setPhone(persona.getTelefono());
/* 601 */       dto.setActive(persona.getEstado());
/* 602 */       List<Rol> roles = this.rolesDao.buscarRoles(persona.getId());
/* 603 */       for (Rol rol : roles) {
/* 604 */         if (rol.getDescripcion().equalsIgnoreCase("ADMINISTRADOR")) {
/* 605 */           dto.setRole(rol.getDescripcion());
          }
        } 
/* 608 */     } catch (Exception e) {
/* 609 */       e.printStackTrace();
      } 
/* 611 */     return dto;
    }
  
    
    public void findUsuariosRol(int idPersona, int idRolNuevo) {
/* 616 */     List<RolPersona> rol = new ArrayList<>();
/* 617 */     rol = this.rolesPersonaRepository.findRolByidPersona(idPersona);
/* 618 */     RolPersona rolUpdate = rol.get(0);
/* 619 */     rolUpdate.setIdRol(idRolNuevo);
/* 620 */     this.rolesPersonaRepository.save(rolUpdate);
/* 621 */     if (rolUpdate.getIdRol() == 1) {
/* 622 */       List<PermisosMenu> listpermisos = this.permisosRepo.findByIdPersona(idPersona);
/* 623 */       if (listpermisos.size() == 0) {
/* 624 */         List<PermisosMenu> listRolsInicial = crearRolesPrimerVezAdmin(idPersona);
/* 625 */         for (PermisosMenu permiso : listRolsInicial) {
/* 626 */           this.permisosRepo.save(permiso);
          }
        } 
      } 
    }
  
    
    private List<PermisosMenu> crearRolesPrimerVezAdmin(int idPersona) {
/* 634 */     List<ParamMenu> menuList = this.paramMenuRepo.findAll();
      
/* 636 */     List<PermisosMenu> listPermisosIniciales = new ArrayList<>();
/* 637 */     String estadoInicial = "true";
      
/* 639 */     for (ParamMenu boton : menuList) {
/* 640 */       PermisosMenu permisoInicial = new PermisosMenu();
/* 641 */       permisoInicial.setIdPersona(idPersona);
        
/* 643 */       if (boton.getNombreBotonMenu().equals("menuAdministrar")) {
/* 644 */         permisoInicial.setEstado("false");
        } else {
/* 646 */         permisoInicial.setEstado(estadoInicial);
        } 
/* 648 */       permisoInicial.setNombreBotonMenu(boton.getNombreBotonMenu());
/* 649 */       permisoInicial.setMenu(boton);
/* 650 */       listPermisosIniciales.add(permisoInicial);
      } 
/* 652 */     return listPermisosIniciales;
    }
  
    
    public List<EstudioPersona> findEstudiosPersona(int idPersona) {
/* 657 */     List<EstudioPersona> estudios = this.estudiosPersonaRepository.findByIdPersona(idPersona);
/* 658 */     return estudios;
    }
  
    
    public EstudioPersona saveEstudio(EstudioPersona estudioNew) {
/* 663 */     EstudioPersona estudio = (EstudioPersona)this.estudiosPersonaRepository.save(estudioNew);
/* 664 */     return estudio;
    }
    
    private PersonaDto mapPersonaDto(Persona persona) {
/* 668 */     PersonaDto dto = new PersonaDto();
      try {
/* 670 */       dto.setNombre(persona.getNombre());
/* 671 */       dto.setApellido(persona.getApellido());
/* 672 */       dto.setDocumento(persona.getDocumento());
/* 673 */       dto.setTipodocumento(persona.getTipodocumento());
/* 674 */       dto.setEmail(persona.getEmail());
/* 675 */       dto.setId(persona.getId().intValue());
/* 676 */       dto.setFechanacimiento(persona.getFechanacimiento());
/* 677 */       dto.setTelefono(persona.getTelefono());
/* 678 */       dto.setPassword(persona.getPassword());
/* 679 */       List<Rol> roles = this.rolesDao.buscarRoles(persona.getId());
/* 680 */       roles.forEach(r -> dto.setRolUnico(r));
/* 681 */       dto.setRoles(roles);
/* 682 */     } catch (Exception e) {
/* 683 */       e.printStackTrace();
      } 
/* 685 */     return dto;
    }
    
    private PersonaDto mapCumplePersonaDto(Object obj, String dia) {
/* 689 */     PersonaDto dto = new PersonaDto();
/* 690 */     Object[] object = (Object[])obj;
      try {
/* 692 */       dto.setNombre(object[0].toString());
/* 693 */       dto.setApellido(object[1].toString());
/* 694 */       dto.setFechanacimiento(object[2].toString());
/* 695 */       dto.setCumpleActual(dia.equals(object[2].toString()));
      }
/* 697 */     catch (Exception e) {
/* 698 */       e.printStackTrace();
      } 
/* 700 */     return dto;
    }
    
    private List<AsignacionServicioDTO> validateServiceByPersonAndDate(int idPersona) {
/* 704 */     List<AsignacionServicioDTO> asignacion = new ArrayList<>();
/* 705 */     Optional<Date> fechaSiguiente = this.servicioRepo.findNextDateService();
/* 706 */     Optional<List<Servicio>> ServiciosProximos = this.servicioRepo.findAllServiceByDateAndPerson(idPersona, fechaSiguiente.get());
/* 707 */     if (ServiciosProximos.isPresent()) {
        
/* 709 */       List<Servicio> listServices = ServiciosProximos.get();
/* 710 */       listServices.forEach(serv -> {
              AsignacionServicioDTO asig = new AsignacionServicioDTO();
              
              Object objRepo = this.ministerioRepo.findMnisteryAndPosition(serv.getIdMinisterio(), serv.getIdPosicion());
              
              Object[] object = (Object[])objRepo;
              
              try {
                asig.setMInisterio(object[0].toString());
                asig.setPosiciones(object[1].toString());
                asignacion.add(asig);
/* 721 */             } catch (Exception e) {
                e.printStackTrace();
              } 
            });
      } 
      
/* 727 */     return asignacion;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\PersonaServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */