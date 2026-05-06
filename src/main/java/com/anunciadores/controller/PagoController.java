  package com.anunciadores.controller;
  
  import com.anunciadores.dto.CursoDto;
  import com.anunciadores.dto.PagoDto;
  import com.anunciadores.dto.ReportePagoDto;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.Pago;
  import com.anunciadores.service.interfaces.ICursoService;
  import com.anunciadores.service.interfaces.IPagoService;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.Date;
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
  public class PagoController
  {
    @Autowired
    private IPagoService pagoService;
    @Autowired
    private ICursoService cursoService;
    List<Pago> pagoList;
    
    @GetMapping({"/listarPagos"})
    public String Cursos(HttpServletResponse response, Model model) {
/*  41 */     this.pagoList = this.pagoService.findAll();
/*  42 */     model.addAttribute("cursos", this.pagoList);
/*  43 */     return "cursos";
    }
  
    
    @GetMapping({"/buscarPagosCurso"})
    public String buscarPagosCurso(@RequestParam int idPersona, @RequestParam int idCurso, Model model) {
/*  49 */     Curso curso = this.cursoService.findCursoById(Integer.valueOf(idCurso));
/*  50 */     this.pagoList = this.pagoService.findPagosByIdCurso(idPersona, idCurso);
/*  51 */     int pagoTotal = 0;
/*  52 */     int adeuda = 0;
/*  53 */     boolean validarPago = false;
      
/*  55 */     for (Pago pago : this.pagoList) {
/*  56 */       pagoTotal += pago.getValor();
      }
      
/*  59 */     adeuda = curso.getValorTotal() - pagoTotal;
/*  60 */     if (adeuda > 0) {
/*  61 */       validarPago = true;
      }
/*  63 */     model.addAttribute("pagos", this.pagoList);
/*  64 */     model.addAttribute("curso", curso);
/*  65 */     model.addAttribute("pagoTotal", Integer.valueOf(pagoTotal));
/*  66 */     model.addAttribute("adeuda", Integer.valueOf(adeuda));
/*  67 */     model.addAttribute("validarPago", Boolean.valueOf(validarPago));
/*  68 */     model.addAttribute("idPersona", Integer.valueOf(idPersona));
/*  69 */     model.addAttribute("idCurso", Integer.valueOf(idCurso));
/*  70 */     return "pagos";
    }
  
    
    @PostMapping({"/savePago"})
    public String save(@ModelAttribute Pago pago, Model model) throws ParseException {
/*  76 */     String url = "redirect:/404.html";
/*  77 */     if (pago != null) {
        
/*  79 */       Date myDate = new Date();
/*  80 */       pago.setFechaPago((new SimpleDateFormat("dd-MM-yyyy")).format(myDate));
/*  81 */       Pago pagoSave = this.pagoService.save(pago);
/*  82 */       List<Curso> CursosList = this.cursoService.findAll();
/*  83 */       model.addAttribute("cursos", CursosList);
/*  84 */       url = "cursos";
      } 
/*  86 */     return url;
    }
    
    @GetMapping({"/eliminarPago"})
    public String deleteProductoById(@ModelAttribute Pago pago, HttpServletResponse response, Model model) {
/*  91 */     this.pagoService.delete(pago);
/*  92 */     return "redirect:/listarCursos";
    }
  
  
    
    @PostMapping({"/editarPago"})
    public String editarCursoById(@ModelAttribute CursoDto curso, HttpServletResponse response, Model model) throws ParseException {
/*  99 */     Curso cursoMostrar = new Curso();
      
/* 101 */     System.out.println("fechaInicio para modificacion " + curso.getFechaInicio());
/* 102 */     System.out.println("fechaFin para modificacion " + curso.getFechaFin());
/* 103 */     cursoMostrar.setId(curso.getId());
  
      
/* 106 */     cursoMostrar.setNombreCurso(curso.getNombreCurso());
/* 107 */     cursoMostrar.setValorTotal(curso.getValorTotal());
  
  
      
/* 111 */     model.addAttribute("curso", curso);
      
/* 113 */     return "edit-curso";
    }
  
  
  
    
    @GetMapping({"/reportePagosCursos"})
    public String reportePagosCursos(Model model) {
/* 121 */     List<PagoDto> pagoList = this.pagoService.reportePagosCursos(1);
/* 122 */     int pagoTotal = 0;
/* 123 */     model.addAttribute("pagos", pagoList);
/* 124 */     model.addAttribute("pagoTotal", Integer.valueOf(pagoTotal));
/* 125 */     return "pagos";
    }
  
    
    @GetMapping({"/reportePagos"})
    public String reportePagos(HttpServletResponse response, Model model) {
/* 131 */     List<ReportePagoDto> pagosList = this.pagoService.reportePagos();
/* 132 */     model.addAttribute("pagos", pagosList);
/* 133 */     return "reportePagos";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\PagoController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */