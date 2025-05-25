package co.edu.uniquindio.proyecto_bd2_backend.Dto;

import java.util.Date;

public record PresentacionExamenDTO(


        Integer tiempo,
        Character terminado,
        String ipSource,
        Date fechaHoraPresentacion,
        Integer idExamen,
        Integer idAlumno

) {
}
