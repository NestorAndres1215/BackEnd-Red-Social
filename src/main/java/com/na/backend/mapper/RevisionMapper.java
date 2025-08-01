package com.na.backend.mapper;

import com.na.backend.dto.response.RevisionResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class RevisionMapper {


    public RevisionResponseDTO listarRevisionCorreo(Object[] fila) {

        RevisionResponseDTO dto = new RevisionResponseDTO();

        dto.setCodigoRevision((String) fila[0]);
        dto.setFechaRevision((LocalDate) fila[1]);
        dto.setEstadoRevision((String) fila[2]);
        dto.setObservacionRevision((String) fila[3]);

        dto.setCodigoSuspension((String) fila[4]);
        dto.setFechaSuspension((LocalDate) fila[5]);
        dto.setFechaExpiracion((LocalDate) fila[6]);
        dto.setMotivoSuspension((String) fila[7]);
        dto.setHoraSuspension((LocalTime) fila[8]);
        dto.setDetalleSuspension((String) fila[9]);

        dto.setCodigoUsuario((String) fila[10]);
        dto.setUsernameUsuario((String) fila[11]);
        dto.setCorreoUsuario((String) fila[12]);

        dto.setCodigoModerador((String) fila[13]);
        dto.setNombreModerador((String) fila[14]);
        dto.setApellidoModerador((String) fila[15]);
        dto.setCorreoModerador((String) fila[16]);

        return dto;

    }
}
