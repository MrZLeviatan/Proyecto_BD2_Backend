package co.edu.uniquindio.proyecto_bd2_backend.controllers;


import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;
import co.edu.uniquindio.proyecto_bd2_backend.service.DocenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/docente")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DocenteController {

    private final DocenteService docenteService;


    @PostMapping("/crearRespuesta")
    public ResponseEntity<MensajeDTO<String>> crearRespuesta(@RequestBody CrearRespuestaDto crearRespuestaDto) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearRespuesta(crearRespuestaDto.descripcion(), crearRespuestaDto.esVerdadera(), crearRespuestaDto.id_pregunta())));
    }

    @PostMapping("/crearExamen")
    public ResponseEntity<MensajeDTO<String>> crearExamen(@RequestBody CrearExamenDTO examenDTO) throws ParseException {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearExamen(examenDTO)));
    }

    @PostMapping("/crearPregunta")
    public ResponseEntity<MensajeDTO<String>> crearPregunta(@RequestBody String enunciado, Character es_publica, String tipoPregunta,
                                                            Integer id_tema, Integer id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearPregunta(enunciado,es_publica,tipoPregunta,id_tema,id_docente)));
    }

    @PostMapping("/calificarExamen")
    public ResponseEntity<MensajeDTO<String>> calificarExamen(@RequestBody   Long id_presentacion_examen) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.calificarExamen(id_presentacion_examen)));
    }


    @PostMapping("/obtenerPreguntasDocente")
    public ResponseEntity<MensajeDTO<List<PreguntaBancoDTO>>> obtenerPreguntasDocente (@RequestBody  Long id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerPreguntasDocente(id_docente)));
    }

    @PostMapping("/obtenerExamenesDocente")
    public ResponseEntity<MensajeDTO<List<ExamenDTO>>> obtenerExamenesDocente (@RequestBody  Long id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerExamenesDocente(id_docente)));
    }

    @GetMapping("/nombre/{id}/{rol}")
    public ResponseEntity<MensajeDTO<String>> obtenerNombre(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerNombre(id, rol)));
    }
    @GetMapping("/cursos/{id}/{rol}")
    public ResponseEntity<MensajeDTO<List<CursoDTO>>> obtenerCursos(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerCursos(id, rol)));
    }

    @GetMapping("/temasCurso/{id_curso}")
    public ResponseEntity<MensajeDTO<List<TemasCursoDTO>>> obtenerTemasCurso(@PathVariable Integer id_curso) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerTemasCurso(id_curso)));
    }

    @GetMapping("/allTemas")
    public ResponseEntity<MensajeDTO<List<TemasCursoDTO>>> obtenerTemasDocente() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerTemasDocente()));
    }
}
