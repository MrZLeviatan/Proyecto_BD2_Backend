package co.edu.uniquindio.proyecto_bd2_backend.service;

import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;

import java.text.ParseException;
import java.util.List;

public interface DocenteService {


    String crearExamen (CrearExamenDTO examenDTO) throws ParseException;

    String agregarPreguntaExamen (PreguntaExamenDto preguntaExamenDto);

    String crearPregunta (String enunciado, Character es_publica, String tipoPregunta,
                          Integer id_tema, Integer id_docente);

    String crearRespuesta (String descripcion, Character esVerdadera, Long id_pregunta );

    String calificarExamen (Long id_presentacion_examen , Integer calificacion);

    List <PreguntaBancoDTO> obtenerPreguntasDocente(Long id_docente);

    List <ExamenDTO> obtenerExamenesDocente (Long id_docente);

    String obtenerNombre(String id, String rol);

    List<CursoDTO> obtenerCursos(String id, String rol);

    List<TemasCursoDTO> obtenerTemasCurso(Integer id_curso);

    List<TemasCursoDTO> obtenerTemasDocente();

    List<PreguntaBancoDTO> obtenerBancoPreguntas (Long id_tema);


}
