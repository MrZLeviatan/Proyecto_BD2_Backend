package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record PreguntaBancoDTO(

        Long id_pregunta,
        String enunciado,
        Character es_publica,
        String tipo_pregunta,
        Long id_tema,
        Long id_docente

) {
}
