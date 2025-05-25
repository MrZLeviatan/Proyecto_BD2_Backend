package co.edu.uniquindio.proyecto_bd2_backend.Dto;

import java.util.Date;

public record ExamenDTO(


        Integer id_examen, Integer tiempo_max, Integer numero_preguntas,
        Integer porcentaje_curso, String nombre, Integer porcentaje_aprobatorio,
        Date fecha_hora_inicio, Date fecha_hora_fin, Integer num_preguntas_aleatorias,
        Integer id_tema, Integer id_docente, Integer id_grupo, String estado


) {
}
