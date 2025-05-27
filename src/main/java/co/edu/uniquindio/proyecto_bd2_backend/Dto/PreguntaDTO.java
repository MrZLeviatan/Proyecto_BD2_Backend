package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record PreguntaDTO(
        String enunciado,
        Character es_publica,
        String tipo_pregunta,
        Integer id_tema,
        Integer id_docente
) {
}
