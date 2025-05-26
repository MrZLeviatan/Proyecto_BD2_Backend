package co.edu.uniquindio.proyecto_bd2_backend.service;

import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;

import java.util.Date;
import java.util.List;


public interface AlumnoService {

    String guardarPregunta(PreguntaDTO preguntaDTO);

    Float obtenerNotaPresentacionExamen (Long id_presentacion, long id_alumno);

    String obtenerNombre(String id, String rol);

    String crearPresentacionExamen (Integer tiempo, Character terminado, String ip_source,
                                    Date fecha_hora_presentacion, Integer id_examen, Integer id_alumno );

    List<CursoDTO> obtenerCursos(String id, String rol);

    List<ExamenPendienteDTO> obtenerExamenesPendiente(String id, Integer idGrupo);

    List<ExamenHechoDTO> obtenerExamenesHechos(String id, Integer idGrupo);

}
