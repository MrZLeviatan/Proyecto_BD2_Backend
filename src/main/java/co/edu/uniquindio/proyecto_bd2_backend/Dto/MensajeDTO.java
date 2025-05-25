package co.edu.uniquindio.proyecto_bd2_backend.Dto;

public record MensajeDTO<T>(
        Boolean error,
        String mensajeError,
        T respuesta
) {
}
