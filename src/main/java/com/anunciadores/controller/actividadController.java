   package com.anunciadores.controller;
 
  import com.anunciadores.dto.ActividadDto;
  import com.anunciadores.model.Actividad;
  import com.anunciadores.model.Mesa;
  import com.anunciadores.model.Persona;
  import com.anunciadores.service.interfaces.IActividadService;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.fasterxml.jackson.core.JsonProcessingException;
  import com.fasterxml.jackson.databind.JsonMappingException;
  import java.text.ParseException;
  import java.util.List;
  import javax.servlet.http.HttpServletResponse;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  
  
  
  
  
  
  
  
  
  
  
  @Controller
  @RequestMapping
  public class actividadController
  {
    @Autowired
    private IActividadService actividadService;
    @Autowired
    private IBibliaService bibliaService;
    @Autowired(required = true)
    private IPersonaService personaService;
    List<Actividad> ActividadesList;
    List<Persona> personasList;
    List<Mesa> mesasList;
    
    @GetMapping({"/listarActividades"})
    public String Cursos(HttpServletResponse response, Model model) {
/*  52 */     this.ActividadesList = this.actividadService.listarActiviades();
/*  53 */     model.addAttribute("actividades", this.ActividadesList);
/*  54 */     return "actividades";
    }
    
    @GetMapping({"/actividadesAutoIncripcion"})
    public String listarActividadesAutoIncripcion(HttpServletResponse response, Model model) {
/*  59 */     this.ActividadesList = this.actividadService.listarActiviades();
/*  60 */     model.addAttribute("actividades", this.ActividadesList);
/*  61 */     return "actividadesAutoInscripcion";
    }
  
    
    @GetMapping({"/buscarActividad/{id}"})
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
/*  67 */     return ResponseEntity.ok(this.actividadService.findActividadById(id));
    }
  
    
    @PostMapping({"/saveActividad"})
    public String save(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/*  73 */     String url = "redirect:/404.html";
/*  74 */     if (actividadDto != null) {
/*  75 */       Actividad actividad = this.actividadService.save(actividadDto);
/*  76 */       this.ActividadesList = this.actividadService.listarActiviades();
/*  77 */       model.addAttribute("actividades", this.ActividadesList);
/*  78 */       url = "actividades";
      } 
/*  80 */     return url;
    }
  
    
    @GetMapping({"/eliminarActividad"})
    public String deleteProductoById(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response, Model model) {
/*  86 */     this.actividadService.delete(actividadDto);
/*  87 */     this.ActividadesList = this.actividadService.listarActiviades();
/*  88 */     model.addAttribute("actividades", this.ActividadesList);
/*  89 */     return "actividades";
    }
  
  
  
  
  
  
  
  
  
  
    
    @GetMapping({"/editarActividad"})
    public String editarCursoById(@ModelAttribute ActividadDto actividadDto, HttpServletResponse response, Model model) throws ParseException {
/* 104 */     model.addAttribute("actividad", actividadDto);
      
/* 106 */     return "edit-actividad";
    }
  
  
    
    @GetMapping({"/agregarPersonaActividad"})
    public String AgregarPersonasCurso(@RequestParam int idPersona, @RequestParam int idActividad, @RequestParam String nombreActividad, Model model) {
/* 113 */     this.personaService.agregarPersonaActividad(idPersona, idActividad);
/* 114 */     this.personasList = this.actividadService.buscarPersonasActividad(idActividad);
/* 115 */     model.addAttribute("personas", this.personasList);
/* 116 */     model.addAttribute("msj", "Personas asociadas a la actividad: " + nombreActividad);
/* 117 */     model.addAttribute("titulo", "Lista de Personas inscritas");
/* 118 */     model.addAttribute("add", Boolean.valueOf(false));
/* 119 */     model.addAttribute("delete", Boolean.valueOf(true));
/* 120 */     model.addAttribute("idActividad", Integer.valueOf(idActividad));
/* 121 */     return "personasActividad";
    }
    
    @GetMapping({"/buscarPersonasSinActividad"})
    public String buscarPersonasSinCurso(@RequestParam int idActividad, @RequestParam String nombreActividad, Model model) {
/* 126 */     this.personasList = this.actividadService.buscarTodosSinActividad(idActividad);
/* 127 */     model.addAttribute("personas", this.personasList);
/* 128 */     model.addAttribute("msj", "Personas las cuales se puede agregar a la actividad: " + nombreActividad);
/* 129 */     model.addAttribute("titulo", "Lista de Personas no inscritas");
/* 130 */     model.addAttribute("add", Boolean.valueOf(true));
/* 131 */     model.addAttribute("delete", Boolean.valueOf(false));
/* 132 */     model.addAttribute("nombreActividad", nombreActividad);
/* 133 */     model.addAttribute("idActividad", Integer.valueOf(idActividad));
/* 134 */     return "personasActividad";
    }
    
    @PostMapping({"/hisMisActividades"})
    public String hisMisActividades(@RequestParam int idPersonaMinisterioUser, Model model) {
/* 139 */     List<Actividad> actividades = this.actividadService.listarActiviadesByPersona(idPersonaMinisterioUser);
      
/* 141 */     model.addAttribute("actividades", actividades);
/* 142 */     return "misActividades";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\actividadController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */