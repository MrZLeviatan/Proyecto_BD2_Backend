package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record CrearPreguntaDto(

        String enunciado,
        Character esPublica,
        String tipoPregunta,
         Integer idTema,
         Integer idDocente

) {
}
