  package com.anunciadores.controller;
  
  import com.anunciadores.dto.CustomMultipartFile;
  import com.anunciadores.dto.TdcDto;
  import com.anunciadores.dto.TdcReporteDto;
  import com.anunciadores.model.Pago;
  import com.anunciadores.model.Persona;
  import com.anunciadores.model.Tdc;
  import com.anunciadores.service.interfaces.ICursoService;
  import com.anunciadores.service.interfaces.IPersonaService;
  import com.anunciadores.service.interfaces.ITdcService;
  import com.anunciadores.util.UtilDate;
  import java.awt.image.BufferedImage;
  import java.io.ByteArrayOutputStream;
  import java.io.IOException;
  import java.sql.Date;
  import java.text.ParseException;
  import java.time.ZoneId;
  import java.time.ZonedDateTime;
  import java.time.format.DateTimeFormatter;
  import java.util.Base64;
  import java.util.List;
  import javax.imageio.ImageIO;
  import javax.servlet.http.HttpServletResponse;
  import org.apache.commons.io.FilenameUtils;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.multipart.MultipartFile;
  
  @Controller
  @RequestMapping
  public class TdcController {
    @Autowired
    private ITdcService tdcService;
    @Autowired
    private ICursoService cursoService;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private UtilDate utilDate;
    List<Pago> pagoList;
/*  52 */   private final Logger log = LoggerFactory.getLogger(com.anunciadores.controller.TdcController.class);
    @GetMapping({"/uploadimage"})
    public String displayUploadForm() {
/*  55 */     return "imageupload/index";
    }
    @PostMapping({"/upload"})
    public String uploadImage(@ModelAttribute Persona persona, Model model, @RequestParam("image") MultipartFile file) throws IOException {
/*  59 */     this.log.info("cargar imagenes inicio");
/*  60 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/*  61 */     ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
/*  62 */     String fecha = nowInBogota.format(formatter);
/*  63 */     model.addAttribute("fecha", fecha);
      try {
/*  65 */       model.addAttribute("idPersona", persona.getId());
/*  66 */       Tdc picture = new Tdc();
/*  67 */       DateTimeFormatter formatterSAve = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*  68 */       ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        
/*  70 */       String fechaformateada = now.format(formatterSAve);
/*  71 */       Date fechafinal = Date.valueOf(fechaformateada);
/*  72 */       String extension = null;
/*  73 */       if (file != null && file.getSize() > 0L) {
          
/*  75 */         extension = FilenameUtils.getExtension(file.getOriginalFilename());
/*  76 */         System.out.println(extension);
          
/*  78 */         this.log.info("tamaño del archivo es de " + file.getSize() + " bytes");
/*  79 */         this.log.info("fecha a usar: " + fechafinal);
        } else {
/*  81 */         model.addAttribute("messageError", "Debe seleccionar una foto o documento de su tiempo con Dios");
/*  82 */         return "registerTDC";
        } 
/*  84 */       if (extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jfif")) {
/*  85 */         BufferedImage image = ImageIO.read(file.getInputStream());
/*  86 */         BufferedImage originalImage = this.tdcService.resizeImage(image, 480, 1024);
/*  87 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  88 */         ImageIO.write(originalImage, "jpg", baos);
/*  89 */         baos.flush();
          
/*  91 */         CustomMultipartFile customMultipartFile = new CustomMultipartFile(baos.toByteArray());
/*  92 */         String encodedString = Base64.getEncoder().encodeToString(customMultipartFile.getBytes());
/*  93 */         picture.setTdc(encodedString);
/*  94 */         picture.setPdf(Boolean.valueOf(false));
/*  95 */         picture.setImagen(Boolean.valueOf(true));
        } 
/*  97 */       if (extension.equalsIgnoreCase("pdf")) {
/*  98 */         picture.setTdc(Base64.getEncoder().encodeToString(file.getBytes()));
/*  99 */         picture.setPdf(Boolean.valueOf(true));
/* 100 */         picture.setImagen(Boolean.valueOf(false));
        } 
/* 102 */       DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/* 103 */       String fechaformat2 = nowInBogota.format(formatter2);
/* 104 */       model.addAttribute("fecha", fechaformat2);
/* 105 */       if (this.tdcService.getTdcByFechaAndPersona(fechafinal, persona.getId().intValue())) {
          
/* 107 */         picture.setFechaCreacion(fechafinal);
/* 108 */         picture.setIdPersona(persona.getId().intValue());
/* 109 */         picture.setNombredocumento("TCD_" + fechafinal);
/* 110 */         this.log.debug("se va a guardar imagen debug");
/* 111 */         this.log.info("se va a guardar imagen info");
/* 112 */         this.tdcService.save(fechafinal, picture);
/* 113 */         model.addAttribute("message", "su Imagen ha sido cargada correctamente");
/* 114 */         model.addAttribute("msg", null);
        } else {
/* 116 */         model.addAttribute("messageError", "ud ya a cargardo su TCD del dia de hoy");
/* 117 */         model.addAttribute("msg", null);
        } 
/* 119 */     } catch (Exception e) {
/* 120 */       this.log.error("error cargar imagen: ", e);
/* 121 */       model.addAttribute("message", null);
/* 122 */       model.addAttribute("messageError", "Error al cargar imagen");
/* 123 */       return "registerTDC";
      } 
      
/* 126 */     return "registerTDC";
    }
  
    
    @GetMapping({"/listarTdc"})
    public String getdcById(Model model) throws ParseException {
/* 132 */     List<TdcDto> listaTdc = this.tdcService.getTdcByFecha(this.utilDate.cargarfechaActualBogotaDate());
/* 133 */     model.addAttribute("listaTdc", listaTdc);
/* 134 */     return "listarTdc";
    }
    
    @GetMapping({"/consutarPdf"})
    public ResponseEntity<Object> consutarEmail(HttpServletResponse response, Model model) {
/* 139 */     Tdc image = this.tdcService.getTdcById(1);
      
/* 141 */     return new ResponseEntity(image, null, HttpStatus.ACCEPTED);
    }
    @PostMapping({"/buscarTdcByFecha"})
    public String buscarTdcByFecha(@RequestParam Date fecha, Model model) {
/* 145 */     List<TdcDto> listaTdc = this.tdcService.getTdcByFecha(fecha);
/* 146 */     model.addAttribute("listaTdc", listaTdc);
/* 147 */     return "listarTdc";
    }
    @PostMapping({"/buscarTdcByRangoFecha"})
    public String buscarTdcByRangoFecha(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, Model model) throws ParseException {
/* 151 */     if (fechaInicio != null && fechaFin != null) {
/* 152 */       List<TdcReporteDto> listaTdc = this.tdcService.findAllBetweenDates(fechaInicio, fechaFin);
/* 153 */       model.addAttribute("listaTdc", listaTdc);
/* 154 */       model.addAttribute("fechaInicio", fechaInicio);
/* 155 */       model.addAttribute("fechaFin", fechaFin);
      } 
/* 157 */     return "reporteTdc";
    }
    @PostMapping({"/datalleTdcByPersona"})
    public String datalleTdcByPersona(@RequestParam int idPersona, @RequestParam Date fechaInicio, @RequestParam Date fechaFin, Model model) {
/* 161 */     if (fechaInicio != null && fechaFin != null && idPersona != 0) {
/* 162 */       List<TdcDto> listaTdc = this.tdcService.findAllBetweenDatesByPersona(fechaInicio, fechaFin, idPersona);
/* 163 */       model.addAttribute("listaTdc", listaTdc);
/* 164 */       model.addAttribute("idPersona", Integer.valueOf(idPersona));
/* 165 */     }  return "reporteTdcPersona";
    }
    @GetMapping({"/viewImageIndivudual"})
    public String viewImageIndivudual(@RequestParam int idTdc, Model model) {
/* 169 */     Tdc tcdObject = this.tdcService.getById(idTdc);
/* 170 */     Persona persona = this.personaService.findPersonaById(Integer.valueOf(tcdObject.getIdPersona()));
/* 171 */     model.addAttribute("tcd", tcdObject);
/* 172 */     model.addAttribute("persona", persona);
/* 173 */     return "visualizarTCDIndividual";
    }
    @GetMapping({"/viewImageFilter"})
    public String viewImageFilter(@RequestParam int idTdc, Model model) {
/* 177 */     Tdc tcdObject = this.tdcService.getById(idTdc);
/* 178 */     Persona persona = this.personaService.findPersonaById(Integer.valueOf(tcdObject.getIdPersona()));
/* 179 */     model.addAttribute("tcd", tcdObject);
/* 180 */     model.addAttribute("persona", persona);
/* 181 */     return "visualizarTDC";
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\TdcController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */