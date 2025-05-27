package co.edu.uniquindio.proyecto_bd2_backend.controllers;

import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;
import co.edu.uniquindio.proyecto_bd2_backend.service.AlumnoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AlumnoController {

    private final AlumnoService alumnoService;


    @PostMapping("/registrar-respuesta")
    public ResponseEntity<MensajeDTO<String>> registrarRespuesta(
            @RequestBody RespuestaEstudianteDto dto) {
        String mensaje = alumnoService.registrarRespuestaTexto(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "", mensaje));
    }


    @PostMapping("/obtener-nota")
    public ResponseEntity<MensajeDTO<Float>> obtenerNota(@RequestBody ObtenerNotaDto dto ) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", alumnoService.obtenerNotaPresentacionExamen(dto.idPresentacion(), dto.idAlumno())));
    }


    @PostMapping("/presentar-examen")
    public ResponseEntity<MensajeDTO<List<PreguntaAlumnoExamenDto>>> presentarExamen(@RequestBody PresentacionExamenDTO p) {

        List<PreguntaAlumnoExamenDto> preguntas = alumnoService.crearPresentacionExamen(
                p.idExamen(), p.idAlumno());

        return ResponseEntity.ok().body(
                new MensajeDTO<>(false, "Examen presentado correctamente", preguntas)
        );
    }


    @GetMapping("/nombre/{id}/{rol}")
    public ResponseEntity<MensajeDTO<String>> obtenerNombre(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", alumnoService.obtenerNombre(id, rol)));
    }

    @GetMapping("/cursos/{id}/{rol}")
    public ResponseEntity<MensajeDTO<List<CursoDTO>>> obtenerCursos(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", alumnoService.obtenerCursos(id, rol)));
    }


    @GetMapping("/examenes-pendientes/{id}/{id_grupo}")
    public ResponseEntity<MensajeDTO<List<ExamenPendienteDTO>>> obtenerExamenesPendientes(@PathVariable String id, @PathVariable Integer id_grupo) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", alumnoService.obtenerExamenesPendiente(id, id_grupo)));
    }

    @GetMapping("/examenes-hechos/{id}/{id_grupo}")
    public ResponseEntity<MensajeDTO<List<ExamenHechoDTO>>> obtenerExamenesHechos(@PathVariable String id, @PathVariable Integer id_grupo) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", alumnoService.obtenerExamenesHechos(id, id_grupo)));
    }

}
