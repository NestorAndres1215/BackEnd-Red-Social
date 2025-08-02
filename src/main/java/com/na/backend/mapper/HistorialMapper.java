package com.na.backend.mapper;

import com.na.backend.dto.response.HistorialResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;
@Component
public class HistorialMapper {


    public HistorialResponseDTO listarHistorial(Object[] row) {
        HistorialResponseDTO dto = new HistorialResponseDTO();
        dto.setCodigoHistorial((String) row[0]);
        dto.setFechaHistorial(((LocalDate) row[1]));
        dto.setHoraHistorial(((LocalTime) row[2]));
        dto.setUsuarioHistorial((String) row[3]);
        dto.setEstadoHistorial((String) row[4]);
        dto.setDetalleHistorial((String) row[5]);
        return dto;
    }
}
