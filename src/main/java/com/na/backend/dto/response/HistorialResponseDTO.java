package com.na.backend.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistorialResponseDTO {

    private String codigoHistorial;

    private LocalDate fechaHistorial;

    private LocalTime horaHistorial;

    private String usuarioHistorial;

    private String estadoHistorial;

    private String detalleHistorial;

    public String getDetalleHistorial() {
        return detalleHistorial;
    }

    public void setDetalleHistorial(String detalleHistorial) {
        this.detalleHistorial = detalleHistorial;
    }

    public String getEstadoHistorial() {
        return estadoHistorial;
    }

    public void setEstadoHistorial(String estadoHistorial) {
        this.estadoHistorial = estadoHistorial;
    }

    public String getUsuarioHistorial() {
        return usuarioHistorial;
    }

    public void setUsuarioHistorial(String usuarioHistorial) {
        this.usuarioHistorial = usuarioHistorial;
    }

    public LocalTime getHoraHistorial() {
        return horaHistorial;
    }

    public void setHoraHistorial(LocalTime horaHistorial) {
        this.horaHistorial = horaHistorial;
    }

    public LocalDate getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(LocalDate fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public String getCodigoHistorial() {
        return codigoHistorial;
    }

    public void setCodigoHistorial(String codigoHistorial) {
        this.codigoHistorial = codigoHistorial;
    }
}
