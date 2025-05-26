package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record PreguntaExamenDto(

        Integer porcentajeExamen,         // Porcentaje de la pregunta en el examen
        Integer tiempoPregunta,           // Tiempo asignado a la pregunta
        Character tieneTiempoMaximo,      // Indica si tiene tiempo máximo ('S' o 'N')
        Integer idPregunta,               // ID de la pregunta
        Integer idExamen                  // ID del examen

) {
}
