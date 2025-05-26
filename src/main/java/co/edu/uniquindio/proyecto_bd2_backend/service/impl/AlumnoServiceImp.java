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
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AlumnoServiceImp implements AlumnoService {

    private final EntityManager entityManager;

    @Transactional
    public String guardarPregunta(PreguntaDTO preguntaDTO) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_pregunta");


        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("v_enunciado", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_es_publica", Character.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_tipo_pregunta", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_tema", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_docente", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);


        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("v_enunciado", preguntaDTO.enunciado());
        storedProcedure.setParameter("v_es_publica", preguntaDTO.es_publica());
        storedProcedure.setParameter("v_tipo_pregunta", preguntaDTO.tipo_pregunta());
        storedProcedure.setParameter("v_id_tema", preguntaDTO.id_tema());
        storedProcedure.setParameter("v_id_docente", preguntaDTO.id_docente());

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

        return mensaje;

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
    public String crearPresentacionExamen(Integer tiempo, Character terminado, String ip_source, Date fecha_hora_presentacion, Integer id_examen, Integer id_alumno) {


        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_presentacion_examen");

        storedProcedure.registerStoredProcedureParameter("v_id_examen", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_alumno", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

        storedProcedure.setParameter("v_id_examen", id_examen);
        storedProcedure.setParameter("v_id_alumno", id_alumno);

        storedProcedure.execute();
        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");
        return mensaje;

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
