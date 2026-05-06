  package com.anunciadores.controller;
  
  import com.anunciadores.dto.CursoDto;
  import com.anunciadores.dto.HistoricoNotasDto;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.NotasCurso;
  import com.anunciadores.model.Persona;
  import com.anunciadores.service.interfaces.IBibliaService;
  import com.anunciadores.service.interfaces.ICursoService;
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
  public class CursoController
  {
    @Autowired
    private ICursoService cursoService;
    @Autowired
    private IBibliaService bibliaService;
    @Autowired
    private IPersonaService personaService;
    List<Curso> CursosList;
    List<Persona> personasList;
    
    @GetMapping({"/listarCursos"})
    public String Cursos(HttpServletResponse response, Model model) {
/*  49 */     this.CursosList = this.cursoService.findAll();
/*  50 */     model.addAttribute("cursos", this.CursosList);
/*  51 */     return "cursos";
    }
    
    @PostMapping({"/listarCursosAbiertos"})
    public String cursosAbiertos(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
/*  56 */     this.CursosList = this.cursoService.findAllActiveByPerson(persona.getId().intValue());
/*  57 */     model.addAttribute("cursos", this.CursosList);
/*  58 */     model.addAttribute("persona", persona);
/*  59 */     return "cursosAutoInscripcion";
    }
  
    
    @GetMapping({"/buscarCurso/{id}"})
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
/*  65 */     return ResponseEntity.ok(this.cursoService.findCursoById(id));
    }
  
    
    @PostMapping({"/saveCurso"})
    public String save(@ModelAttribute CursoDto Curso, HttpServletResponse response, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/*  71 */     String url = "redirect:/404.html";
      
/*  73 */     if (Curso != null) {
/*  74 */       Curso CursoSave = this.cursoService.save(Curso);
/*  75 */       this.CursosList = this.cursoService.findAll();
/*  76 */       model.addAttribute("cursos", this.CursosList);
/*  77 */       url = "cursos";
      } 
/*  79 */     return url;
    }
    
    @PostMapping({"/saveNotasCurso"})
    public String saveNotasCurso(@ModelAttribute NotasCurso notasCurso, @RequestParam int idPersona, @RequestParam int idCurso, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/*  84 */     StringBuilder url = new StringBuilder();
/*  85 */     url.append("redirect:/notasCurso?idCurso=");
/*  86 */     if (notasCurso != null) {
/*  87 */       notasCurso.setPersona(this.personaService.findPersonaById(Integer.valueOf(idPersona)));
/*  88 */       notasCurso.setCurso(this.cursoService.findCursoById(Integer.valueOf(idCurso)));
        
/*  90 */       NotasCurso notas = this.cursoService.saveNotasCurso(notasCurso);
/*  91 */       this.CursosList = this.cursoService.findAll();
/*  92 */       model.addAttribute("idCurso", Integer.valueOf(idCurso));
/*  93 */       url.append(idCurso);
/*  94 */       url.append("&nombreCurso=Curso%20pruebas%20");
      } 
      
/*  97 */     return url.toString();
    }
    
    @PostMapping({"/historicoNotas"})
    public String historicoNotas(@ModelAttribute HistoricoNotasDto idHistorico, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/* 102 */     List<NotasCurso> historicoNotas = this.cursoService.findHistoricoNotas(idHistorico.getIdHistorico());
/* 103 */     model.addAttribute("historicoNotas", historicoNotas.isEmpty() ? null : historicoNotas);
/* 104 */     return "historicoNotas";
    }
  
  
    
    @GetMapping({"/eliminarCurso"})
    public String deleteProductoById(@ModelAttribute Curso curso, HttpServletResponse response, Model model) throws ParseException {
/* 111 */     this.cursoService.desactivarCurso(curso);
/* 112 */     this.CursosList = this.cursoService.findAll();
/* 113 */     model.addAttribute("cursos", this.CursosList);
/* 114 */     return "cursos";
    }
  
  
    
    @PostMapping({"/agregarPersona"})
    public String agregarPersona(@RequestParam String idCurso, @RequestParam String idPersona) {
/* 121 */     String url = "redirect:/404.html";
      
/* 123 */     url = "index";
      
/* 125 */     return url;
    }
  
    
    @GetMapping({"/editarCurso"})
    public String editarCursoById(@ModelAttribute CursoDto curso, HttpServletResponse response, Model model) throws ParseException {
/* 131 */     Curso cursoMostrar = new Curso();
/* 132 */     cursoMostrar = this.cursoService.findCursoById(Integer.valueOf(curso.getId()));
/* 133 */     cursoMostrar.setId(curso.getId());
  
      
/* 136 */     cursoMostrar.setNombreCurso(curso.getNombreCurso());
/* 137 */     cursoMostrar.setValorTotal(curso.getValorTotal());
      
/* 139 */     curso.setFechaInicio(this.cursoService.formatFecha(curso.getFechaInicio()));
/* 140 */     curso.setFechaFin(this.cursoService.formatFecha(curso.getFechaFin()));
      
/* 142 */     this.personasList = this.personaService.findAllUsuarios();
/* 143 */     model.addAttribute("personas", this.personasList);
/* 144 */     model.addAttribute("curso", cursoMostrar);
      
/* 146 */     return "edit-curso";
    }
  
  
    
    @GetMapping({"/regresar"})
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
/* 153 */     model.addAttribute("name", name);
/* 154 */     model.addAttribute("pago", Boolean.valueOf(false));
/* 155 */     model.addAttribute("consolidacion", Boolean.valueOf(false));
/* 156 */     return "personasCurso";
    }
    
    @PostMapping({"/buscarCursosByPersona"})
    public String buscarCursosByPersona(@ModelAttribute Persona persona, HttpServletResponse response, Model model) {
/* 161 */     List<CursoDto> CursosList = this.cursoService.findCursosDtoByIdPersona(persona.getId());
      
/* 163 */     model.addAttribute("consolidar", Boolean.valueOf(true));
/* 164 */     model.addAttribute("cursos", CursosList);
/* 165 */     return "cursosUsuario";
    }
    
    @GetMapping({"/CursosPrueba"})
    public String CursosPrueba(@RequestParam int id, Model model) {
/* 170 */     List<Curso> CursosList = this.cursoService.findCursosByIdPersona(Integer.valueOf(id));
/* 171 */     CursosList = this.cursoService.findAll();
/* 172 */     model.addAttribute("cursos", CursosList);
/* 173 */     return "cursos";
    }
    
    @GetMapping({"/redirectNotasCurso"})
    public String redirectNotasCurso(@RequestParam int idCurso, Model model) throws ParseException, JsonMappingException, JsonProcessingException {
/* 178 */     StringBuilder url = new StringBuilder();
/* 179 */     url.append("redirect:/notasCurso?idCurso=");
/* 180 */     url.append(idCurso);
/* 181 */     url.append("&nombreCurso=Curso%20pruebas%20");
/* 182 */     return url.toString();
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\CursoController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */