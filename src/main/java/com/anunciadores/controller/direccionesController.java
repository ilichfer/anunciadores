  package com.anunciadores.controller;
  
  import com.anunciadores.dto.ListasCombos;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.dto.ServicioListResponseDto;
  import com.anunciadores.dto.TdcDto;
  import com.anunciadores.dto.VersiculoSaveDto;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.Ministerio;
  import com.anunciadores.model.Persona;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.service.interfaces.ICombos;
  import com.anunciadores.service.interfaces.ICursoService;
  import com.anunciadores.service.interfaces.IPagoService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.anunciadores.service.interfaces.IServicioService;
  import com.anunciadores.service.interfaces.ITdcService;
  import com.anunciadores.util.UtilDate;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import java.sql.Date;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.time.ZoneId;
  import java.time.ZonedDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.List;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  
  
  
  
  
  @Controller
  @RequestMapping
  public class direccionesController
  {
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IBibliaService bibliaService;
    @Autowired
    private IPagoService pagoService;
    @Autowired
    private ICursoService cursoService;
    @Autowired
    private ITdcService tdcService;
    @Autowired
    private IServicioService servicioService;
    @Autowired
    private ICombos combosService;
    @Autowired
    private UtilDate utilDate;
    List<Persona> personasList;
    
    @GetMapping({"/redirectCurso"})
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/*  70 */     this.personasList = this.personaService.findAllUsuarios();
/*  71 */     model.addAttribute("personas", this.personasList);
/*  72 */     return "register-curso";
    }
  
    
    @GetMapping({"/redirectCoordinador"})
    public String redirectCoordinador(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/*  78 */     this.personasList = this.personaService.findAllUsuarios();
/*  79 */     model.addAttribute("personas", this.personasList);
/*  80 */     model.addAttribute("msj", null);
/*  81 */     return "register-coordinador";
    }
  
  
    
    @GetMapping({"/redirectPersona"})
    public String redirectPersona(@RequestParam(value = "action", required = false) String action, Model model) {
/*  88 */     model.addAttribute("name", action);
/*  89 */     return "registrarPersona";
    }
    
    @GetMapping({"/redirectPersonaOut"})
    public String redirectPersonaOut(@RequestParam(value = "action", required = false) String action, Model model) {
/*  94 */     Persona persona = new Persona();
/*  95 */     ListasCombos listas = this.combosService.listarParametros();
/*  96 */     model.addAttribute("name", action);
/*  97 */     model.addAttribute("persona", persona);
/*  98 */     model.addAttribute("listaGenero", listas.getListaGenero());
/*  99 */     model.addAttribute("listaEstadoCivil", listas.getEstadoCivil());
/* 100 */     model.addAttribute("listaEscolaridad", listas.getListaEscolaridad());
/* 101 */     model.addAttribute("listaDocumento", listas.getListaDocuemntos());
/* 102 */     return "registerOut";
    }
    
    @GetMapping({"/redirectDashboard"})
    public String login(@ModelAttribute Persona persona, HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException, ParseException {
/* 107 */     VersiculoSaveDto dia = this.bibliaService.buscarVersiculoSemanal();
/* 108 */     List<ServicioListResponseDto> listProgramacionMinisterio = this.servicioService.findProgramacionByDateGroup(this.utilDate.cargarfechaActualBogotaDate());
/* 109 */     model.addAttribute("dia", dia);
/* 110 */     List<PersonaDto> listadoCumpleañosMes = this.personaService.findBirthdayByMonth();
/* 111 */     List<PersonaDto> listadoCumpleañosDiario = this.personaService.getBirthDay(listadoCumpleañosMes);
/* 112 */     model.addAttribute("cumpleanos", listadoCumpleañosMes);
/* 113 */     if (!listadoCumpleañosDiario.isEmpty()) {
/* 114 */       model.addAttribute("cumpleanosDiario", listadoCumpleañosDiario);
      } else {
/* 116 */       model.addAttribute("cumpleanosDiario", null);
      } 
/* 118 */     if (listProgramacionMinisterio.size() > 0) {
/* 119 */       Coordinador cor = this.servicioService.findCoordinador(listProgramacionMinisterio);
/* 120 */       SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
/* 121 */       model.addAttribute("programacionMin", listProgramacionMinisterio);
/* 122 */       model.addAttribute("coordinador", cor);
/* 123 */       model.addAttribute("fechaCoordinador", (cor != null) ? dt1.format(cor.getFechaServicio()) : null);
      } else {
        
/* 126 */       model.addAttribute("programacionMin", null);
      } 
/* 128 */     String url = "index";
/* 129 */     return url;
    }
    
    @GetMapping({"/redirectActividad"})
    public String redirectActividad(HttpServletResponse response, Model model) throws JsonMappingException, JsonProcessingException {
/* 134 */     String url = "register-actividad";
/* 135 */     return url;
    }
  
    
    @GetMapping({"/redirectAsistente"})
    public String redirectAsistente(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 141 */     return "registerAsistente";
    }
  
    
    @GetMapping({"/redirectAsistenteConsolidacion"})
    public String redirectAsistenteConsolidacion(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 147 */     return "registerAsistenteConsolidacion";
    }
  
    
    @GetMapping({"/redirectUsuario"})
    public String redirectUsuario(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 153 */     return "register";
    }
  
    
    @GetMapping({"/redirectLogin"})
    public String redirectLogin(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model, HttpServletRequest request, HttpServletResponse response) {
/* 159 */     HttpSession session = request.getSession();
/* 160 */     session.invalidate();
/* 161 */     return "login";
    }
    
    @GetMapping({"/redirectSeguimiento"})
    public String redirectSeguimiento(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 166 */     return "seguimientoPadres";
    }
    @PostMapping({"/perfil"})
    public String redirectTDC2(@ModelAttribute Persona persona, Model model) {
/* 170 */     Persona per = this.personaService.findPersonaById(persona.getId());
/* 171 */     model.addAttribute("persona", per);
/* 172 */     return "perfil";
    }
    @GetMapping({"/editarPerfil"})
    public String editarPerfil(@RequestParam int idPersona, Model model) {
/* 176 */     Persona per = this.personaService.findPersonaById(Integer.valueOf(idPersona));
/* 177 */     ListasCombos listas = this.combosService.listarParametros();
/* 178 */     model.addAttribute("persona", per);
/* 179 */     model.addAttribute("listaGenero", listas.getListaGenero());
/* 180 */     model.addAttribute("listaEstadoCivil", listas.getEstadoCivil());
/* 181 */     model.addAttribute("listaEscolaridad", listas.getListaEscolaridad());
/* 182 */     model.addAttribute("listaDocumento", listas.getListaDocuemntos());
/* 183 */     return "editPerfil";
    }
  
    
    @GetMapping({"/redirectPago"})
    public String redirectPago(@RequestParam int idCurso, @RequestParam int idPersona, Model model) {
/* 189 */     Curso curso = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/* 190 */     Persona persona = this.personaService.findPersonaById(Integer.valueOf(idPersona));
/* 191 */     model.addAttribute("persona", persona);
/* 192 */     model.addAttribute("curso", curso);
/* 193 */     return "registrarPago";
    }
  
    
    @GetMapping({"/recuperarPass"})
    public String recuperarPass(@RequestParam(value = "action", required = false) String action, Model model) {
/* 199 */     return "recoverPass";
    }
    
    @PostMapping({"/redirectTDC"})
    public String redirectTDC2(@RequestParam int idPersonaTCD, Model model) {
/* 204 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/* 205 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/* 206 */     String fechaformat = nowInBogota.format(formatter);
      
/* 208 */     model.addAttribute("fecha", fechaformat);
/* 209 */     model.addAttribute("idPersona", Integer.valueOf(idPersonaTCD));
/* 210 */     return "registerTDC";
    }
  
    
    @GetMapping({"/redirectReporteTDC"})
    public String redirectReporteTDC(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 216 */     return "reporteTdc";
    }
  
    
    @GetMapping({"/redirectReporteTdcPersona"})
    public String reporteTdcPersona(@RequestParam int idPersona, @RequestParam Date fechaInicio, @RequestParam Date fechaFin, Model model) {
/* 222 */     List<TdcDto> listTCD = this.tdcService.findAllBetweenDatesByPersona(fechaInicio, fechaFin, idPersona);
/* 223 */     Persona per = this.personaService.findPersonaById(Integer.valueOf(idPersona));
/* 224 */     model.addAttribute("fechaInicio", fechaInicio);
/* 225 */     model.addAttribute("fechaFin", fechaFin);
/* 226 */     model.addAttribute("idPersona", Integer.valueOf(idPersona));
/* 227 */     model.addAttribute("persona", per);
/* 228 */     model.addAttribute("listaTdc", listTCD);
/* 229 */     return "reporteTdcPersona";
    }
    @GetMapping({"/listarMinisterios"})
    public String listarMinisterios(Model model) {
/* 233 */     List<Ministerio> ministerios = this.servicioService.getAll();
/* 234 */     model.addAttribute("ministerios", ministerios);
/* 235 */     return "ministerios";
    }
    @GetMapping({"/gestionarMinisterios"})
    public String gestionarMinisterios(Model model) {
/* 239 */     List<Ministerio> ministerios = this.servicioService.getAll();
/* 240 */     model.addAttribute("ministerios", ministerios);
/* 241 */     return "listarMinisterios";
    }
    
    @GetMapping({"/redirectMinisterio"})
    public String redirectMinisterio(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 246 */     return "register-ministerio";
    }
    
    @GetMapping({"/loginInicio"})
    public String defectDetails() {
/* 251 */     return "ingreso";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\direccionesController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */