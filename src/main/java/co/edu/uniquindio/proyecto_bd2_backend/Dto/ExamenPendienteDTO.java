package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record ExamenPendienteDTO(
        Integer id_examen,
        String tiempo_max,
        Integer numero_preguntas,
        Integer porcentaje_aprobatorio,
        String nombre,
        Integer porcentaje_curso,
        String fecha_hora_inicio,
        String fecha_hora_fin,
        String tema
) {
}
