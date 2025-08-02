package com.na.backend.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.na.backend.dto.response.AdminResponseDTO;
@Component
public class AdminMapper {

    public  AdminResponseDTO listarAdmin(Object[] row) {
        AdminResponseDTO dto = new AdminResponseDTO();

        dto.setCodigoAdmin((String) row[0]);
        dto.setNombreAdmin((String) row[1]);
        dto.setApellidoAdmin((String) row[2]);
        dto.setCorreoAdmin((String) row[3]);
        dto.setTelefonoAdmin((String) row[4]);
        dto.setEdadAdmin((Integer) row[5]);
        dto.setFechaNacimientoAdmin(((LocalDate) row[6]));
        dto.setEstadoAdmin((String) row[7]);
        dto.setFechaRegistroAdmin(((LocalDate) row[8]));
        dto.setCodigoUsuarioAdmin((String) row[9]);
        dto.setUsernameAdmin((String) row[10]);
        dto.setRolAdmin((String) row[11]);

        return dto;
    }
}
