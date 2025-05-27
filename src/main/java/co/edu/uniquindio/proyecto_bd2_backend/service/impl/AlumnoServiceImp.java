package co.edu.uniquindio.proyecto_bd2_backend.service.impl;

import co.edu.uniquindio.proyecto_bd2_backend.service.AlumnoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.proyecto_bd2_backend.Dto.*;


import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class AlumnoServiceImp implements AlumnoService {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public String registrarRespuestaTexto(RespuestaEstudianteDto dto) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("REGISTRAR_RESPUESTA_ESTUDIANTE");

        query.registerStoredProcedureParameter("v_id_presentacion_examen", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_id_pregunta", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_enunciado_respuesta", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

        query.setParameter("v_id_presentacion_examen", dto.idPresentacionExamen());
        query.setParameter("v_id_pregunta", dto.idPregunta());
        query.setParameter("v_enunciado_respuesta", dto.enunciadoRespuesta());

        query.execute();

        return (String) query.getOutputParameterValue("v_mensaje");
    }


    @Transactional
    @Override
    public Float obtenerNotaPresentacionExamen(Long id_presentacion_examen , long id_alumno) {
            // Crear una consulta para el procedimiento almacenado
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("obtener_nota");

            // Registrar los parámetros de entrada y salida del procedimiento almacenado
            storedProcedure.registerStoredProcedureParameter("p_id_presentacion", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_id_alumno", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nota", Float.class, ParameterMode.OUT);

            // Establecer los valores de los parámetros de entrada
            storedProcedure.setParameter("p_id_presentacion", id_presentacion_examen);
            storedProcedure.setParameter("p_id_alumno", id_alumno);


        // Ejecutar el procedimiento almacenado
            storedProcedure.execute();

            // Obtener el valor del parámetro de salida
            Float nota = (Float) storedProcedure.getOutputParameterValue("p_nota");

            // Retornar la nota
            return nota != null ? nota : 0.0f;
        }

    @Transactional
    @Override
    public List<PreguntaAlumnoExamenDto> crearPresentacionExamen(Integer idExamen, Integer idAlumno) {

        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("crear_presentacion_examen");

        storedProcedure.registerStoredProcedureParameter("v_id_examen", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_alumno", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("out_cursor", void.class, ParameterMode.REF_CURSOR);

        storedProcedure.setParameter("v_id_examen", idExamen);
        storedProcedure.setParameter("v_id_alumno", idAlumno);

        storedProcedure.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> results = storedProcedure.getResultList();

        // Mapear preguntas y sus respuestas
        Map<Integer, PreguntaAlumnoExamenDto> preguntasMap = new HashMap<>();
        Map<Integer, List<RespuestaDto>> respuestasPorPregunta = new HashMap<>();

        for (Object[] row : results) {
            Integer idPregunta = ((BigDecimal) row[0]).intValue();
            String enunciado = (String) row[1];
            String tipoPregunta = (String) row[2];

            BigDecimal idRespuestaRaw = (BigDecimal) row[3];
            Integer idRespuesta = idRespuestaRaw != null ? idRespuestaRaw.intValue() : null;
            String descripcion = (String) row[4];
            String esCorrecta = String.valueOf(row[5]);

            // Agregar respuesta si existe
            if (idRespuesta != null) {
                RespuestaDto respuesta = new RespuestaDto(idRespuesta, descripcion, esCorrecta);
                respuestasPorPregunta.computeIfAbsent(idPregunta, k -> new ArrayList<>()).add(respuesta);
            }

            // Asegurar que la pregunta esté en el mapa
            preguntasMap.putIfAbsent(idPregunta,
                    new PreguntaAlumnoExamenDto(idPregunta, enunciado, tipoPregunta, new ArrayList<>()));
        }

        // Agregar las respuestas a cada pregunta
        List<PreguntaAlumnoExamenDto> resultado = new ArrayList<>();
        for (Map.Entry<Integer, PreguntaAlumnoExamenDto> entry : preguntasMap.entrySet()) {
            Integer idPregunta = entry.getKey();
            PreguntaAlumnoExamenDto pregunta = entry.getValue();
            List<RespuestaDto> respuestas = respuestasPorPregunta.getOrDefault(idPregunta, List.of());

            // Re-crear record con respuestas actualizadas
            PreguntaAlumnoExamenDto preguntaFinal = new PreguntaAlumnoExamenDto(
                    pregunta.idPregunta(), pregunta.enunciado(), pregunta.tipoPregunta(), respuestas
            );
            resultado.add(preguntaFinal);
        }

        return resultado;
    }



    @Override
    public String obtenerNombre(String id, String rol) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_nombre_usuario");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_usuario", id);
        storedProcedure.setParameter("rol", "alumno");

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String nombre = (String) storedProcedure.getOutputParameterValue("res");

        return nombre;

    }



    @Override
    public List<CursoDTO> obtenerCursos(String id, String rol) {

        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_grupos_por_usuario");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_usuario", id);
        storedProcedure.setParameter("rol", rol);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String json1 = (String) storedProcedure.getOutputParameterValue("res");

        Gson gson = new Gson();
        Type personListType = new TypeToken<List<CursoDTO>>() {}.getType();

        return gson.fromJson(json1, personListType);
    }

    @Override
    public List<ExamenPendienteDTO> obtenerExamenesPendiente(String id, Integer idGrupo) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_examenes_grupo_pendientes_por_alumno");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_alumno", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_id_grupo", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_alumno", Integer.parseInt(id));
        storedProcedure.setParameter("p_id_grupo", idGrupo);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String json1 = (String) storedProcedure.getOutputParameterValue("res");
        Gson gson = new Gson();
        Type personListType = new TypeToken<List<ExamenPendienteDTO>>() {}.getType();
        return gson.fromJson(json1, personListType);
    }

    @Override
    public List<ExamenHechoDTO> obtenerExamenesHechos(String id, Integer idGrupo) {
        // Crear una consulta para el procedimiento almacenado

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_PRESENTACION_EXAMEN_ALUMNO_GRUPO");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_alumno", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_id_grupo", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_alumno", Integer.parseInt(id));
        storedProcedure.setParameter("p_id_grupo", idGrupo);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String json1 = (String) storedProcedure.getOutputParameterValue("res");
        Gson gson = new Gson();
        Type personListType = new TypeToken<List<ExamenHechoDTO>>() {}.getType();
        return gson.fromJson(json1, personListType);
    }



}
