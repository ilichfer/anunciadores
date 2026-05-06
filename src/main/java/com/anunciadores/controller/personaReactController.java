package com.anunciadores.controller;

import com.anunciadores.auth.dto.reporRequest;
import com.anunciadores.auth.dto.updateServiceRequest;
import com.anunciadores.dto.*;
import com.anunciadores.model.*;
import com.anunciadores.repository.*;
import com.anunciadores.service.UsuarioService;
import com.anunciadores.service.interfaces.*;
import com.anunciadores.util.UtilDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/api")
public class personaReactController {

    private Logger LOGGER = LoggerFactory.getLogger(personaReactController.class);

    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IMenuService menuService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private IBibliaService bibliaService;

    @Autowired
    private IPersonaRepo personaRepoImpl;

    @Autowired
    private ConsolidacionRepoImpl consolidacionDao;

    @Autowired
    private IPagoService pagoService;

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IMInisteryService ministeryService;

    @Autowired
    private IRolesRepo rolesPersonaRepo;

    @Autowired
    private IPermisosRepo permisosMenuRepo;
    @Autowired
    private IParamMenuRepo paramMenuRepo;

    @Autowired
    private RolesRepoImpl rolesDao;

    @Autowired
    private UtilDate utilDate;

    @Autowired
    private IR2UploadService r2UploadService;

    @Autowired
    private ITdcService tdcService;

    List<Persona> personasList;
    List<PersonaDto> personasListDto;


    @GetMapping("/users")
    public ResponseEntity<List<PersonaReactDto>> users() throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<List<PersonaReactDto>> rp = null;
        List<PersonaReactDto> listResult = personaService.findAllUsers();
        if (listResult.size() > 0) {
            rp = new ResponseEntity<>(listResult, null, HttpStatus.ACCEPTED);
        } else {
            rp = new ResponseEntity<>(listResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return rp;
    }

    @GetMapping("/events")
    public ResponseEntity<ProgramationDto> events() throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<ProgramationDto> rp = null;
        ProgramationDto result = servicioService.findNextServices(utilDate.cargarfechaActualBogotaDate());

        if (result != null && result.getMinistries() != null && result.getMinistries().size() > 0) {
            rp = new ResponseEntity<>(result, null, HttpStatus.ACCEPTED);
        } else {
            rp = new ResponseEntity<>(result, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rp;
    }

    @GetMapping("/findprog")
    public ResponseEntity<ProgramationDto> findprog(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<ProgramationDto> rp = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaD = sdf.parse(date);
        ProgramationDto result = servicioService.findServices(fechaD);

        if (result != null && result.getMinistries() != null && result.getMinistries().size() > 0) {
            rp = new ResponseEntity<>(result, null, HttpStatus.ACCEPTED);
        } else {

            rp = new ResponseEntity<>(result, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rp;
    }


    @GetMapping("/ministries")
    public ResponseEntity<List<Ministry>> ministries() throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<List<Ministry>> rp = null;
        List<Ministry> listServResult = ministeryService.getAllministriesWithPositions();

        if (listServResult.size() > 0) {
            rp = new ResponseEntity<>(listServResult, null, HttpStatus.ACCEPTED);
        } else {
            rp = new ResponseEntity<>(listServResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rp;
    }


    @GetMapping("/ministries/{idMinistry}")
    public ResponseEntity<Ministry> getMinistries(@PathVariable Integer idMinistry) throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<Ministry> rp = null;
        Ministry minResult = ministeryService.getMinistryWithPositions(idMinistry);

        if (minResult != null && minResult.getPositions() != null && minResult.getPositions().size() > 0) {
            rp = new ResponseEntity<>(minResult, null, HttpStatus.ACCEPTED);
        } else {
            rp = new ResponseEntity<>(minResult, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rp;
    }

    // GET /api/ministries/{id}/personas
    // Devuelve las personas que pertenecen a un ministerio
    @GetMapping("/ministries/{id}/personas")
    public ResponseEntity<?> getPersonasByMinisterio(@PathVariable Integer id) {
        try {
            List<PersonaDto> personas = servicioService.findPersonaByidMnisterio(id);
            return ResponseEntity.ok(personas);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al obtener personas del ministerio: " + e.getMessage());
        }
    }

    @PostMapping("/ministries/addposition")
    public ResponseEntity<?> addPositionToMinistrie(
            @RequestBody PosicionDto posicionDto) {
        try {
            servicioService.savePosicion(posicionDto);return ResponseEntity.noContent().build(); // 204 ✅
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason()); // 404 ❌
        }
    }

    // GET /api/user
    // Lee el token del header Authorization y devuelve el usuario logueado
    @GetMapping("/user")
    public ResponseEntity<?> getUsuarioLogueado(HttpServletRequest request) {
        try {
            // Extraer token del header: "Bearer eyJ..."
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token no proporcionado");
            }
            String token = authHeader.substring(7);

            UserResponseDto user = personaService.getUsuarioDesdeToken(token);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
    }

    // GET /api/schedule/persona/{id}
    // Devuelve la programación de una persona por su id
    @GetMapping("/schedule/persona/{id}")
    public ResponseEntity<?> getProgramacionPersona(@PathVariable Integer id) {
        try {
            List<ServicioResponseDto> listServ = servicioService.buscarProgramacionMes(id);
            return ResponseEntity.ok(listServ);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al obtener programación: " + e.getMessage());
        }
    }

    @DeleteMapping("/ministeries/{idMinisterio}/personas/{idPersona}")
    public ResponseEntity<?> deletePersonaFromMinisterie(
            @PathVariable int idMinisterio,
            @PathVariable int idPersona) {
        ResponseEntity<List<PersonaDto>> rp = null;


        personaService.eliminarPersonaMinisterio(idPersona, idMinisterio);
        List<PersonaDto> personasList = servicioService.findPersonaByidMnisterio(idMinisterio);

        if (personasList.size() > 0) {
            rp = new ResponseEntity<>(personasList, null, HttpStatus.ACCEPTED);
        } else {
            rp = new ResponseEntity<>(personasList, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rp;
    }

    @PostMapping("/ministeries/addperson")
    public ResponseEntity<?> addPersonaToMinisterie(
            @RequestBody AddPersonaRequest request) {
        try {
            ministeryService.agregarPersonasAMinisterio(request.getIdPersona(), request.getIdMinisterio());
            return ResponseEntity.noContent().build(); // 204 ✅
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason()); // 404 ❌
        }
    }

    //updateprog
    @PostMapping("/updateprog")
    public ResponseEntity<?> updateprog(@RequestBody updateServiceRequest request) {
        try {
            System.out.println("entro ");
            ministeryService.updateService(request);
            return ResponseEntity.noContent().build(); // 204 ✅
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason()); // 404 ❌
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("idPersona") Integer idPersona) {
        try {
            // 1. Subir imagen a R2 y obtener URL
            String imageUrl = r2UploadService.uploadImage(file);

            // 2. Guardar URL en BD (ya no el blob)
            ImagenDiariaDto imagen = new ImagenDiariaDto();
            imagen.setIdPersona(idPersona);
            imagen.setFechaCreacion(LocalDate.now());
            imagen.setTdc(imageUrl); // ahora tdc guarda la URL como String

            tdcService.saveTcdImage(imageUrl, idPersona);

            return ResponseEntity.ok(imageUrl);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen");
        }
    }

    @PostMapping("/scheduleByDate")
    public ResponseEntity<?> scheduleByDate(
            @RequestBody reporRequest request) throws ParseException {
        ResponseEntity<List<TdcReporteDto>> rp = null;
        if (request.getFechaInicio() != null && request.getFechaFin() != null) {
            System.out.println("Fecha Inicio: " + request.getFechaInicio());
            List<TdcReporteDto> listaTdc = tdcService.findAllBetweenDates(utilDate.convertStringToDate(request.getFechaInicio()), utilDate.convertStringToDate(request.getFechaFin()));

            if (listaTdc.size() > 0) {
                rp = new ResponseEntity<>(listaTdc, null, HttpStatus.ACCEPTED);
            } else {
                rp = new ResponseEntity<>(listaTdc, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return rp;
    }


    @GetMapping("/tdcbyPerson/{idPersona}")
    public ResponseEntity<?> tdcbyPerson(
            @PathVariable Integer idPersona) throws ParseException {
        ResponseEntity<TdcReporteDto> rp = null;
        if (idPersona != null) {
            TdcReporteDto Tdc = tdcService.findAllBetweenDatesAndPerson(idPersona);

            if (Tdc != null && Tdc.getIdPersona() != 0) {
                rp = new ResponseEntity<>(Tdc, null, HttpStatus.ACCEPTED);
            } else {
                rp = new ResponseEntity<>(Tdc, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return rp;
    }

    @PostMapping("/savecordinador")
    public ResponseEntity<?> saveCordinador(@RequestBody CoordinadorDTO cordinador) {
        ResponseEntity<?> rp;

        try {
            Coordinador corSave = servicioService.findCoordinadorByFecha(utilDate.convertStringToDate(cordinador.getFechaString()));
            if (corSave == null) {
                Boolean response = servicioService.saveCoordinado(cordinador);
                if (response) {
                    rp = new ResponseEntity<>(Boolean.TRUE, null, HttpStatus.ACCEPTED);
                } else {
                    rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error al guardar cordinador");
                }
            } else {
                rp = ResponseEntity
                        .status(HttpStatus.CONFLICT) // Código 409
                        .body(new ApiResponse(false, "Ya existe una programación para esta fecha y ministerio."));
            }
        } catch (Exception e) {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar cordinador");
        }
        return rp;
    }

    @GetMapping("/findSchedule")
    public ResponseEntity<?>  consultarMiProgramacion(@RequestParam String fecha, @RequestParam int idMinisterio) throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<?> rp;

        Date fechaD = utilDate.convertStringToDate(fecha);
        List<ServicioResponseDto> listProgramacionMinisterio = servicioService.findProgramacionByDateAndMinisterio(fechaD,idMinisterio);
        //List<ServicioResponseDto> listProgramacion = servicioService.findProgramacionByDate(Date.valueOf(LocalDate.now()));
        if(listProgramacionMinisterio.size()>0) {

            rp = new ResponseEntity<>(listProgramacionMinisterio, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar cordinador");
        }
        return rp;
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePass(@RequestBody Persona persona)
            throws JsonMappingException, JsonProcessingException {
        ResponseEntity<?> rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar contraseña");
        PersonaDto per = personaService.buscarByDocumento(persona.getDocumento());
        if (per.getEmail() != null) {
            per.setPassword(persona.getPassword());
            persona = personaService.personaDtoToEntity(per);
            persona.setEstado(true);
            Persona personaSave = personaService.savePassword(persona);
            if(personaSave != null) {
                rp = new ResponseEntity<>(personaSave, null, HttpStatus.ACCEPTED);
            } else {
                rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al actualizar contraseña");
            }           
       }
        return rp;
    }

    @PostMapping("/saveInformCoordinator")
        public ResponseEntity<?> saveInformCoordinator(@RequestBody CoordinadorDTO cordinador) throws ParseException {

        ResponseEntity<?> rp;

        try {
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            Persona per = personaService.findPersonaById(cordinador.getPersona().getId());
            cordinador.setPersona(per);
            //cordinador.setFechaServicio(dt1.parse(fechaServCoord));

            Boolean save =    servicioService.saveCoordinadorEntity(cordinador);

            if (save) {
                rp = ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("informe guardado exitosamente");
            }else {
                rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al guardar informe del coordinador");
            }
        } catch (Exception e) {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar informe del coordinador");
        }
        return rp;
    }

    @GetMapping("/findBirthday")
    public ResponseEntity<?>  findBirthday() throws JsonMappingException, JsonProcessingException, ParseException {
        ResponseEntity<?> rp;
        List<PersonaDto> listadoCumpleanosMes = personaService.findBirthdayByMonth();
        List<PersonaDto> listadoCumpleanosDiario =	personaService.getBirthDay(listadoCumpleanosMes);
        if(listadoCumpleanosDiario.size()>0) {
            rp = new ResponseEntity<>(listadoCumpleanosDiario, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("no hay cumpleaños hoy");
        }
        return rp;
    }

    @GetMapping("/reporteTdcIndividual")
    public ResponseEntity<?> reporteTdcPersona(@RequestParam int idPersona) throws ParseException {
        ResponseEntity<?> rp;
        List<TdcDto> listTCD= tdcService.findAlltcdByPersona(idPersona);
        //Persona per= personaService.findPersonaById(idPersona);

        if(listTCD.size()>0) {
            rp = new ResponseEntity<>(listTCD, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("no hay reporte");
        }
        return rp;
    }

    @GetMapping("/findBirthDaysMOnth")
    public ResponseEntity<?> findBirthDaysMOnth() throws ParseException {
        ResponseEntity<?> rp;
        List<PersonaDto> listadoCumpleanosMes = personaService.findBirthdayByMonth();

        if(listadoCumpleanosMes.size()>0) {
            rp = new ResponseEntity<>(listadoCumpleanosMes, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("no hay cumpleaños este mes");
        }
        return rp;
    }

    @GetMapping("/personas/{idpersona}/toggle-active")
    public ResponseEntity<?>  toggleActive(@PathVariable Integer idpersona,
                   @RequestParam boolean active) {
        ResponseEntity<?> rp;
        Persona retorno = personaService.toggleActive(idpersona,active);
        if(retorno.getId() != null) {
            rp = new ResponseEntity<>(retorno, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("so se puedo actualizar el estado del usuario");
        }
        return rp;
    }


    @GetMapping("/findTcdPerson")
    public ResponseEntity<?> findTcdPerson(@RequestParam Integer idPersona,
                                                @RequestParam String fechaInicio,
                                                @RequestParam String fechaFin) throws ParseException {
        ResponseEntity<?> rp;
        List<TdcDto> listTCD = tdcService.findAllBetweenDatesByPersona(utilDate.convertStringToDate(fechaInicio), utilDate.convertStringToDate(fechaFin), idPersona);

        if(listTCD!= null && listTCD.size()>0) {
            rp = new ResponseEntity<>(listTCD, null, HttpStatus.ACCEPTED);
        } else {
            rp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("so se encontro tcd del usuario");
        }
        return rp;
    }

    @PostMapping("/saveService")
    public ResponseEntity<?> saveService(@RequestBody List<ServiceDTO> request) {
        ResponseEntity<?> rp;
        try {
            List<Persona> listamultiple = servicioService.saveProgram(request);

                rp = new ResponseEntity<>(listamultiple, null, HttpStatus.ACCEPTED);

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getReason()); // 404 ❌
        }
        return rp;
    }
}





