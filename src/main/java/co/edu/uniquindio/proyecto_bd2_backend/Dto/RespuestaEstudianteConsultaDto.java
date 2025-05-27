package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record RespuestaEstudianteConsultaDto(

        Integer idPresentacionExamen,
        Integer idPregunta,
        String enunciadoPregunta,
        String respuestaEstudiante
) {
}
