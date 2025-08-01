package com.na.backend.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class RevisionResponseDTO {

    private String codigoRevision;

    private LocalDate fechaRevision;

    private String estadoRevision;

    private String observacionRevision;

    private String codigoSuspension;

    private LocalDate fechaSuspension;

    private LocalDate fechaExpiracion;

    private String motivoSuspension;

    private LocalTime horaSuspension;

    private String detalleSuspension;

    private String codigoUsuario;

    private String usernameUsuario;

    private String correoUsuario;

    private String codigoModerador;

    private String nombreModerador;

    private String apellidoModerador;
    
    private String correoModerador;

    public String getCodigoRevision() {
        return codigoRevision;
    }

    public void setCodigoRevision(String codigoRevision) {
        this.codigoRevision = codigoRevision;
    }

    public LocalDate getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(LocalDate fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getEstadoRevision() {
        return estadoRevision;
    }

    public void setEstadoRevision(String estadoRevision) {
        this.estadoRevision = estadoRevision;
    }

    public String getObservacionRevision() {
        return observacionRevision;
    }

    public void setObservacionRevision(String observacionRevision) {
        this.observacionRevision = observacionRevision;
    }

    public String getCodigoSuspension() {
        return codigoSuspension;
    }

    public void setCodigoSuspension(String codigoSuspension) {
        this.codigoSuspension = codigoSuspension;
    }

    public LocalDate getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(LocalDate fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }

    public LocalTime getHoraSuspension() {
        return horaSuspension;
    }

    public void setHoraSuspension(LocalTime horaSuspension) {
        this.horaSuspension = horaSuspension;
    }

    public String getDetalleSuspension() {
        return detalleSuspension;
    }

    public void setDetalleSuspension(String detalleSuspension) {
        this.detalleSuspension = detalleSuspension;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getCodigoModerador() {
        return codigoModerador;
    }

    public void setCodigoModerador(String codigoModerador) {
        this.codigoModerador = codigoModerador;
    }

    public String getNombreModerador() {
        return nombreModerador;
    }

    public void setNombreModerador(String nombreModerador) {
        this.nombreModerador = nombreModerador;
    }

    public String getApellidoModerador() {
        return apellidoModerador;
    }

    public void setApellidoModerador(String apellidoModerador) {
        this.apellidoModerador = apellidoModerador;
    }

    public String getCorreoModerador() {
        return correoModerador;
    }

    public void setCorreoModerador(String correoModerador) {
        this.correoModerador = correoModerador;
    }
}
