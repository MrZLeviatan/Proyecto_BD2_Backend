package co.edu.uniquindio.proyecto_bd2_backend.Dto;

import java.util.List;

public record PreguntaAlumnoExamenDto(
       Integer idPregunta,
       String enunciado,
       String tipoPregunta,
       List<RespuestaDto> respuestas



) {
}
