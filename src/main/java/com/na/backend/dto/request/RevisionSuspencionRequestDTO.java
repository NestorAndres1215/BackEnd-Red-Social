package com.na.backend.dto.request;

import java.time.LocalDate;

public class RevisionSuspencionRequestDTO {
    private String codigo;
    private String detalle;
    private String correo;
    private String asunto;
    private String motivo;
    private String usuario;
    private String moderador;
    private LocalDate fechaSuspension;



    public RevisionSuspencionRequestDTO(String codigo, String detalle, String correo, String asunto, String motivo,
            String usuario, String moderador, LocalDate fechaSuspension) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.correo = correo;
        this.asunto = asunto;
        this.motivo = motivo;
        this.usuario = usuario;
        this.moderador = moderador;
        this.fechaSuspension = fechaSuspension;
    }

    public LocalDate getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(LocalDate fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getModerador() {
        return moderador;
    }

    public void setModerador(String moderador) {
        this.moderador = moderador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public RevisionSuspencionRequestDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
