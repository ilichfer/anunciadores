  package  com.anunciadores.service;
  
  import com.anunciadores.dto.CursoDto;
  import com.anunciadores.dto.NotasCursoDTO;
  import com.anunciadores.dto.PersonaDto;
  import com.anunciadores.mapper.mapperNotas;
  import com.anunciadores.mapper.mapperPersona;
  import com.anunciadores.model.Curso;
  import com.anunciadores.model.NotasCurso;
  import com.anunciadores.model.Persona;
  import com.anunciadores.repository.ConsolidacionRepoImpl;
  import com.anunciadores.repository.CursosRepoImpl;
  import com.anunciadores.repository.ICursoRepo;
  import com.anunciadores.repository.INotasCursoRepo;
  import com.anunciadores.repository.IPersonaRepo;
  import com.anunciadores.service.interfaces.ICursoService;
  import java.text.DateFormat;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.Comparator;
  import java.util.Date;
  import java.util.List;
  import java.util.Optional;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  
  
  @Service
  public class CursoServiceImpl
    implements ICursoService
  {
    @Autowired
    private ICursoRepo cursoRepository;
    @Autowired
    private IPersonaRepo personaRepo;
    @Autowired
    private INotasCursoRepo notasCursoRepo;
    @Autowired
    private CursosRepoImpl cursosRepository;
    @Autowired
    private ConsolidacionRepoImpl consolidacionDao;
    @Autowired
    private mapperNotas mapperNotas;
    @Autowired
    private mapperPersona mapperPersona;
/*  47 */   List<Curso> listaCursos = new ArrayList<>();
  
    
    public List<Curso> findAll() {
/*  51 */     List<Curso> listaCursosActivos = new ArrayList<>();
      
/*  53 */     this.listaCursos = this.cursoRepository.findAll();
      
/*  55 */     for (Curso curso : this.listaCursos) {
/*  56 */       if (curso.isActivo()) {
/*  57 */         listaCursosActivos.add(curso);
        }
      } 
      
/*  61 */     return listaCursosActivos;
    }
  
  
    
    public List<Curso> findAllActive() {
/*  67 */     List<Curso> listaCursosActivos = new ArrayList<>();
      
/*  69 */     this.listaCursos = this.cursoRepository.findByActivo(true);
      
/*  71 */     for (Curso curso : this.listaCursos) {
/*  72 */       if (curso.isActivo()) {
/*  73 */         listaCursosActivos.add(curso);
        }
      } 
      
/*  77 */     return listaCursosActivos;
    }
  
  
    
    public List<Curso> findAllActiveByPerson(int idPersona) {
/*  83 */     List<Curso> listaCursosActivos = new ArrayList<>();
      
/*  85 */     this.listaCursos = this.cursoRepository.findByActivos(idPersona);
      
/*  87 */     for (Curso curso : this.listaCursos) {
/*  88 */       if (curso.isActivo()) {
/*  89 */         listaCursosActivos.add(curso);
        }
      } 
      
/*  93 */     return listaCursosActivos;
    }
  
    
    public Curso save(CursoDto curso) throws ParseException {
/*  98 */     Curso cursoSave = new Curso();
      
/* 100 */     List<Curso> list = this.cursoRepository.findTopByOrderByIdDesc();
/* 101 */     cursoSave.setFechaFin(curso.getFechaFin());
/* 102 */     cursoSave.setFechaInicio(curso.getFechaInicio());
/* 103 */     cursoSave.setId(curso.getId());
/* 104 */     cursoSave.setNombreCurso(curso.getNombreCurso());
/* 105 */     cursoSave.setValorTotal(curso.getValorTotal());
/* 106 */     cursoSave.setComentario((curso.getCheck() != null));
/* 107 */     cursoSave.setActivo(true);
/* 108 */     cursoSave.setProfesor(this.personaRepo.findById(Integer.valueOf(curso.getProfesor())).get());
/* 109 */     return (Curso)this.cursoRepository.save(cursoSave);
    }
  
  
    
    public Curso findCursoById(Integer id) {
/* 115 */     Optional<Curso> curso = this.cursoRepository.findById(id);
      
/* 117 */     return curso.get();
    }
  
  
    
    public Curso delete(Curso curso) {
/* 123 */     this.cursoRepository.deleteById(Integer.valueOf(curso.getId()));
      
/* 125 */     return curso;
    }
  
    
    public NotasCurso findNotasByCurso(int idCurso, int idPersona) {
/* 130 */     return this.notasCursoRepo.findNotasByCurso(idCurso, idPersona);
    }
  
    
    public List<NotasCurso> findHistoricoNotas(int idPersona) {
/* 135 */     Optional<List<NotasCurso>> lista = this.notasCursoRepo.findHistoricoNotas(idPersona);
/* 136 */     if (lista.isPresent()) {
/* 137 */       return lista.get();
      }
/* 139 */     List<NotasCurso> listaVacia = new ArrayList<>();
/* 140 */     return listaVacia;
    }
  
    
    public NotasCurso saveNotasCurso(NotasCurso notas) throws ParseException {
/* 145 */     double notaM = notas.getNotaMaestro() * 0.3D;
/* 146 */     double notaA = notas.getNotaAsistencia() * 0.2D;
/* 147 */     double notaP = notas.getNotaPractica() * 0.2D;
/* 148 */     double notaEF = notas.getNotaExamenFinal() * 0.3D;
/* 149 */     double notafinal = notaM + notaA + notaP + notaEF;
/* 150 */     notas.setNotaFinal(notafinal);
      
/* 152 */     return (NotasCurso)this.notasCursoRepo.save(notas);
    }
  
  
    
    public Date ParseFecha(String fecha) throws ParseException {
/* 158 */     fecha = fecha.replace("-", "/");
/* 159 */     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
/* 160 */     Date fechaDate = null;
/* 161 */     fechaDate = formato.parse(fecha);
/* 162 */     return fechaDate;
    }
  
    
    public String formatFecha(String fecha) throws ParseException {
/* 167 */     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  
  
      
/* 171 */     Date fechaDate = null;
/* 172 */     fechaDate = dateFormat.parse(fecha);
      
/* 174 */     Date date = new Date();
/* 175 */     String dateToStr = dateFormat.format(fechaDate);
/* 176 */     System.out.println("fechaInicio  formateada " + dateToStr);
/* 177 */     return dateToStr;
    }
  
    
    public List<PersonaDto> buscarNotasXPersonas(int idCurso, List<Persona> estudiantes) {
/* 182 */     List<PersonaDto> personasDto = this.mapperPersona.listEntityToConsolidacionDto(estudiantes);
/* 183 */     personasDto.forEach(p -> p.setNotas(EntityToNotasCursoDTO(findNotasByCurso(idCurso, p.getId()))));
/* 184 */     if (!personasDto.isEmpty()) {
/* 185 */       personasDto.sort(Comparator.comparing(p -> Double.valueOf(p.getNotas().getNotaFinal())));
      }
/* 187 */     return personasDto;
    }
  
    
    public List<Curso> findCursosByIdPersona(Integer idPersona) {
/* 192 */     return this.cursosRepository.cursosByIdPersona(idPersona);
    }
  
  
    
    public List<CursoDto> findCursosDtoByIdPersona(Integer idPersona) {
/* 198 */     List<CursoDto> cursosDto = new ArrayList<>();
/* 199 */     List<Curso> listaCursos = this.cursoRepository.cursosByIdPersona(idPersona.intValue());
/* 200 */     List<Curso> listaCursosDictados = this.cursoRepository.cursosByIdProfesor(idPersona.intValue());
/* 201 */     if (listaCursosDictados != null && !listaCursosDictados.isEmpty()) {
/* 202 */       listaCursosDictados.forEach(p -> listaCursos.add(p));
      }
/* 204 */     listaCursos.forEach(c -> cursosDto.add(contruirCursosConsolidados(c, idPersona)));
/* 205 */     return cursosDto;
    }
    
    private CursoDto contruirCursosConsolidados(Curso curso, Integer idPersona) {
/* 209 */     CursoDto cursoDto = new CursoDto();
      
/* 211 */     cursoDto.setId(curso.getId());
/* 212 */     cursoDto.setIdPago(curso.getIdPago());
/* 213 */     cursoDto.setFechaInicio(curso.getFechaInicio());
/* 214 */     cursoDto.setFechaFin(curso.getFechaFin());
/* 215 */     cursoDto.setNombreCurso(curso.getNombreCurso());
/* 216 */     cursoDto.setValorTotal(curso.getValorTotal());
/* 217 */     cursoDto.setProfesorDto(curso.getProfesor());
/* 218 */     boolean activarNotas = false;
/* 219 */     if (curso.getProfesor().getId() == idPersona) {
/* 220 */       activarNotas = true;
      }
/* 222 */     cursoDto.setNotas(activarNotas);
/* 223 */     if (cursoDto.getNombreCurso().contains("padres")) {
/* 224 */       cursoDto.setPersonaAConsolidar(buscarConsolidados(idPersona));
      }
/* 226 */     return cursoDto;
    }
    
    private PersonaDto buscarConsolidados(Integer idPersona) {
/* 230 */     PersonaDto personaDto = new PersonaDto();
/* 231 */     Persona persona = this.consolidacionDao.listarPersonaConsolidacion(idPersona.intValue());
/* 232 */     personaDto.setId(persona.getId().intValue());
/* 233 */     personaDto.setNombre(persona.getNombre());
/* 234 */     personaDto.setApellido(persona.getApellido());
/* 235 */     return personaDto;
    }
  
  
    
    public Curso desactivarCurso(Curso curso) throws ParseException {
/* 241 */     curso = this.cursoRepository.findById(Integer.valueOf(curso.getId())).get();
/* 242 */     curso.setActivo(false);
/* 243 */     return (Curso)this.cursoRepository.save(curso);
    }
    
    private NotasCursoDTO EntityToNotasCursoDTO(NotasCurso entity) {
/* 247 */     if (entity == null) {
/* 248 */       NotasCursoDTO notasCursoDTO1 = new NotasCursoDTO();
/* 249 */       notasCursoDTO1.setNotaFinal(0.0D);
/* 250 */       notasCursoDTO1.setColorCelda(2);
/* 251 */       return notasCursoDTO1;
      } 
      
/* 254 */     NotasCursoDTO notasCursoDTO = new NotasCursoDTO();
      
/* 256 */     notasCursoDTO.setId(entity.getId());
/* 257 */     notasCursoDTO.setCurso(entity.getCurso());
/* 258 */     notasCursoDTO.setNotaMaestro(entity.getNotaMaestro());
/* 259 */     notasCursoDTO.setNotaAsistencia(entity.getNotaAsistencia());
/* 260 */     notasCursoDTO.setNotaPractica(entity.getNotaPractica());
/* 261 */     notasCursoDTO.setNotaExamenFinal(entity.getNotaExamenFinal());
/* 262 */     notasCursoDTO.setNotaFinal(entity.getNotaFinal());
      
/* 264 */     if (notasCursoDTO != null && notasCursoDTO.getNotaFinal() >= 4.7D)
/* 265 */     { notasCursoDTO.setColorCelda(1); }
/* 266 */     else if (notasCursoDTO != null && notasCursoDTO.getNotaFinal() < 3.0D)
/* 267 */     { notasCursoDTO.setColorCelda(2); }
/* 268 */     else { notasCursoDTO.setColorCelda(3); }
      
/* 270 */     return notasCursoDTO;
    }
  }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\service\CursoServiceImpl.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */