  package com.anunciadores.controller;
  
  import com.anunciadores.dto.CoordinadorDTO;
  import com.anunciadores.dto.MinisterioDto;
  import com.anunciadores.dto.ServicioListResponseDto;
  import com.anunciadores.dto.ServicioResponseDto;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Persona;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.anunciadores.service.interfaces.IServicioService;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import com.fasterxml.jackson.databind.ObjectMapper;
  import com.fasterxml.jackson.databind.ObjectWriter;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.time.LocalDate;
  import java.time.ZoneId;
  import java.time.ZonedDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.Date;
  import java.util.List;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.CrossOrigin;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
  @Controller
  @RequestMapping
  public class ServicioController
  {
/*  42 */   private Logger LOGGER = LoggerFactory.getLogger(com.anunciadores.controller.ServicioController.class);
    
    @Autowired
    private IServicioService servicioService;
    
    @Autowired
    private IPersonaService personaService;
    
    private Date cargarfechaActualBogota() throws ParseException {
/*  51 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
/*  52 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/*  53 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/*  54 */     String fechaActualStr = nowInBogota.format(formatter);
/*  55 */     Date actualDate = sdf.parse(fechaActualStr);
/*  56 */     return actualDate;
    }
    
    @GetMapping({"/consultarProgramacion"})
    public String consultarProgramacion(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/*  61 */     String url = "buscarServicio";
/*  62 */     Date actualDate = cargarfechaActualBogota();
/*  63 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(actualDate);
      
/*  65 */     if (listProgramacionMinisterio.size() > 0) {
/*  66 */       Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/*  67 */       SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/*  68 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/*  69 */       model.addAttribute("coordinador", cor);
/*  70 */       model.addAttribute("fechaCoordinador", (cor != null) ? dt1.format(cor.getFechaServicio()) : null);
      } else {
/*  72 */       model.addAttribute("programacionMin", null);
      } 
/*  74 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/*  75 */     return url;
    }
  
  
    
    @GetMapping({"/editarProgramacion"})
    public String editarProgramacion(@RequestParam String fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/*  82 */     String url = "editar_programacion";
/*  83 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  84 */     Date fechaD = sdf.parse(fecha);
/*  85 */     List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterioAndPerson(fechaD, idMinisterio);
  
  
      
/*  89 */     model.addAttribute("listaPosiciones", ministerios);
/*  90 */     model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/*  91 */     model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/*  92 */     model.addAttribute("fecha", fecha);
/*  93 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/*  94 */     return url;
    }
    
    @GetMapping({"/registrarAsistencia"})
    public String registrarAsistencia(@RequestParam String fecha, @RequestParam int idMinisterio, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/*  99 */     String url = "register_asistencia";
/* 100 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 101 */     Date fechaD = sdf.parse(fecha);
/* 102 */     List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterioAndPerson(fechaD, idMinisterio);
      
/* 104 */     ministerios = this.servicioService.limpiarListaPosiciones(ministerios, fechaD, idMinisterio);
/* 105 */     model.addAttribute("listaPosiciones", ministerios);
/* 106 */     model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 107 */     model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterioAsistencia(idMinisterio));
/* 108 */     model.addAttribute("fecha", fecha);
/* 109 */     model.addAttribute("itemsCombo", this.servicioService.findItemsCombo());
/* 110 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 111 */     return url;
    }
    @PostMapping({"/saveCoordinador"})
    public String saveCoordinado(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 115 */     String url = "buscarServicio";
/* 116 */     Date actualDate = cargarfechaActualBogota();
      
/* 118 */     Coordinador corSave = this.servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
/* 119 */     if (corSave != null && corSave.getId() != 0) {
/* 120 */       List<Persona> personasList = this.personaService.findAllUsuarios();
/* 121 */       model.addAttribute("personas", personasList);
/* 122 */       model.addAttribute("msj", "Ya existe un coordinador asignado para esta fecha, si desea editar porfavor dirijase a la seccion de edicion de coordinador");
        
/* 124 */       return "register-coordinador";
      } 
/* 126 */     this.servicioService.saveCoordinado(cordinador);
/* 127 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(actualDate);
      
/* 129 */     if (listProgramacionMinisterio.size() > 0) {
/* 130 */       Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 131 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 132 */       model.addAttribute("coordinador", cor);
      } else {
/* 134 */       model.addAttribute("programacionMin", null);
      } 
/* 136 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 137 */     return url;
    }
  
  
    
    @PostMapping({"/updateCoordinador"})
    public String updateCoordinador(@ModelAttribute CoordinadorDTO cordinador, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 144 */     String url = "buscarServicio";
/* 145 */     Coordinador cor = this.servicioService.findCoordinadorByFecha(cordinador.getFechaServcio());
/* 146 */     cordinador.setId(cor.getId());
      
/* 148 */     this.servicioService.saveCoordinado(cordinador);
      
/* 150 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDate(cordinador.getFechaServcio());
      
/* 152 */     if (listProgramacionMinisterio.size() > 0) {
/* 153 */       Coordinador cord = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 154 */       SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 155 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 156 */       model.addAttribute("coordinador", cord);
/* 157 */       model.addAttribute("fechaCoordinador", (cord != null) ? dt1.format(cord.getFechaServicio()) : null);
      } else {
/* 159 */       model.addAttribute("programacionMin", null);
/* 160 */       model.addAttribute("fechaBuscada", cordinador.getFechaServcio());
      } 
/* 162 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 163 */     return url;
    }
  
    
    @GetMapping({"/editarCoordinador"})
    public String editarCoordinador(@RequestParam String fecha, @RequestParam int idPersona, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 169 */     String url = "editar-coordinador";
/* 170 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 171 */     String fechaComoCadena = fecha;
      
/* 173 */     List<Persona> personasList = this.personaService.findAllUsuarios();
/* 174 */     model.addAttribute("personas", personasList);
/* 175 */     model.addAttribute("idPersona", Integer.valueOf(idPersona));
/* 176 */     model.addAttribute("fecha", fechaComoCadena);
/* 177 */     return url;
    }
    
    @PostMapping({"/buscarProgramacionByFecha"})
    public String buscarProgramacionByFecha(@RequestParam String fecha, Model model) throws ParseException {
/* 182 */     String url = "buscarServicio";
/* 183 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 184 */     Date fechaD = sdf.parse(fecha);
/* 185 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDate(fechaD);
      
/* 187 */     if (listProgramacionMinisterio.size() > 0) {
/* 188 */       Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 189 */       SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 190 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 191 */       model.addAttribute("coordinador", cor);
/* 192 */       model.addAttribute("fechaCoordinador", (cor != null) ? dt1.format(cor.getFechaServicio()) : null);
      } else {
/* 194 */       model.addAttribute("programacionMin", null);
/* 195 */       model.addAttribute("fechaBuscada", fecha);
      } 
/* 197 */     ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 198 */     return url;
    }
    
    @PostMapping({"/miProgramacion"})
    public String miProgramacion(@ModelAttribute Persona persona, Model model) throws ParseException {
/* 203 */     String url = "servicioMes";
/* 204 */     List<ServicioResponseDto> listServ = this.servicioService.buscarProgramacionMes(persona.getId().intValue());
      
/* 206 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 207 */     String fechaActual = sdf.format(new Date());
/* 208 */     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/* 209 */     LocalDate ld = LocalDate.parse(fechaActual, dtf);
      
/* 211 */     int month = ld.getMonthValue();
/* 212 */     int mesSiguiente = month + 1;
/* 213 */     String meses = "" + month + "  y " + month;
      
/* 215 */     model.addAttribute("listMes", listServ);
/* 216 */     model.addAttribute("msj", "personasList");
/* 217 */     model.addAttribute("mesActual", meses);
/* 218 */     return url;
    }
    
    @GetMapping({"/crearInforme"})
    public String crearInforme(Model model, HttpServletRequest request) throws ParseException {
/* 223 */     String url = "asistenciaProgramacion";
/* 224 */     Date actualDate = cargarfechaActualBogota();
/* 225 */     model.addAttribute("fechaActual", actualDate);
  
      
/* 228 */     Coordinador cor = new Coordinador();
/* 229 */     Persona per = new Persona();
/* 230 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(actualDate);
  
      
/* 233 */     if (listProgramacionMinisterio.size() > 0) {
/* 234 */       cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 235 */       if (cor == null) {
/* 236 */         cor = this.servicioService.findCoordinadorAdministrator(request);
        }
/* 238 */       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 239 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 240 */       model.addAttribute("coordinador", cor);
/* 241 */       model.addAttribute("fechaCoordinador", (cor.getFechaServicio() != null) ? simpleDateFormat.format(cor.getFechaServicio()) : null);
      } else {
/* 243 */       model.addAttribute("programacionMin", null);
      } 
      
/* 246 */     SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 247 */     if (cor != null && cor.getPersona() != null) {
/* 248 */       per = this.personaService.findPersonaById(cor.getPersona().getId());
      }
      
/* 251 */     model.addAttribute("msj", "personasList");
/* 252 */     model.addAttribute("persona", (per != null) ? per : null);
/* 253 */     model.addAttribute("coordinador", cor);
/* 254 */     model.addAttribute("fechaServicio", (cor.getFechaServicio() != null) ? dt1.format(cor.getFechaServicio()) : null);
/* 255 */     return url;
    }
    
    @PostMapping({"/redirectCrearInforme"})
    public String redirectCrearInforme(@ModelAttribute Persona persona, Model model, HttpServletRequest request) throws ParseException {
/* 260 */     String url = "register-informCoordinador";
/* 261 */     Date fechaProgramacion = new Date();
/* 262 */     Date actualDate = cargarfechaActualBogota();
/* 263 */     SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
      
/* 265 */     Coordinador cor = new Coordinador();
/* 266 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(actualDate);
/* 267 */     if (listProgramacionMinisterio.size() > 0) {
/* 268 */       cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 269 */       if (cor == null) {
/* 270 */         cor = this.servicioService.findCoordinadorAdministrator(request);
        }
/* 272 */       String fechaStr = ((ServicioListResponseDto)listProgramacionMinisterio.get(0)).getFechaServcio();
/* 273 */       fechaProgramacion = dt1.parse(fechaStr);
      } 
/* 275 */     model.addAttribute("msj", "personasList");
/* 276 */     model.addAttribute("persona", cor.getPersona());
/* 277 */     model.addAttribute("coordinador", cor);
/* 278 */     model.addAttribute("fechaServicio", (cor.getFechaServicio() != null) ? dt1.format(cor.getFechaServicio()) : dt1.format(fechaProgramacion));
/* 279 */     return url;
    }
    
    @PostMapping({"/guardarInforme"})
    public String guardarInforme(@ModelAttribute Coordinador cordinador, int idPersona, String fechaServCoord, Model model) throws ParseException {
/* 284 */     String url = "redirect:/redirectDashboard";
/* 285 */     SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 286 */     Persona per = this.personaService.findPersonaById(Integer.valueOf(idPersona));
/* 287 */     cordinador.setPersona(per);
/* 288 */     cordinador.setFechaServicio(dt1.parse(fechaServCoord));
      
/* 290 */     model.addAttribute("msj", "personasList");
/* 291 */     return url;
    }
    
    @GetMapping({"/redirectHisCordindor"})
    public String redirectHisCordindor(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 296 */     return "reporteHisCordinador";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\ServicioController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */