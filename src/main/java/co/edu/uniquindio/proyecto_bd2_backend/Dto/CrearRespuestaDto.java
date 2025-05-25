package co.edu.uniquindio.proyecto_bd2_backend.Dto;


import lombok.AllArgsConstructor;

// DTO para crear una respuesta
public record CrearRespuestaDto(
    String descripcion,
    Character esVerdadera,
    Long id_pregunta

){}

