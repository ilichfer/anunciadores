  package com.anunciadores.controller;
  
  import com.anunciadores.dto.AsignacionConsolidacionDto;
  import com.anunciadores.dto.ConsolidacionDto;
  import com.anunciadores.dto.PersonaConsolidacionDto;
  import com.anunciadores.model.Mesa;
  import com.anunciadores.model.Persona;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.service.interfaces.IConsolidacionService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
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
  
  
  
  
  
  @Controller
  @RequestMapping
  public class consolidacionController
  {
    @Autowired
    private IConsolidacionService consolidacionService;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IPersonaRepo personaRepoImpl;
    
    @GetMapping({"/buscarConsolidacion"})
    public String buscarConsolidacionById(Model model) {
/*  42 */     String nombre = "consolidacion";
/*  43 */     List<PersonaConsolidacionDto> servidoresConsolidacion = this.consolidacionService.listarservidoresConsolidacion(nombre);
/*  44 */     model.addAttribute("personas", servidoresConsolidacion);
/*  45 */     return "personasConsolidacion";
    }
  
  
    
    @PostMapping({"/buscarConsolidacionByPersona"})
    public String buscarConsolidacionByPersona(@RequestParam int idPersona, Model model) {
/*  52 */     String nombre = "consolidacion";
/*  53 */     List<AsignacionConsolidacionDto> personasList = this.consolidacionService.listarConsolidacionByServidor(idPersona);
/*  54 */     model.addAttribute("personas", personasList);
/*  55 */     model.addAttribute("msj", "Personas asignadas para consolidacion");
/*  56 */     model.addAttribute("titulo", "Lista de Personas a consolidar");
/*  57 */     model.addAttribute("add", Boolean.valueOf(true));
/*  58 */     model.addAttribute("delete", Boolean.valueOf(false));
/*  59 */     model.addAttribute("nombreCurso", "nombreCurso");
/*  60 */     model.addAttribute("idCurso", "idCurso");
/*  61 */     model.addAttribute("pago", Boolean.valueOf(false));
/*  62 */     return "personasAConsolidar";
    }
  
    
    @PostMapping({"/saveConsolidacion"})
    public String saveConsolidacion(@ModelAttribute ConsolidacionDto consolidacionDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/*  68 */     String url = "asignarPadres";
/*  69 */     if (consolidacionDto != null && consolidacionDto.getIdPersonaConsolidar() != 0) {
/*  70 */       Boolean asigancion = this.consolidacionService.asignarPersonaAPadreEspiritual(consolidacionDto.getIdPadreEspiritual(), consolidacionDto.getIdPersonaConsolidar());
/*  71 */       if (asigancion.booleanValue()) {
/*  72 */         url = "redirect:/buscarConsolidacion";
        } else {
/*  74 */         List<Persona> listtConsolidacion = this.personaRepoImpl.listarConsolidacion();
/*  75 */         Persona persona = this.personaService.findPersonaById(Integer.valueOf(consolidacionDto.getIdPadreEspiritual()));
/*  76 */         model.addAttribute("id", persona.getId());
/*  77 */         model.addAttribute("nombre", persona.getNombre());
/*  78 */         model.addAttribute("asistentes", listtConsolidacion);
/*  79 */         model.addAttribute("message", "ya posee una consolidacion asignada");
        } 
      } else {
/*  82 */       List<Persona> listtConsolidacion = this.personaRepoImpl.listarConsolidacion();
/*  83 */       Persona persona = this.personaService.findPersonaById(Integer.valueOf(consolidacionDto.getIdPadreEspiritual()));
/*  84 */       model.addAttribute("message", "debe seleccionar una de las opciones disponibles");
/*  85 */       model.addAttribute("id", persona.getId());
/*  86 */       model.addAttribute("nombre", persona.getNombre());
/*  87 */       model.addAttribute("asistentes", listtConsolidacion);
      } 
/*  89 */     return url;
    }
  
  
    
    @GetMapping({"/eliminarConsolidacion"})
    public String deleteProductoById(@ModelAttribute Mesa mesa, HttpServletResponse response, Model model) {
/*  96 */     return "mesas";
    }
  
  
    
    @PostMapping({"/guardarActividadConsolidacion"})
    public String guardarActividadConsolidacion(@RequestParam int idPersona, @RequestParam int idConsolidacion, @RequestParam String desConsolidacion, Model model) {
/* 103 */     this.consolidacionService.saveDescripcionConsolidacion(idConsolidacion, desConsolidacion);
/* 104 */     List<AsignacionConsolidacionDto> personasList = this.consolidacionService.listarConsolidacionByServidor(idPersona);
/* 105 */     model.addAttribute("personas", personasList);
/* 106 */     model.addAttribute("msj", "Personas asignadas para consolidacion");
/* 107 */     model.addAttribute("titulo", "Lista de Personas a consolidar");
/* 108 */     model.addAttribute("add", Boolean.valueOf(true));
/* 109 */     model.addAttribute("delete", Boolean.valueOf(false));
/* 110 */     model.addAttribute("nombreCurso", "nombreCurso");
/* 111 */     model.addAttribute("idCurso", "idCurso");
/* 112 */     model.addAttribute("idPersona", Integer.valueOf(idPersona));
/* 113 */     model.addAttribute("pago", Boolean.valueOf(false));
/* 114 */     return "personasAConsolidar";
    }
  
    
    @GetMapping({"/redirectActividadConsolidacion"})
    public String redirectActividadConsolidacion(@RequestParam int idPersona, @RequestParam int idConsolidacion, Model model) {
/* 120 */     model.addAttribute("idConsolidacion", Integer.valueOf(idConsolidacion));
/* 121 */     model.addAttribute("idPersona", Integer.valueOf(idPersona));
/* 122 */     return "register-desconsolidacion";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\consolidacionController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */