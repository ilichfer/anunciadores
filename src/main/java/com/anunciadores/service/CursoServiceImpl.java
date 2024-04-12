package com.anunciadores.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.CursoDto;
import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Curso;
import com.anunciadores.model.Persona;
import com.anunciadores.repository.ConsolidacionRepoImpl;
import com.anunciadores.repository.CursosRepoImpl;
import com.anunciadores.repository.ICursoRepo;
import com.anunciadores.service.interfaces.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoRepo cursoRepository;
	
	@Autowired
	private CursosRepoImpl cursosRepository;
	
	@Autowired
	private ConsolidacionRepoImpl consolidacionDao;
	
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
	public List<Curso> findCursosByIdPersona(Integer idPersona) {
		return cursosRepository.cursosByIdPersona(idPersona);
	}
	
	@Override
	public List<CursoDto> findCursosDtoByIdPersona(Integer idPersona) {
//		return cursosRepository.cursosByIdPersona(id);
		List<CursoDto> cursosDto = new ArrayList<CursoDto>();
		List<Curso> listaCursos= cursosRepository.cursosByIdPersona(idPersona);		
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

}
