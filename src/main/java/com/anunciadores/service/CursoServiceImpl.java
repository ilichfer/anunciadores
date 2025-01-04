package com.anunciadores.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.anunciadores.dto.NotasCursoDTO;
import com.anunciadores.dto.ServicioResponseDto;
import com.anunciadores.mapper.mapperNotas;
import com.anunciadores.mapper.mapperParametros;
import com.anunciadores.mapper.mapperPersona;
import com.anunciadores.model.NotasCurso;
import com.anunciadores.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.anunciadores.service.interfaces.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

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
	
	List<Curso> listaCursos = new ArrayList<>();

	@Override
	public List<Curso> findAll() {
		List<Curso> listaCursosActivos = new ArrayList<>();

		listaCursos = cursoRepository.findAll();

		for (Curso curso : listaCursos) {
			if (curso.isActivo()) {
				listaCursosActivos.add(curso);
			}
		}

		return listaCursosActivos;

	}

	@Override
	public List<Curso> findAllActive() {
		List<Curso> listaCursosActivos = new ArrayList<>();

		listaCursos = cursoRepository.findByActivo(true);

		for (Curso curso : listaCursos) {
			if (curso.isActivo()) {
				listaCursosActivos.add(curso);
			}
		}

		return listaCursosActivos;

	}

	@Override
	public List<Curso> findAllActiveByPerson(int idPersona) {
		List<Curso> listaCursosActivos = new ArrayList<>();

		listaCursos = cursoRepository.findByActivos(idPersona);

		for (Curso curso : listaCursos) {
			if (curso.isActivo()) {
				listaCursosActivos.add(curso);
			}
		}

		return listaCursosActivos;
	}

	@Override
	public Curso save(CursoDto curso) throws java.text.ParseException {
		Curso cursoSave = new Curso();

		List<Curso> list = cursoRepository.findTopByOrderByIdDesc();
		cursoSave.setFechaFin(curso.getFechaFin());
		cursoSave.setFechaInicio(curso.getFechaInicio());
		cursoSave.setId(curso.getId());
		cursoSave.setNombreCurso(curso.getNombreCurso());
		cursoSave.setValorTotal(curso.getValorTotal());
		cursoSave.setComentario(curso.getCheck() !=null? true: false);
		cursoSave.setActivo(true);
		cursoSave.setProfesor(personaRepo.findById(curso.getProfesor()).get());
		return cursoRepository.save(cursoSave);
	}

	@Override
	public Curso findCursoById(Integer id) {

		Optional<Curso> curso = cursoRepository.findById(id);

		return curso.get();
	}

	@Override
	public Curso delete(Curso curso) {

		cursoRepository.deleteById(curso.getId());
		
		return curso;
	}

	@Override
	public NotasCurso findNotasByCurso(int idCurso,int idPersona) {
		return notasCursoRepo.findNotasByCurso(idCurso,idPersona);
	}

	@Override
	public List<NotasCurso> findHistoricoNotas(int idPersona) {
		Optional<List<NotasCurso>> lista= notasCursoRepo.findHistoricoNotas(idPersona);
		if (lista.isPresent()){
			return lista.get();
		}
		List<NotasCurso> listaVacia = new ArrayList<>();
		return listaVacia;
	}

	@Override
	public NotasCurso saveNotasCurso(NotasCurso notas) throws ParseException {
		double notaM = notas.getNotaMaestro()*0.3;
		double notaA = notas.getNotaAsistencia()*0.2;
		double notaP = notas.getNotaPractica()*0.2;
		double notaEF = notas.getNotaExamenFinal()*0.3;
		double notafinal = notaM + notaA + notaP +notaEF;
		notas.setNotaFinal(notafinal);

		return notasCursoRepo.save(notas);
	}

	@Override
    public Date ParseFecha(String fecha) throws java.text.ParseException
    {
    	fecha = fecha.replace("-", "/");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        fechaDate = formato.parse(fecha);
        return fechaDate;
    }

	@Override
	public String formatFecha(String fecha) throws java.text.ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
//		fecha = fecha.replace("-", "/");
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        fechaDate = dateFormat.parse(fecha);
		
		Date date = new Date();
		String dateToStr = dateFormat.format(fechaDate);
		System.out.println("fechaInicio  formateada " + dateToStr);
		return dateToStr;
	}

	@Override
	public List<PersonaDto> buscarNotasXPersonas(int idCurso,List<Persona> estudiantes) {
		List<PersonaDto> personasDto = mapperPersona.listEntityToConsolidacionDto(estudiantes);
		personasDto.forEach(p-> p.setNotas(EntityToNotasCursoDTO(findNotasByCurso(idCurso,p.getId()))));
		if(!personasDto.isEmpty()){
			personasDto.sort(Comparator.comparing(p -> p.getNotas().getNotaFinal()));
		}
		return personasDto;
	}

	@Override
	public List<Curso> findCursosByIdPersona(Integer idPersona) {
		return cursosRepository.cursosByIdPersona(idPersona);
	}
	
	@Override
	public List<CursoDto> findCursosDtoByIdPersona(Integer idPersona) {
//		return cursosRepository.cursosByIdPersona(id);
		List<CursoDto> cursosDto = new ArrayList<CursoDto>();
		List<Curso> listaCursos= cursoRepository.cursosByIdPersona(idPersona);
		List<Curso> listaCursosDictados = cursoRepository.cursosByIdProfesor(idPersona);
		if (listaCursosDictados != null && !listaCursosDictados.isEmpty())
			listaCursosDictados.forEach(p -> listaCursos.add(p));

		listaCursos.forEach(c -> cursosDto.add(contruirCursosConsolidados(c, idPersona)));
		return cursosDto;
	}
	
	private CursoDto contruirCursosConsolidados(Curso curso,Integer idPersona){
		CursoDto cursoDto = new CursoDto();
		
		cursoDto.setId(curso.getId());
		cursoDto.setIdPago(curso.getIdPago());
		cursoDto.setFechaInicio(curso.getFechaInicio());
		cursoDto.setFechaFin(curso.getFechaFin());
		cursoDto.setNombreCurso(curso.getNombreCurso());
		cursoDto.setValorTotal(curso.getValorTotal());
		cursoDto.setProfesorDto(curso.getProfesor());
		boolean activarNotas = false;
		if (curso.getProfesor().getId() == idPersona) {
			activarNotas= true;
		}
		cursoDto.setNotas(activarNotas);
		if (cursoDto.getNombreCurso().contains("padres")) {
			cursoDto.setPersonaAConsolidar(buscarConsolidados(idPersona));
		}
		return cursoDto;
	}
	
	private PersonaDto buscarConsolidados(Integer idPersona){
		PersonaDto personaDto = new PersonaDto();
		Persona persona = consolidacionDao.listarPersonaConsolidacion(idPersona);
		personaDto.setId(persona.getId());
		personaDto.setNombre(persona.getNombre());
		personaDto.setApellido(persona.getApellido());
		return personaDto;
	}

	@Override
	public Curso desactivarCurso(Curso curso) throws ParseException {
		
		curso = cursoRepository.findById(curso.getId()).get();
		curso.setActivo(false);
		return cursoRepository.save(curso);
	}

	private NotasCursoDTO EntityToNotasCursoDTO(NotasCurso entity) {
		if ( entity == null ) {
			NotasCursoDTO notasCursoDTO = new NotasCursoDTO();
			notasCursoDTO.setNotaFinal(0.0);
			notasCursoDTO.setColorCelda(2);
			return notasCursoDTO;
		}

		NotasCursoDTO notasCursoDTO = new NotasCursoDTO();

		notasCursoDTO.setId( entity.getId() );
		notasCursoDTO.setCurso( entity.getCurso() );
		notasCursoDTO.setNotaMaestro( entity.getNotaMaestro() );
		notasCursoDTO.setNotaAsistencia( entity.getNotaAsistencia() );
		notasCursoDTO.setNotaPractica( entity.getNotaPractica() );
		notasCursoDTO.setNotaExamenFinal( entity.getNotaExamenFinal() );
		notasCursoDTO.setNotaFinal( entity.getNotaFinal() );

		if (notasCursoDTO != null && notasCursoDTO.getNotaFinal() >= 4.7) {
			notasCursoDTO.setColorCelda(1);
		}else if (notasCursoDTO != null && notasCursoDTO.getNotaFinal() < 3){
			notasCursoDTO.setColorCelda(2);
		}else{notasCursoDTO.setColorCelda(3);}

		return notasCursoDTO;
	}

}
