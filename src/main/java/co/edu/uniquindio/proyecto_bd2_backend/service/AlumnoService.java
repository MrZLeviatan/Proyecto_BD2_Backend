package co.edu.uniquindio.proyecto_bd2_backend.service;

import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;

import java.util.Date;
import java.util.List;


public interface AlumnoService {

    String registrarRespuestaTexto(RespuestaEstudianteDto dto);

    Float obtenerNotaPresentacionExamen (Long id_presentacion, long id_alumno);

    String obtenerNombre(String id, String rol);

    List<PreguntaAlumnoExamenDto> crearPresentacionExamen(Integer idExamen, Integer idAlumno);

    List<CursoDTO> obtenerCursos(String id, String rol);

    List<ExamenPendienteDTO> obtenerExamenesPendiente(String id, Integer idGrupo);

    List<ExamenHechoDTO> obtenerExamenesHechos(String id, Integer idGrupo);

}
