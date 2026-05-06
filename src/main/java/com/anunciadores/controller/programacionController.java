  package com.anunciadores.controller;
  
  import com.anunciadores.dto.MinisterioDto;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.dto.PosicionDto;
  import com.anunciadores.dto.ServicioDto;
  import com.anunciadores.model.Coordinador;
  import com.anunciadores.model.Ministerio;
  import com.anunciadores.model.Persona;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.service.interfaces.IPagoService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.anunciadores.service.interfaces.IServicioService;
  import com.anunciadores.service.interfaces.ITdcService;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.ObjectMapper;
  import com.fasterxml.jackson.databind.ObjectWriter;
  import java.sql.Date;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Optional;
  import javax.servlet.http.HttpServletResponse;
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
  public class programacionController {
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IBibliaService bibliaService;
    @Autowired
    private IPagoService pagoService;
    @Autowired
    private ITdcService tdcService;
    @Autowired
    private IServicioService servicioService;
    List<Persona> personasList;
    
    @GetMapping({"/listarPosiciones"})
    public String listarPosiciones(@RequestParam int idMinisterio, Model model) {
/*  51 */     List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterio(idMinisterio);
/*  52 */     ministerios = this.servicioService.getPositionInitial(ministerios);
/*  53 */     model.addAttribute("listaPosiciones", ministerios);
/*  54 */     model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/*  55 */     model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/*  56 */     return "listarPosiciones";
    }
  
    
    @GetMapping({"/eliminarMinisterio"})
    public String deleteProductoById(@RequestParam int idMinisterio, HttpServletResponse response, Model model) throws ParseException {
/*  62 */     this.servicioService.deleteMinisterio(idMinisterio);
/*  63 */     List<Ministerio> ministerios = this.servicioService.getAll();
/*  64 */     model.addAttribute("ministerios", ministerios);
/*  65 */     return "listarMinisterios";
    }
    
    @PostMapping({"/guardarServicio"})
    public String save(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio, @RequestParam int idMinisterio, HttpServletResponse response, Model model) {
/*  70 */     List<String> encargadosList = new ArrayList<>();
/*  71 */     List<String> posicionesList = new ArrayList<>();
/*  72 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  73 */     String fechaComoCadena = sdf.format(fechaServicio);
/*  74 */     model.addAttribute("fecha", fechaComoCadena);
      
      try {
/*  77 */       for (int i = 0; i < servicio.getEncargado().size(); i++) {
/*  78 */         if (!((String)servicio.getEncargado().get(i)).equals("0") && !((String)servicio.getEncargado().get(i)).equals("")) {
/*  79 */           encargadosList.add(servicio.getEncargado().get(i));
/*  80 */           posicionesList.add(servicio.getPosicion().get(i));
          } 
        } 
        
/*  84 */       servicio.setEncargado(encargadosList);
/*  85 */       servicio.setPosicion(posicionesList);
  
        
/*  88 */       if (this.servicioService.validarDuplicados(servicio).booleanValue()) {
/*  89 */         Optional<Persona> per = this.servicioService.validarProgramacionByFecha(servicio, fechaServicio);
/*  90 */         if (per.isPresent()) {
/*  91 */           model.addAttribute("message", "Servicio Guardado EXitosamente!!! \n el servidor " + ((Persona)per.get()).getNombre() + " ya tiene una asignacion para la fecha " + fechaServicio + " en otro ministerio ");
/*  92 */           this.servicioService.updateProgramacion(servicio, fechaServicio, idMinisterio);
/*  93 */           List<MinisterioDto> list1 = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/*  94 */           List<MinisterioDto> list2 = this.servicioService.poblarPosiciones(list1, servicio);
/*  95 */           model.addAttribute("listaPosiciones", list2);
/*  96 */           model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/*  97 */           model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
            
/*  99 */           return "editar_programacion";
          } 
          
/* 102 */         this.servicioService.saveProgramacion(servicio, fechaServicio, idMinisterio);
/* 103 */         List<Ministerio> list = this.servicioService.getAll();
/* 104 */         model.addAttribute("ministerios", list);
/* 105 */         return "ministerios";
        } 
        
/* 108 */       Persona perDobleAsignacion = this.servicioService.identificarDuplicados(servicio);
/* 109 */       model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor " + perDobleAsignacion.getNombre() + " posee doble asignacion para la fecha " + fechaServicio + " en este ministerio ");
/* 110 */       this.servicioService.updateProgramacion(servicio, fechaServicio, idMinisterio);
/* 111 */       List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/* 112 */       List<MinisterioDto> ministeriosEditar = this.servicioService.poblarPosiciones(ministerios, servicio);
/* 113 */       model.addAttribute("listaPosiciones", ministeriosEditar);
/* 114 */       model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 115 */       model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/* 116 */       model.addAttribute("fecha", fechaComoCadena);
        
/* 118 */       return "editar_programacion";
      }
/* 120 */     catch (Exception e) {
/* 121 */       model.addAttribute("message", "Error: " + e.getMessage());
/* 122 */       List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterio(idMinisterio);
/* 123 */       ministerios = this.servicioService.poblarPosiciones(ministerios, servicio);
/* 124 */       model.addAttribute("listaPosiciones", ministerios);
/* 125 */       model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 126 */       model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/* 127 */       return "listarPosiciones";
      } 
    }
  
  
    
    @PostMapping({"/actualizarServicio"})
    public String actualizarServicio(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio, @RequestParam int idMinisterio, HttpServletResponse response, Model model) {
/* 135 */     List<MinisterioDto> ministerioOriginal = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
      
/* 137 */     List<String> encargadosList = new ArrayList<>();
/* 138 */     List<String> posicionesList = new ArrayList<>();
/* 139 */     for (int i = 0; i < servicio.getEncargado().size(); i++) {
/* 140 */       if (!((String)servicio.getEncargado().get(i)).equals("0") && !((String)servicio.getEncargado().get(i)).equals("")) {
/* 141 */         encargadosList.add(servicio.getEncargado().get(i));
/* 142 */         posicionesList.add(servicio.getPosicion().get(i));
        } 
      } 
      
/* 146 */     servicio.setEncargado(encargadosList);
/* 147 */     servicio.setPosicion(posicionesList);
/* 148 */     if (this.servicioService.validarDuplicados(servicio).booleanValue()) {
/* 149 */       Optional<Persona> per = this.servicioService.validarActualizarProgramacionByFecha(servicio, fechaServicio, idMinisterio);
/* 150 */       if (per.isPresent()) {
/* 151 */         model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor " + ((Persona)per.get()).getNombre() + " ya tiene una asignacion para la fecha " + fechaServicio + " en otro ministerio ");
/* 152 */         this.servicioService.updateProgramacion(servicio, fechaServicio, idMinisterio);
/* 153 */         List<MinisterioDto> list1 = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/* 154 */         List<MinisterioDto> list2 = this.servicioService.poblarPosiciones(list1, servicio);
/* 155 */         model.addAttribute("listaPosiciones", list2);
/* 156 */         model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 157 */         model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/* 158 */         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 159 */         String str = simpleDateFormat.format(fechaServicio);
/* 160 */         model.addAttribute("fecha", str);
          
/* 162 */         return "editar_programacion";
        } 
        
/* 165 */       this.servicioService.updateProgramacion(servicio, fechaServicio, idMinisterio);
/* 166 */       List<Ministerio> list = this.servicioService.getAll();
/* 167 */       model.addAttribute("ministerios", list);
/* 168 */       return "redirect:/consultarProgramacion";
      } 
      
/* 171 */     Persona perDobleAsignacion = this.servicioService.identificarDuplicados(servicio);
/* 172 */     model.addAttribute("message", "Servicio Guardado Exitosamente!!! \n el servidor " + perDobleAsignacion.getNombre() + " posee doble asignacion para la fecha " + fechaServicio + " en este ministerio ");
/* 173 */     this.servicioService.updateProgramacion(servicio, fechaServicio, idMinisterio);
/* 174 */     List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/* 175 */     List<MinisterioDto> ministeriosEditar = this.servicioService.poblarPosiciones(ministerios, servicio);
/* 176 */     model.addAttribute("listaPosiciones", ministeriosEditar);
/* 177 */     model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 178 */     model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterio(idMinisterio));
/* 179 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 180 */     String fechaComoCadena = sdf.format(fechaServicio);
/* 181 */     model.addAttribute("fecha", fechaComoCadena);
      
/* 183 */     return "editar_programacion";
    }
  
    
    @PostMapping({"/guardarAsistencia"})
    public String guardarAsistencia(@ModelAttribute ServicioDto servicio, @RequestParam Date fechaServicio, @RequestParam int idMinisterio, HttpServletResponse response, Model model) {
/* 189 */     List<MinisterioDto> ministerioOriginal = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/* 190 */     Coordinador corSave = this.servicioService.findCoordinadorByFecha(fechaServicio);
/* 191 */     List<String> encargadosList = new ArrayList<>();
/* 192 */     List<String> posicionesList = new ArrayList<>();
/* 193 */     for (int i = 0; i < servicio.getEncargado().size(); i++) {
/* 194 */       if (!((String)servicio.getEncargado().get(i)).equals("0") && !((String)servicio.getEncargado().get(i)).equals("")) {
/* 195 */         encargadosList.add(servicio.getEncargado().get(i));
/* 196 */         posicionesList.add(servicio.getPosicion().get(i));
        } 
      } 
      
/* 200 */     servicio.setEncargado(encargadosList);
/* 201 */     servicio.setPosicion(posicionesList);
/* 202 */     boolean asistenciaGuardad = this.servicioService.validarActualizarProgramacionByFechaAndName(servicio, fechaServicio, idMinisterio);
/* 203 */     if (asistenciaGuardad) {
/* 204 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 205 */       model.addAttribute("message", "Asistencia Guardada Exitosamente!!! ");
/* 206 */       String url = "register_asistencia";
/* 207 */       List<MinisterioDto> list = this.servicioService.getPositionByidMinisterioAndPerson(fechaServicio, idMinisterio);
/* 208 */       String fechaComoCadena = sdf.format(fechaServicio);
        
/* 210 */       list = this.servicioService.limpiarListaPosiciones(list, fechaServicio, idMinisterio);
/* 211 */       model.addAttribute("listaPosiciones", list);
/* 212 */       model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 213 */       model.addAttribute("servidores", this.servicioService.findPersonaByidMnisterioAsistencia(idMinisterio));
/* 214 */       model.addAttribute("fecha", fechaComoCadena);
/* 215 */       model.addAttribute("itemsCombo", this.servicioService.findItemsCombo());
/* 216 */       ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/* 217 */       return url;
      } 
  
      
/* 221 */     List<Ministerio> ministerios = this.servicioService.getAll();
/* 222 */     model.addAttribute("ministerios", ministerios);
/* 223 */     return "redirect:/consultarProgramacion";
    }
  
    
    @PostMapping({"/saveMinisterio"})
    public String save(@RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 229 */     String url = "redirect:/404.html";
      
/* 231 */     if (nombreMinisterio != null) {
/* 232 */       Ministerio ministerioSave = this.servicioService.saveMinisterio(nombreMinisterio);
        
/* 234 */       List<Ministerio> ministerios = this.servicioService.getAll();
/* 235 */       model.addAttribute("ministerios", ministerios);
/* 236 */       return "listarMinisterios";
      } 
/* 238 */     return url;
    }
    
    @GetMapping({"/redirecteditarMinisterio"})
    public String redirecteditarMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 243 */     String url = "redirect:/404.html";
      
/* 245 */     if (nombreMinisterio != null) {
/* 246 */       MinisterioDto ministerioDto = new MinisterioDto();
/* 247 */       ministerioDto.setNombreMinisterio(nombreMinisterio);
/* 248 */       ministerioDto.setId(idMinisterio);
/* 249 */       model.addAttribute("ministerio", ministerioDto);
/* 250 */       return "edit-ministerio";
      } 
/* 252 */     return url;
    }
    
    @PostMapping({"/editarMinisterio"})
    public String editarMinisterio(@ModelAttribute MinisterioDto ministerioDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 257 */     String url = "redirect:/404.html";
      
/* 259 */     if (ministerioDto != null) {
        
/* 261 */       Ministerio ministerioSave = this.servicioService.saveMinisterio(ministerioDto);
        
/* 263 */       List<Ministerio> ministerios = this.servicioService.getAll();
/* 264 */       model.addAttribute("ministerios", ministerios);
/* 265 */       return "listarMinisterios";
      } 
/* 267 */     return url;
    }
    
    @GetMapping({"/buscarPersonasSinMinisterio"})
    public String buscarPersonasSinMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, Model model) {
/* 272 */     List<PersonaDto> personasList = this.servicioService.getPeopleWithoutMinisterio(idMinisterio);
      
/* 274 */     model.addAttribute("personas", personasList);
/* 275 */     model.addAttribute("msj", "Personas las cuales se puede agregar al Ministerio: " + nombreMinisterio);
/* 276 */     model.addAttribute("titulo", "Lista de Personas no inscritas al ministerio");
/* 277 */     model.addAttribute("add", Boolean.valueOf(true));
/* 278 */     model.addAttribute("delete", Boolean.valueOf(false));
/* 279 */     model.addAttribute("nombreMinisterio", nombreMinisterio);
/* 280 */     model.addAttribute("idMinisterio", Integer.valueOf(idMinisterio));
      
/* 282 */     model.addAttribute("pago", Boolean.valueOf(false));
/* 283 */     return "personasSinMinisterio";
    }
  
    
    @PostMapping({"/agregarPersonasAMinisterio"})
    public String agregarPersonasAMinisterio(@RequestParam int idMinisterio, @RequestParam List<Long> categoriasSeleccionadas, Model model) {
/* 289 */     this.servicioService.agregarPersonasAMinisterio(categoriasSeleccionadas, idMinisterio);
/* 290 */     List<PersonaDto> personasList = this.servicioService.findPersonaByidMnisterio(idMinisterio);
/* 291 */     model.addAttribute("personas", personasList);
/* 292 */     model.addAttribute("msj", "Personas inscritas al Ministerio: nombreMinisterio");
/* 293 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 294 */     model.addAttribute("add", Boolean.valueOf(false));
/* 295 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 296 */     model.addAttribute("idMinisterio", Integer.valueOf(idMinisterio));
/* 297 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 298 */     return "personasMinisterio";
    }
    
    @GetMapping({"/personasMinisterio"})
    public String personasMinisterio(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, Model model) {
/* 303 */     List<PersonaDto> personasList = this.servicioService.findPersonaByidMnisterio(idMinisterio);
/* 304 */     boolean consolidacion = false;
      
/* 306 */     model.addAttribute("personas", personasList);
/* 307 */     model.addAttribute("idMinisterio", Integer.valueOf(idMinisterio));
/* 308 */     model.addAttribute("nombreMinisterio", nombreMinisterio);
/* 309 */     model.addAttribute("msj", "Personas inscritas al Ministerio: " + nombreMinisterio);
/* 310 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 311 */     model.addAttribute("add", Boolean.valueOf(false));
/* 312 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 313 */     model.addAttribute("consolidacion", Boolean.valueOf(consolidacion));
/* 314 */     return "personasMinisterio";
    }
    
    @GetMapping({"/redirectedAgregarPosicion"})
    public String redirectedAgregarPosicion(@RequestParam int idMinisterio, @RequestParam String nombreMinisterio, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 319 */     String url = "redirect:/404.html";
      
/* 321 */     if (nombreMinisterio != null) {
/* 322 */       MinisterioDto ministerioDto = new MinisterioDto();
/* 323 */       ministerioDto.setNombreMinisterio(nombreMinisterio);
/* 324 */       ministerioDto.setId(idMinisterio);
/* 325 */       model.addAttribute("ministerio", ministerioDto);
/* 326 */       return "register-posicion";
      } 
/* 328 */     return url;
    }
    @GetMapping({"/verPosiciones"})
    public String verPosiciones(@RequestParam int idMinisterio, Model model) {
/* 332 */     List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterio(idMinisterio);
/* 333 */     model.addAttribute("listaPosiciones", ministerios);
/* 334 */     model.addAttribute("ministerio", this.servicioService.findByidMnisterio(idMinisterio));
/* 335 */     return "verPosiciones";
    }
    
    @PostMapping({"/agregarPosicion"})
    public String agregarPosicion(@ModelAttribute PosicionDto posicionDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 340 */     String url = "redirect:/404.html";
      
/* 342 */     if (posicionDto != null) {
/* 343 */       this.servicioService.savePosicion(posicionDto);
/* 344 */       List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterio(posicionDto.getIdMinisterio());
/* 345 */       model.addAttribute("listaPosiciones", ministerios);
/* 346 */       model.addAttribute("ministerio", this.servicioService.findByidMnisterio(posicionDto.getIdMinisterio()));
/* 347 */       return "verPosiciones";
      } 
/* 349 */     return url;
    }
  
    
    @GetMapping({"/redirecteditarPosicion"})
    public String redirecteditarPosicion(@RequestParam int idPosicion, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 355 */     PosicionDto posicion = this.servicioService.findPosicion(idPosicion);
/* 356 */     model.addAttribute("posicion", posicion);
/* 357 */     return "editar-posicion";
    }
    
    @PostMapping({"/editarPosicion"})
    public String editarPosicion(@ModelAttribute PosicionDto posicionDto, HttpServletResponse response, Model model) throws ParseException, JsonProcessingException {
/* 362 */     String url = "redirect:/404.html";
      
/* 364 */     if (posicionDto != null) {
/* 365 */       this.servicioService.editPosicion(posicionDto);
/* 366 */       List<MinisterioDto> ministerios = this.servicioService.getPositionByidMinisterio(posicionDto.getIdMinisterio());
/* 367 */       model.addAttribute("listaPosiciones", ministerios);
/* 368 */       model.addAttribute("ministerio", this.servicioService.findByidMnisterio(posicionDto.getIdMinisterio()));
/* 369 */       return "verPosiciones";
      } 
/* 371 */     return url;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\programacionController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */