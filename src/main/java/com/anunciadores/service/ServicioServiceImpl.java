/*      */ package  com.anunciadores.service;
/*      */ import com.anunciadores.dto.CoordinadorDTO;
/*      */ import com.anunciadores.dto.CordinatorDto;
/*      */ import com.anunciadores.dto.MinisterioDto;
/*      */ import com.anunciadores.dto.MinistryDto;
/*      */ import com.anunciadores.dto.MinistryMember;
/*      */ import com.anunciadores.dto.PersonaDto;
/*      */ import com.anunciadores.dto.PosicionDto;
/*      */ import com.anunciadores.dto.ProgramationDto;
/*      */ import com.anunciadores.dto.ServiceDTO;
/*      */ import com.anunciadores.dto.ServicioDto;
/*      */ import com.anunciadores.dto.ServicioListResponseDto;
/*      */ import com.anunciadores.dto.ServicioResponseDto;
/*      */ import com.anunciadores.model.Coordinador;
/*      */ import com.anunciadores.model.Ministerio;
/*      */ import com.anunciadores.model.Persona;
/*      */ import com.anunciadores.model.PersonaMinisterio;
/*      */ import com.anunciadores.model.PosicionesMinisterio;
/*      */ import com.anunciadores.model.Servicio;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.time.LocalDate;
/*      */ import java.time.format.DateTimeFormatter;
/*      */ import java.util.*;
/*      */
/*      */
/*      */
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ 
/*      */ @Service
/*      */ public class ServicioServiceImpl implements IServicioService {
/*   32 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.service.ServicioServiceImpl.class);
/*      */   
/*      */   @Autowired
/*      */   private IMinisterioRepo ministerioRepository;
/*      */   
/*      */   @Autowired
/*      */   private IPosicionesRepo posicionesRepository;
/*      */   
/*      */   @Autowired
/*      */   private IServicioRepo servicioRepository;
/*      */   
/*      */   @Autowired
/*      */   private ICoordinadorRepo coordinadorRepo;
/*      */   
/*      */   @Autowired
/*      */   private IPersonaMinisterioRepo personaMinisterioRepoSitory;
/*      */   
/*      */   @Autowired
/*      */   private IPersonaRepo personaRepository;
/*      */   
/*      */   @Autowired
/*      */   private IParametrosRepo parametrosRepo;
/*      */   
/*      */   @Autowired
/*      */   private mapperParametros mapperParametros;
/*      */   
/*      */   @Autowired
/*      */   private UtilDate utilDate;
/*      */   
/*      */   public List<Ministerio> getAll() {
/*   62 */     List<Ministerio> listaMinisterio = new ArrayList<>();
/*   63 */     listaMinisterio = this.ministerioRepository.findAll();
/*   64 */     return listaMinisterio;
/*      */   }
/*      */ 
/*      */   
/*      */   public Ministerio saveMinisterio(String nombreMinisterio) {
/*   69 */     Ministerio ministerio = new Ministerio();
/*   70 */     ministerio.setNombre(nombreMinisterio);
/*      */     
/*   72 */     return (Ministerio)this.ministerioRepository.save(ministerio);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Ministerio saveMinisterio(MinisterioDto ministerioDto) {
/*   78 */     Ministerio ministerio = new Ministerio();
/*   79 */     ministerio.setNombre(ministerioDto.getNombreMinisterio());
/*   80 */     ministerio.setId(ministerioDto.getId());
/*   81 */     return (Ministerio)this.ministerioRepository.save(ministerio);
/*      */   }
/*      */ 
/*      */   
/*      */   public void deleteMinisterio(int idMinisterio) {
/*   86 */     this.ministerioRepository.deleteById(Integer.valueOf(idMinisterio));
/*      */   }
/*      */ 
/*      */   
/*      */   public Ministerio findByidMnisterio(int idMinisterio) {
/*   91 */     return this.ministerioRepository.findById(Integer.valueOf(idMinisterio)).get();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<PersonaDto> findPersonaByidMnisterio(int idMinisterio) {
/*   96 */     List<PersonaDto> listaDto = new ArrayList<>();
/*   97 */     List<Persona> listaEntity = this.ministerioRepository.findPersonasByIdMinisterio(idMinisterio);
/*   98 */     listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
/*   99 */     return listaDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<PersonaDto> findPersonaByidMnisterioAsistencia(int idMinisterio) {
/*  104 */     List<PersonaDto> listaDto = new ArrayList<>();
/*  105 */     List<Persona> listaEntity = this.ministerioRepository.findPersonasByIdMinisterio(idMinisterio);
/*  106 */     listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
/*  107 */     return listaDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MinisterioDto> getPositionByidMinisterio(int idMinisterio) {
/*  112 */     List<PosicionesMinisterio> listaPosiciones = new ArrayList<>();
/*  113 */     List<MinisterioDto> listaPosicionesDto = new ArrayList<>();
/*  114 */     Ministerio ministerio = this.ministerioRepository.findById(Integer.valueOf(idMinisterio)).get();
/*  115 */     listaPosiciones = this.posicionesRepository.findAllByIdMinisterio(idMinisterio);
/*  116 */     listaPosiciones.forEach(p -> listaPosicionesDto.add(mapEntityToDto(p, ministerio.getNombre())));
/*  117 */     return listaPosicionesDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MinisterioDto> getPositionByidMinisterioAndPerson(Date fechaActual, int idMinisterio) {
/*  122 */     List<PosicionesMinisterio> listaPosiciones = new ArrayList<>();
/*  123 */     List<MinisterioDto> listaPosicionesDto = new ArrayList<>();
/*      */     
/*  125 */     List<Object> obj = new ArrayList();
/*  126 */     List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  127 */     obj = this.servicioRepository.findProgramacionByDateAndMinistery(fechaActual, idMinisterio);
/*      */     
/*  129 */     Ministerio ministerio = this.ministerioRepository.findById(Integer.valueOf(idMinisterio)).get();
/*  130 */     listaPosiciones = this.posicionesRepository.findAllByIdMinisterio(idMinisterio);
/*  131 */     List<Object> finalObj = obj;
/*  132 */     listaPosiciones.forEach(p -> listaPosicionesDto.add(mapEntityToDtoAndPerson(p, ministerio.getNombre(), finalObj)));
/*  133 */     return listaPosicionesDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MinisterioDto> limpiarListaPosiciones(List<MinisterioDto> posiciones, Date fechaServicio, int idMinisterio) {
/*  138 */     List<MinisterioDto> listaResponse = new ArrayList<>();
/*  139 */     List<Servicio> servicio = this.servicioRepository.findByFechaServicioAndIdMinisterio(fechaServicio, idMinisterio);
/*      */     
/*  141 */     for (MinisterioDto min : posiciones) {
/*  142 */       for (int i = 0; i < servicio.size(); i++) {
/*  143 */         if (((Servicio)servicio.get(i)).getIdPosicion() == min.getPosicionDto().getId()) {
/*  144 */           min.getPosicionDto().setAsistencia(((Servicio)servicio.get(i)).getAsistencia());
/*  145 */           listaResponse.add(min);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  151 */     return listaResponse;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<PersonaDto> getPeopleWithoutMinisterio(int idMinisterio) {
/*  157 */     List<PersonaDto> listaDto = new ArrayList<>();
/*  158 */     List<Persona> listaEntity = this.ministerioRepository.findPeopleWithOutMinisterio(idMinisterio);
/*  159 */     listaEntity.forEach(p -> listaDto.add(mapPersonaToDto(p)));
/*  160 */     return listaDto;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveProgramacion(ServicioDto servidores, Date fechaServicio, int idMinisterio) {
/*  166 */     List<Servicio> progServicio = new ArrayList<>();
/*      */ 
/*      */     
/*      */     try {
/*  170 */       for (int i = 0; i < servidores.getPosicion().size(); i++) {
/*  171 */         Servicio servicio = new Servicio();
/*  172 */         List<PosicionesMinisterio> posicionEntity = this.posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i), idMinisterio);
/*  173 */         servicio.setIdPosicion(((PosicionesMinisterio)posicionEntity.get(0)).getId());
/*  174 */         int posicion = Integer.parseInt(servidores.getEncargado().get(i));
/*  175 */         servicio.setIdPersona(Integer.parseInt(servidores.getEncargado().get(i)));
/*  176 */         servicio.setFechaServicio(fechaServicio);
/*  177 */         servicio.setIdMinisterio(((PosicionesMinisterio)posicionEntity.get(0)).getIdMinisterio());
/*  178 */         progServicio.add(servicio);
/*      */       } 
/*  180 */       this.servicioRepository.saveAll(progServicio);
/*  181 */     } catch (Exception e) {
/*  182 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean saveCoordinado(CoordinadorDTO cordinador) throws ParseException {
/*  188 */     Boolean response = Boolean.valueOf(false);
/*  189 */     Date fechaSave = this.utilDate.convertStringToDate(cordinador.getFechaString());
/*      */     
/*      */     try {
/*  192 */       Coordinador cor = new Coordinador();
/*  193 */       Optional<Coordinador> cord = this.coordinadorRepo.findByIdPersonaAndIdPersona(fechaSave, cordinador.getIdPersona());
/*      */       
/*  195 */       if (!cord.isPresent()) {
/*  196 */         cor.setPersona(this.personaRepository.findById(Integer.valueOf(cordinador.getIdPersona())).get());
/*  197 */         cor.setFechaServicio(this.utilDate.convertStringToDate(cordinador.getFechaString()));
/*  198 */         this.coordinadorRepo.save(cor);
/*  199 */         response = Boolean.TRUE;
/*      */       }
/*      */     
/*  202 */     } catch (Exception e) {
/*  203 */       throw e;
/*      */     } 
/*  205 */     return response;
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean saveCoordinadorEntity(CoordinadorDTO cordinador) {
/*      */     try {
/*  211 */       Optional<Coordinador> CorOpt = this.coordinadorRepo.findByIdPersonaAndIdPersona(this.utilDate.convertStringToDate(cordinador.getFechaString()), cordinador.getPersona().getId().intValue());
/*  212 */       if (CorOpt.isPresent()) {
/*  213 */         ((Coordinador)CorOpt.get()).setNotasServicio(cordinador.getNotasServicio());
/*  214 */         this.coordinadorRepo.save(CorOpt.get());
/*  215 */         return Boolean.valueOf(true);
/*      */       } 
/*  217 */       return Boolean.valueOf(false);
/*  218 */     } catch (Exception e) {
/*  219 */       return Boolean.valueOf(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Coordinador findCoordinador(List<ServicioListResponseDto> listProgramacionMinisterio) {
/*  225 */     Coordinador cor = new Coordinador();
/*      */     try {
/*  227 */       for (ServicioListResponseDto serv : listProgramacionMinisterio) {
/*  228 */         if (serv.getFechaServcio() != null) {
/*  229 */           SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
/*  230 */           Date fechaDate = null;
/*      */           try {
/*  232 */             fechaDate = formato.parse(serv.getFechaServcio());
/*  233 */           } catch (ParseException e) {
/*  234 */             this.LOGGER.error("[findCoordinador] " + e.getMessage());
/*  235 */             throw new RuntimeException("[findCoordinador] " + e.getMessage());
/*      */           } 
/*  237 */           Coordinador cord = this.coordinadorRepo.findByFechaServicio(fechaDate);
/*  238 */           return cord;
/*      */         } 
/*      */       } 
/*  241 */     } catch (Exception e) {
/*  242 */       this.LOGGER.error("[findCoordinador] " + e.getMessage());
/*  243 */       throw new RuntimeException("[findCoordinador] " + e.getMessage());
/*      */     } 
/*  245 */     return cor;
/*      */   }
/*      */ 
/*      */   
/*      */   public Coordinador findCoordinadorAdministrator(HttpServletRequest request) {
/*  250 */     HttpSession misession = request.getSession();
/*  251 */     Coordinador cor = new Coordinador();
/*  252 */     Integer menuCordinador = Integer.valueOf(9);
/*  253 */     Integer idpersona = Integer.valueOf(((Integer)misession.getAttribute("idPersona")).intValue());
/*      */     try {
/*  255 */       Persona per = this.personaRepository.findPersonaAndIdMenu(idpersona.intValue(), menuCordinador.intValue());
/*  256 */       cor.setId(per.getId().intValue());
/*  257 */       cor.setPersona(per);
/*  258 */     } catch (Exception e) {
/*  259 */       e.getMessage();
/*      */     } 
/*      */ 
/*      */     
/*  263 */     return cor;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Coordinador findCoordinadorByFecha(Date fechaServicio) {
/*  269 */     return this.coordinadorRepo.findByFechaServicio(fechaServicio);
/*      */   }
/*      */ 
/*      */   
/*      */   public Coordinador findCoordinadorByFechaAndIdPersona(String fechaServicio, int idPersona) {
/*  274 */     Date fechaDate = null;
/*  275 */     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
/*      */     try {
/*  277 */       fechaDate = formato.parse(fechaServicio);
/*  278 */     } catch (ParseException e) {
/*  279 */       this.LOGGER.error("[findCoordinador] " + e.getMessage());
/*  280 */       throw new RuntimeException("[findCoordinador] " + e.getMessage());
/*      */     } 
/*  282 */     Optional<Coordinador> CorOpt = this.coordinadorRepo.findByIdPersonaAndIdPersona(fechaDate, idPersona);
/*  283 */     if (CorOpt.isPresent()) {
/*  284 */       return CorOpt.get();
/*      */     }
/*  286 */     return new Coordinador();
/*      */   }
/*      */ 
/*      */   
/*      */   public Boolean validateCoordinadorByFechaAndIdPersona(String fechaServicio, int idPersona) {
/*  291 */     Date fechaDate = null;
/*  292 */     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
/*      */     try {
/*  294 */       fechaDate = formato.parse(fechaServicio);
/*  295 */     } catch (ParseException e) {
/*  296 */       this.LOGGER.error("[findCoordinador] " + e.getMessage());
/*  297 */       throw new RuntimeException("[findCoordinador] " + e.getMessage());
/*      */     } 
/*  299 */     Optional<Coordinador> CorOpt = this.coordinadorRepo.findByIdPersonaAndIdPersona(fechaDate, idPersona);
/*  300 */     if (CorOpt.isPresent()) {
/*  301 */       return Boolean.valueOf(true);
/*      */     }
/*  303 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public void updateProgramacion(ServicioDto servidores, Date fechaServicio, int idMinisterio) {
/*  310 */     List<Servicio> progServicio = new ArrayList<>();
/*      */     try {
/*  312 */       this.servicioRepository.deleteByFechaServicioAndIdMinisterio(fechaServicio, idMinisterio);
/*  313 */       for (int i = 0; i < servidores.getPosicion().size(); i++) {
/*  314 */         Servicio servicio = new Servicio();
/*  315 */         List<PosicionesMinisterio> posicionEntity = this.posicionesRepository.findMinisterioByName(servidores.getPosicion().get(i), idMinisterio);
/*  316 */         servicio.setFechaServicio(fechaServicio);
/*  317 */         servicio.setIdMinisterio(idMinisterio);
/*  318 */         servicio.setIdPersona(Integer.parseInt(servidores.getEncargado().get(i)));
/*  319 */         servicio.setIdPosicion(((PosicionesMinisterio)posicionEntity.get(0)).getId());
/*  320 */         progServicio.add(servicio);
/*      */       } 
/*  322 */       this.servicioRepository.saveAll(progServicio);
/*  323 */     } catch (Exception e) {
/*  324 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public List<Persona> saveProgram(List<ServiceDTO> servicios) {
/*  331 */     List<Persona> listmultipleAsiganacion = new ArrayList<>();
/*  332 */     List<Servicio> progServicio = new ArrayList<>();
/*      */     try {
/*  334 */       List<Servicio> list = this.servicioRepository.findByFechaServicioAndIdMinisterio(this.utilDate.convertLocaldateToDate(((ServiceDTO)servicios.get(0)).getFechaServicio()), Integer.parseInt(((ServiceDTO)servicios.get(0)).getIdMinisterio()));
/*  335 */       if (!list.isEmpty()) {
/*  336 */         list.forEach(s -> this.servicioRepository.deleteById(Integer.valueOf(s.getId())));
/*      */       }
/*  338 */       for (int i = 0; i < servicios.size(); i++) {
/*  339 */         Servicio servicio = new Servicio();
/*  340 */         List<PosicionesMinisterio> posicionEntity = this.posicionesRepository.findMinisterioByName(((ServiceDTO)servicios.get(i)).getIdPosicion(), Integer.parseInt(((ServiceDTO)servicios.get(i)).getIdMinisterio()));
/*  341 */         servicio.setFechaServicio(this.utilDate.convertLocaldateToDate(((ServiceDTO)servicios.get(i)).getFechaServicio()));
/*  342 */         servicio.setIdMinisterio(Integer.parseInt(((ServiceDTO)servicios.get(i)).getIdMinisterio()));
/*  343 */         servicio.setIdPersona(Integer.parseInt(((ServiceDTO)servicios.get(i)).getIdPersona()));
/*  344 */         servicio.setIdPosicion(Integer.parseInt(((ServiceDTO)servicios.get(i)).getIdPosicion()));
/*  345 */         progServicio.add(servicio);
/*      */         
/*  347 */         Optional<Persona> per = validarPersonaProgramanda(servicio.getIdPersona(), servicio.getFechaServicio());
/*  348 */         if (per.isPresent())
/*      */         {
/*  350 */           listmultipleAsiganacion.add(per.get());
/*      */         }
/*      */       } 
/*  353 */       this.servicioRepository.saveAll(progServicio);
/*      */     }
/*  355 */     catch (Exception e) {
/*  356 */       throw new RuntimeException(e);
/*      */     } 
/*  358 */     return listmultipleAsiganacion;
/*      */   }
/*      */   
/*      */   private Optional<Persona> validarPersonaProgramanda(int idPersona, Date fechaServicio) {
/*      */     try {
/*  363 */       Optional<Servicio> programacion = this.servicioRepository.findProgramacionServidor(idPersona, fechaServicio);
/*      */       
/*  365 */       if (programacion.isPresent()) {
/*  366 */         Optional<Persona> per = this.personaRepository.findById(Integer.valueOf(idPersona));
/*  367 */         return per;
/*      */       } 
/*  369 */     } catch (Exception e) {
/*  370 */       e.printStackTrace();
/*      */     } 
/*  372 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteProgramacion(Date fechaServicio) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Optional<Persona> validarProgramacionByFecha(ServicioDto servidores, Date fechaServicio) {
/*      */     try {
/*  391 */       for (String servidor : servidores.getEncargado()) {
/*  392 */         Optional<Servicio> programacion = this.servicioRepository.findProgramacionServidor(Integer.parseInt(servidor), fechaServicio);
/*      */         
/*  394 */         if (programacion.isPresent()) {
/*  395 */           Optional<Persona> per = this.personaRepository.findById(Integer.valueOf(Integer.parseInt(servidor)));
/*  396 */           return per;
/*      */         } 
/*      */       } 
/*  399 */     } catch (Exception e) {
/*  400 */       e.printStackTrace();
/*      */     } 
/*  402 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Optional<Persona> validarActualizarProgramacionByFecha(ServicioDto servidores, Date fechaServicio, int ministerio) {
/*      */     try {
/*  409 */       for (String servidor : servidores.getEncargado()) {
/*  410 */         Optional<Servicio> programacion = this.servicioRepository.findProgramacionServidor(Integer.parseInt(servidor), fechaServicio);
/*      */         
/*  412 */         if (programacion.isPresent() && ((Servicio)programacion.get()).getIdMinisterio() != ministerio) {
/*  413 */           Optional<Persona> per = this.personaRepository.findById(Integer.valueOf(Integer.parseInt(servidor)));
/*  414 */           return per;
/*      */         } 
/*      */       } 
/*  417 */     } catch (Exception e) {
/*  418 */       e.printStackTrace();
/*      */     } 
/*  420 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validarActualizarProgramacionByFechaAndName(ServicioDto servidores, Date fechaServicio, int ministerio) {
/*      */     try {
/*  427 */       for (int i = 0; i < servidores.getEncargado().size(); i++) {
/*  428 */         Optional<Servicio> programacion = this.servicioRepository.findProgramacionServidorAndMinisterio(servidores.getEncargado().get(i), fechaServicio, servidores.getPosicion().get(i));
/*      */         
/*  430 */         if (programacion.isPresent()) {
/*  431 */           Servicio serSave = new Servicio();
/*  432 */           serSave = programacion.get();
/*  433 */           serSave.setAsistencia(servidores.getAsistencia().get(i));
/*  434 */           serSave = (Servicio)this.servicioRepository.save(serSave);
/*      */         }
/*      */       
/*      */       } 
/*  438 */     } catch (Exception e) {
/*  439 */       e.printStackTrace();
/*  440 */       return false;
/*      */     } 
/*      */     
/*  443 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ServicioListResponseDto> findProgramacionByDate(Date fechaActual) {
/*  449 */     List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
/*  450 */     List<Object> obj = new ArrayList();
/*  451 */     List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  452 */     obj = this.servicioRepository.findProgramacionServicio(fechaActual);
/*  453 */     if (!obj.isEmpty()) {
/*  454 */       obj.forEach(O -> {
/*      */ 
/*      */             
/*      */             try {
/*      */               ListServicioDto.add(mapObjectToDto(O));
/*  459 */             } catch (ParseException e) {
/*      */               throw new RuntimeException(e);
/*      */             } 
/*      */           });
/*      */     }
/*      */ 
/*      */     
/*  466 */     ListServiceDto = buscarMinistarios(ListServicioDto);
/*      */ 
/*      */ 
/*      */     
/*  470 */     return ListServiceDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ServicioResponseDto> findProgramacionByDateAndMinisterio(Date fechaActual, int idMinisterio) {
/*  475 */     List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
/*  476 */     List<Object> obj = new ArrayList();
/*  477 */     List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  478 */     obj = this.servicioRepository.findProgramacionByDateAndidMinistery(fechaActual, idMinisterio);
/*  479 */     int i = 0; if (i < 6)
/*      */     {
/*  481 */       obj.forEach(O -> {
/*      */ 
/*      */             
/*      */             try {
/*      */               ListServicioDto.add(mapObjectToDto(O));
/*  486 */             } catch (ParseException e) {
/*      */               throw new RuntimeException(e);
/*      */             } 
/*      */           });
/*      */     }
/*      */     
/*  492 */     ListServicioDto.size();
/*      */     
/*  494 */     return ListServicioDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ServicioListResponseDto> findProgramacionByDateGroup(Date fechaActual) throws ParseException {
/*  499 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  500 */     List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
/*  501 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/*  502 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/*  503 */     String fechaActualStr = nowInBogota.format(formatter);
/*      */ 
/*      */     
/*      */     try {
/*  507 */       Date fechaActual1 = sdf.parse(fechaActualStr);
/*  508 */       LocalDate fechaActualizada = nowInBogota.toLocalDate();
/*      */ 
/*      */       
/*  511 */       List<Object> obj = new ArrayList();
/*  512 */       List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  513 */       for (int i = 0; i < 6; i++) {
/*  514 */         obj = this.servicioRepository.findProgramacionServicio(fechaActual);
/*  515 */         if (obj.isEmpty()) {
/*  516 */           fechaActualizada = fechaActualizada.plusDays(1L);
/*  517 */           fechaActual = Date.valueOf(fechaActualizada);
/*      */         } else {
/*  519 */           obj.forEach(O -> {
/*      */ 
/*      */                 
/*      */                 try {
/*      */                   ListServicioDto.add(mapObjectToDto(O));
/*  524 */                 } catch (ParseException e) {
/*      */                   this.LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
/*      */                   
/*      */                   throw new RuntimeException("[findProgramacionByDateGroup]" + e);
/*      */                 } 
/*      */               });
/*      */           break;
/*      */         } 
/*      */       } 
/*  533 */       ListServiceDto = buscarMinistarios(ListServicioDto);
/*  534 */     } catch (Exception e) {
/*  535 */       this.LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
/*  536 */       e.printStackTrace();
/*  537 */       throw new RuntimeException("[findProgramacionByDateGroup]" + e);
/*      */     } 
/*      */ 
/*      */     
/*  541 */     return ListServiceDto;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ItemCombo> findItemsCombo() {
/*  546 */     return this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.ASISTENCIA.toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void agregarPersonaAMinisterio(int idPersona, int idMinisterio) {
/*      */     try {
/*  552 */       PersonaMinisterio personaSave = new PersonaMinisterio();
/*  553 */       personaSave.setIdMinisterio(idMinisterio);
/*  554 */       personaSave.setIdPersona(idPersona);
/*  555 */       this.personaMinisterioRepoSitory.save(personaSave);
/*      */     }
/*  557 */     catch (Exception e) {
/*  558 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void agregarPersonasAMinisterio(List<Long> listaPersonas, int idMinisterio) {
/*      */     try {
/*  565 */       listaPersonas.forEach(p -> {
/*      */             PersonaMinisterio personaSave = new PersonaMinisterio();
/*      */             
/*      */             personaSave.setIdMinisterio(idMinisterio);
/*      */             
/*      */             personaSave.setIdPersona(p.intValue());
/*      */             this.personaMinisterioRepoSitory.save(personaSave);
/*      */           });
/*  573 */     } catch (Exception e) {
/*  574 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ServicioResponseDto> buscarProgramacionMes(int idPersona) throws ParseException {
/*  580 */     List<ServicioResponseDto> listaRespuestas = new ArrayList<>();
/*  581 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  582 */     String fechaActual = sdf.format(new Date());
/*  583 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*  584 */     LocalDate ld = LocalDate.parse(fechaActual, dtf);
/*  585 */     int mes = 0;
/*      */     try {
/*  587 */       for (int i = 0; i < 2; i++) {
/*      */         
/*  589 */         if (i > 0) {
/*  590 */           ld = ld.plusMonths(1L);
/*      */         }
/*  592 */         int monthDays = ld.lengthOfMonth();
/*  593 */         int yearDays = ld.lengthOfYear();
/*  594 */         int year = ld.getYear();
/*  595 */         int month = ld.getMonthValue();
/*      */         
/*  597 */         System.out.printf("Mes % 4d de %d tiene %d días%nAño %d tiene %d días", new Object[] {
/*  598 */               Integer.valueOf(month), Integer.valueOf(year), Integer.valueOf(monthDays), 
/*  599 */               Integer.valueOf(year), Integer.valueOf(yearDays)
/*      */             });
/*  601 */         String fechainicial = "" + year + "-" + year + "-1";
/*  602 */         String fechaFinal = "" + year + "-" + year + "-" + month;
/*      */         
/*  604 */         Date date1 = sdf.parse(fechainicial);
/*  605 */         Date date2 = sdf.parse(fechaFinal);
/*      */         
/*  607 */         if (month > mes) {
/*  608 */           List<Servicio> servicio = this.servicioRepository.BuscarServicioMes(date1, date2, idPersona);
/*  609 */           if (!servicio.isEmpty()) {
/*  610 */             servicio.forEach(s -> listaRespuestas.add(buildServicioResponseDto(s)));
/*      */           }
/*  612 */           List<Coordinador> listaCoordinador = this.coordinadorRepo.buscarServicioCoordinadorMes(date1, date2, idPersona);
/*  613 */           if (!listaCoordinador.isEmpty()) {
/*  614 */             listaCoordinador.forEach(c -> listaRespuestas.add(buildServicioCoordinadorResponseDto(c)));
/*      */           }
/*  616 */           mes = month;
/*      */         } 
/*      */         
/*  619 */         if (!listaRespuestas.isEmpty()) {
/*  620 */           listaRespuestas.sort(Comparator.comparing(ServicioResponseDto::getFechaServcio));
/*      */         }
/*      */       } 
/*  623 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  626 */     return listaRespuestas;
/*      */   }
/*      */ 
/*      */   
/*      */   private ServicioResponseDto buildServicioResponseDto(Servicio servicio) {
/*  631 */     ServicioResponseDto serv = new ServicioResponseDto();
/*  632 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  633 */     serv.setFechaServcio(sdf.format(servicio.getFechaServicio()));
/*  634 */     serv.setEncargado(((Persona)this.personaRepository.findById(Integer.valueOf(servicio.getIdPersona())).get()).getNombre());
/*  635 */     serv.setPosicion(((PosicionesMinisterio)this.posicionesRepository.findById(Integer.valueOf(servicio.getIdPosicion())).get()).getNombrePosicion());
/*  636 */     Optional<Ministerio> ministerio = this.ministerioRepository.findById(Integer.valueOf(servicio.getIdMinisterio()));
/*  637 */     serv.setNombreMinisterio(((Ministerio)ministerio.get()).getNombre());
/*  638 */     serv.setIdMinisterio(Integer.valueOf(((Ministerio)ministerio.get()).getId()));
/*  639 */     return serv;
/*      */   }
/*      */   
/*      */   private ServicioResponseDto buildServicioCoordinadorResponseDto(Coordinador coordinador) {
/*  643 */     ServicioResponseDto serv = new ServicioResponseDto();
/*  644 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  645 */     serv.setFechaServcio(sdf.format(coordinador.getFechaServicio()));
/*  646 */     serv.setEncargado(coordinador.getPersona().getNombre());
/*  647 */     serv.setPosicion("Coordinador");
/*  648 */     serv.setNombreMinisterio("Coordinador");
/*  649 */     return serv;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void savePosicion(PosicionDto posicionDto) {
/*  655 */     PosicionesMinisterio posicion = new PosicionesMinisterio();
/*  656 */     posicion.setNombrePosicion(posicionDto.getNombrePosicion());
/*  657 */     posicion.setIdMinisterio(posicionDto.getIdMinisterio());
/*  658 */     this.posicionesRepository.save(posicion);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void editPosicion(PosicionDto posicionDto) {
/*  664 */     PosicionesMinisterio posicion = new PosicionesMinisterio();
/*  665 */     posicion.setNombrePosicion(posicionDto.getNombrePosicion());
/*  666 */     posicion.setIdMinisterio(posicionDto.getIdMinisterio());
/*  667 */     posicion.setId(posicionDto.getId());
/*  668 */     this.posicionesRepository.save(posicion);
/*      */   }
/*      */ 
/*      */   
/*      */   public PosicionDto findPosicion(int idposicion) {
/*  673 */     PosicionDto response = new PosicionDto();
/*  674 */     PosicionesMinisterio posicion = this.posicionesRepository.findById(Integer.valueOf(idposicion)).get();
/*  675 */     response.setIdMinisterio(posicion.getIdMinisterio());
/*  676 */     response.setNombrePosicion(posicion.getNombrePosicion());
/*  677 */     response.setId(posicion.getId());
/*  678 */     return response;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean validarDuplicados(ServicioDto servidores) {
/*      */     try {
/*  686 */       for (int i = 0; i < servidores.getEncargado().size(); i++) {
/*  687 */         if (((String)servidores.getEncargado().get(i)).equals(Integer.valueOf(89898989))) {
/*  688 */           System.out.println(servidores.getEncargado().get(i));
/*      */         }
/*      */       } 
/*      */       
/*  692 */       Set<String> set = new HashSet<>(servidores.getEncargado());
/*      */       
/*  694 */       if (set.size() < servidores.getEncargado().size()) {
/*  695 */         return Boolean.valueOf(false);
/*      */       }
/*  697 */       return Boolean.valueOf(true);
/*      */     
/*      */     }
/*  700 */     catch (Exception e) {
/*  701 */       e.printStackTrace();
/*      */       
/*  703 */       return Boolean.valueOf(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Persona identificarDuplicados(ServicioDto servidores) {
/*  708 */     Persona[] pDuplicada = { new Persona() };
/*  709 */     int cont = 0;
/*  710 */     for (String e : servidores.getEncargado()) {
/*  711 */       System.out.println("inicia ------> " + e);
/*  712 */       cont = 0;
/*  713 */       for (String enc : servidores.getEncargado()) {
/*  714 */         if (enc.equals(e)) {
/*      */           try {
/*  716 */             cont++;
/*      */           }
/*  718 */           catch (Exception ex) {
/*  719 */             ex.printStackTrace();
/*      */           } 
/*      */         }
/*  722 */         System.out.println("interno enc " + enc + " " + cont);
/*  723 */         if (cont > 1) {
/*  724 */           return this.personaRepository.findById(Integer.valueOf(Integer.parseInt(e))).get();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  729 */     return pDuplicada[0];
/*      */   }
/*      */   
/*      */   public List<MinisterioDto> poblarPosiciones(List<MinisterioDto> ministerios, ServicioDto servicioDto) {
/*  733 */     List<MinisterioDto> ministeriosFinal = new ArrayList<>();
/*  734 */     ministerios.forEach(m -> {
/*      */           m.getPosicionDto().getNombrePosicion();
/*      */           
/*      */           for (int i = 0; i < servicioDto.getPosicion().size(); i++) {
/*      */             if (m.getPosicionDto().getNombrePosicion().equals(servicioDto.getPosicion().get(i))) {
/*      */               PersonaDto perMinisterio = new PersonaDto();
/*      */               
/*      */               perMinisterio.setId(Integer.parseInt(servicioDto.getEncargado().get(i)));
/*      */               
/*      */               m.getPosicionDto().setPersonaDto(perMinisterio);
/*      */             } else if (!m.getPosicionDto().getNombrePosicion().equals(servicioDto.getPosicion().get(i)) && m.getPosicionDto().getPersonaDto() == null) {
/*      */               PersonaDto perMinisterio = new PersonaDto();
/*      */               
/*      */               perMinisterio.setId(0);
/*      */               m.getPosicionDto().setPersonaDto(perMinisterio);
/*      */             } 
/*      */           } 
/*      */         });
/*  752 */     ministeriosFinal = ministerios;
/*      */     
/*  754 */     return ministeriosFinal;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MinisterioDto> getPositionInitial(List<MinisterioDto> Ministerio) {
/*  759 */     PersonaDto perPos = new PersonaDto();
/*  760 */     perPos.setId(0);
/*  761 */     Ministerio.forEach(o -> o.getPosicionDto().setPersonaDto(perPos));
/*  762 */     return Ministerio;
/*      */   }
/*      */ 
/*      */   
/*      */   public Persona getPersonDuplicate(ServicioDto servidores) {
/*  767 */     Integer contador = Integer.valueOf(0);
/*      */     
/*  769 */     Iterator<String> iterator = servidores.getEncargado().iterator(); if (iterator.hasNext()) { String o = iterator.next();
/*  770 */       Optional<Persona> p = validarRepetido(o, servidores.getEncargado());
/*  771 */       return p.get(); }
/*      */     
/*  773 */     return new Persona();
/*      */   }
/*      */ 
/*      */   
/*      */   public ProgramationDto findNextServices(Date fechaActual) throws ParseException {
/*  778 */     ProgramationDto prog = new ProgramationDto();
/*  779 */     List<MinistryDto> ministries = new ArrayList<>();
/*      */     
/*  781 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  782 */     List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
/*  783 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/*  784 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/*  785 */     String fechaActualStr = nowInBogota.format(formatter);
/*      */     
/*      */     try {
/*  788 */       Date fechaActual1 = sdf.parse(fechaActualStr);
/*  789 */       LocalDate fechaActualizada = nowInBogota.toLocalDate();
/*      */       
/*  791 */       List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  792 */       for (int i = 0; i < 6; i++) {
/*  793 */         Optional<List<Integer>> listMinisterios = this.servicioRepository.findDistinctIdMinisterio(fechaActual);
/*  794 */         if (!listMinisterios.isPresent() || ((List)listMinisterios.get()).isEmpty()) {
/*  795 */           fechaActualizada = fechaActualizada.plusDays(1L);
/*  796 */           fechaActual = Date.valueOf(fechaActualizada);
/*      */         } else {
/*  798 */           Date finalFechaActual = fechaActual;
/*  799 */           ((List)listMinisterios.get()).forEach(min -> {
/*      */                 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*      */                 
/*      */                 String fechaReactActualStr = null;
/*      */                 try {
/*      */                   fechaReactActualStr = this.utilDate.convertDateToStringWithFormat(finalFechaActual, "yyyy-MM-dd");
/*  805 */                 } catch (ParseException e) {
/*      */                   throw new RuntimeException(e);
/*      */                 } 
/*      */                 
/*      */                 LocalDate ld = LocalDate.parse(fechaReactActualStr, dtf);
/*      */                 
/*      */                 prog.setDate(ld);
/*      */                 
/*      */                 Coordinador cord = this.coordinadorRepo.findByFechaServicio(finalFechaActual);
/*      */                 
/*      */                 if (cord != null) {
/*      */                   CordinatorDto corDto = new CordinatorDto();
/*      */                   
/*      */                   corDto.setName(cord.getPersona().getNombre());
/*      */                   corDto.setDate(ld);
/*      */                   corDto.setId(cord.getPersona().getId().intValue());
/*      */                   prog.setCoordinator(corDto);
/*      */                 } 
/*      */                 List<MinistryMember> menbers = new ArrayList<>();
/*      */                 List<Object> resp = this.servicioRepository.findMInisteriesAndpositions(finalFechaActual, min.intValue());
/*      */                 if (resp != null && resp.size() > 0) {
/*      */                   MinistryDto mDto = new MinistryDto();
/*      */                  // resp.forEach(());
/*      */                   Map<String, List<MinistryMember>> hashMin = new HashMap<>();
/*      */                   mDto.setName(((MinistryMember)menbers.get(0)).getMinistryName());
/*      */                   mDto.setId(min);
/*      */                   mDto.setPositions(menbers);
/*      */                   ministries.add(mDto);
/*      */                 } 
/*      */               });
/*      */           break;
/*      */         } 
/*      */       } 
/*  838 */       ListServiceDto = buscarMinistarios(ListServicioDto);
/*  839 */     } catch (Exception e) {
/*  840 */       this.LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
/*  841 */       e.printStackTrace();
/*  842 */       throw new RuntimeException("[findProgramacionByDateGroup]" + e);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  847 */     prog.setMinistries(ministries);
/*  848 */     return prog;
/*      */   }
/*      */ 
/*      */   
/*      */   public ProgramationDto findServices(Date fechaActual) throws ParseException {
/*  853 */     ProgramationDto prog = new ProgramationDto();
/*  854 */     List<MinistryDto> ministries = new ArrayList<>();
/*      */ 
/*      */     
/*      */     try {
/*  858 */       List<ServicioResponseDto> ListServicioDto = new ArrayList<>();
/*  859 */       int i = 0; if (i < 6) {
/*  860 */         Optional<List<Integer>> listMinisterios = this.servicioRepository.findDistinctIdMinisterio(fechaActual);
/*  861 */         if (!listMinisterios.isPresent() || ((List)listMinisterios.get()).isEmpty()) {
/*  862 */           return null;
/*      */         }
/*  864 */         Date finalFechaActual = fechaActual;
/*  865 */         ((List)listMinisterios.get()).forEach(min -> {
/*      */               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*      */               
/*      */               String fechaReactActualStr = null;
/*      */               try {
/*      */                 fechaReactActualStr = this.utilDate.convertDateToStringWithFormat(finalFechaActual, "yyyy-MM-dd");
/*  871 */               } catch (ParseException e) {
/*      */                 throw new RuntimeException(e);
/*      */               } 
/*      */               
/*      */               LocalDate ld = LocalDate.parse(fechaReactActualStr, dtf);
/*      */               
/*      */               prog.setDate(ld);
/*      */               
/*      */               Coordinador cord = this.coordinadorRepo.findByFechaServicio(finalFechaActual);
/*      */               
/*      */               if (cord != null) {
/*      */                 CordinatorDto corDto = new CordinatorDto();
/*      */                 corDto.setName(cord.getPersona().getNombre());
/*      */                 corDto.setDate(ld);
/*      */                 prog.setCoordinator(corDto);
/*      */               } 
/*      */               List<MinistryMember> menbers = new ArrayList<>();
/*      */               List<Object> resp = this.servicioRepository.findMInisteriesAndpositions(finalFechaActual, min.intValue());
/*      */               if (resp != null && resp.size() > 0) {
/*      */                 MinistryDto mDto = new MinistryDto();
/*      */               //  resp.forEach(());
/*      */                 Map<String, List<MinistryMember>> hashMin = new HashMap<>();
/*      */                 mDto.setName(((MinistryMember)menbers.get(0)).getMinistryName());
/*      */                 mDto.setId(min);
/*      */                 mDto.setPositions(menbers);
/*      */                 ministries.add(mDto);
/*      */               } 
/*      */             });
/*      */       } 
/*  900 */     } catch (Exception e) {
/*  901 */       this.LOGGER.error("[findProgramacionByDateGroup] " + e.getMessage());
/*  902 */       e.printStackTrace();
/*  903 */       throw new RuntimeException("[findProgramacionByDateGroup]" + e);
/*      */     } 
/*  905 */     prog.setMinistries(ministries);
/*  906 */     return prog;
/*      */   }
/*      */   
/*      */   private MinistryMember mapPositionsDto(Object obj) {
/*  910 */     MinistryMember member = new MinistryMember();
/*  911 */     Object[] object = (Object[])obj;
/*  912 */     member.setMinistryName(object[1].toString());
/*  913 */     member.setPositionId(((Integer)object[2]).intValue());
/*  914 */     member.setPosition(object[3].toString());
/*  915 */     member.setPersonName(object[4].toString());
/*  916 */     member.setPersonId(((Integer)object[5]).intValue());
/*  917 */     return member;
/*      */   }
/*      */ 
/*      */   
/*      */   private Optional<Persona> validarRepetido(String idUser, List<String> listUsers) {
/*  922 */     int c = 0;
/*  923 */     for (int j = 0; j < listUsers.size(); j++) {
/*  924 */       if (idUser.equalsIgnoreCase(listUsers.get(j))) {
/*  925 */         c++;
/*  926 */         if (c > 1) {
/*  927 */           return this.personaRepository.findById(Integer.valueOf(Integer.parseInt(idUser)));
/*      */         }
/*      */       } 
/*      */     } 
/*  931 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */   
/*      */   private MinisterioDto mapEntityToDto(PosicionesMinisterio Posicion, String nombreMinisterio) {
/*  936 */     MinisterioDto dto = new MinisterioDto();
/*  937 */     dto.setPosicionDto(new PosicionDto());
/*  938 */     dto.setNombreMinisterio(nombreMinisterio);
/*  939 */     dto.getPosicionDto().setIdMinisterio(Posicion.getIdMinisterio());
/*  940 */     dto.getPosicionDto().setNombrePosicion(Posicion.getNombrePosicion());
/*  941 */     dto.getPosicionDto().setId(Posicion.getId());
/*  942 */     return dto;
/*      */   }
/*      */   
/*      */   private MinisterioDto mapEntityToDtoAndPerson(PosicionesMinisterio Posicion, String nombreMinisterio, List<Object> obj) {
/*  946 */     MinisterioDto dto = new MinisterioDto();
/*  947 */     dto.setPosicionDto(new PosicionDto());
/*  948 */     dto.setNombreMinisterio(nombreMinisterio);
/*  949 */     dto.getPosicionDto().setIdMinisterio(Posicion.getIdMinisterio());
/*  950 */     dto.getPosicionDto().setNombrePosicion(Posicion.getNombrePosicion());
/*  951 */     dto.getPosicionDto().setId(Posicion.getId());
/*  952 */     obj.forEach(o -> {
/*      */           Object[] object = (Object[])o;
/*      */           Integer idPosicion = Integer.valueOf(Posicion.getId());
/*      */           if (idPosicion.equals(Integer.valueOf(Integer.parseInt(object[2].toString())))) {
/*      */             PersonaDto perMinisterio = new PersonaDto();
/*      */             perMinisterio.setId(Integer.parseInt(object[0].toString()));
/*      */             perMinisterio.setNombre(object[1].toString());
/*      */             dto.getPosicionDto().setPersonaDto(perMinisterio);
/*      */           } 
/*      */         });
/*  962 */     if (dto.getPosicionDto().getPersonaDto() == null) {
/*  963 */       PersonaDto personaDto = new PersonaDto();
/*  964 */       personaDto.setId(0);
/*  965 */       dto.getPosicionDto().setPersonaDto(personaDto);
/*      */     } 
/*  967 */     return dto;
/*      */   }
/*      */   
/*      */   private PersonaDto mapPersonaToDto(Persona per) {
/*  971 */     PersonaDto personadto = new PersonaDto();
/*  972 */     personadto.setNombre(per.getNombre());
/*  973 */     personadto.setApellido(per.getApellido());
/*  974 */     personadto.setDocumento(per.getDocumento());
/*  975 */     personadto.setTipodocumento(per.getTipodocumento());
/*  976 */     personadto.setEmail(per.getEmail());
/*  977 */     personadto.setId(per.getId().intValue());
/*  978 */     personadto.setFechanacimiento(per.getFechanacimiento());
/*  979 */     personadto.setTelefono(per.getTelefono());
/*  980 */     personadto.setPassword(per.getPassword());
/*  981 */     return personadto;
/*      */   }
/*      */   
/*      */   private ServicioResponseDto mapObjectToDto(Object obj) throws ParseException {
/*  985 */     ServicioResponseDto servicioDto = new ServicioResponseDto("bateriavoz principal", "gaby ", "2023-12-04", "ministerio", Integer.valueOf(1));
/*  986 */     Object[] object = (Object[])obj;
/*      */     
/*  988 */     String date_s = object[0].toString();
/*  989 */     SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
/*  990 */     Date date1 = dt.parse(date_s);
/*  991 */     SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/*  992 */     System.out.println(dt1.format(date1));
/*  993 */     servicioDto.setFechaServcio(dt1.format(date1));
/*  994 */     servicioDto.setEncargado(object[1].toString());
/*  995 */     servicioDto.setPosicion(object[2].toString());
/*  996 */     servicioDto.setIdMinisterio(Integer.valueOf(Integer.parseInt(object[3].toString())));
/*  997 */     servicioDto.setNombreMinisterio(object[4].toString());
/*  998 */     servicioDto.setIdNotificacionTelegram((object[5] != null) ? object[5].toString() : null);
/*  999 */     servicioDto.setCelular((object[6] != null) ? object[6].toString() : null);
/* 1000 */     servicioDto.setAsistenciaList(this.mapperParametros.listEntitytoListDto(this.parametrosRepo.findByGrupo(ECombos.ASISTENCIA.toString())));
/* 1001 */     return servicioDto;
/*      */   }
/*      */   
/*      */   private List<ServicioListResponseDto> buscarMinistarios(List<ServicioResponseDto> ListServicioDto) {
/* 1005 */     List<ServicioListResponseDto> ListServiceDto = new ArrayList<>();
/* 1006 */     List<Ministerio> ministerioslist = this.ministerioRepository.findAll();
/* 1007 */     if (!ListServicioDto.isEmpty()) {
/* 1008 */       for (Ministerio m : ministerioslist) {
/* 1009 */         ServicioListResponseDto dtoResponse = new ServicioListResponseDto();
/* 1010 */         dtoResponse.setFechaServcio(((ServicioResponseDto)ListServicioDto.get(0)).getFechaServcio());
/* 1011 */         dtoResponse.setIdMinisterio(Integer.valueOf(m.getId()));
/* 1012 */         dtoResponse.setNombreMinisterio(m.getNombre());
/* 1013 */         dtoResponse.setServicioDTO((List)ListServicioDto
/* 1014 */             .stream().filter(p -> (p.getIdMinisterio().intValue() == m.getId()))
/* 1015 */             .collect(Collectors.toList()));
/* 1016 */         dtoResponse.setTamanoLista(Integer.valueOf(dtoResponse.getServicioDTO().size() + 1));
/* 1017 */         ListServiceDto.add(dtoResponse);
/*      */       } 
/*      */     }
/* 1020 */     return ListServiceDto;
/*      */   }
/*      */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\ServicioServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */