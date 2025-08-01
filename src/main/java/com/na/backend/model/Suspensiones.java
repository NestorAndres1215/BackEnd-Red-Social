package com.na.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "suspensiones")
public class Suspensiones {

    @Id
    @Column(name = "sus_codigo")
    private String codigo;

    @Column(name = "sus_fecha_suspension")
    private LocalDate fecha_suspension;

    @Column(name = "sus_fecha_expiracion")
    private LocalDate fecha_expiracion;

    @Column(name = "sus_motivo_corto")
    private String motivo_corto;

    @Column(name = "sus_hora_suspension")
    private LocalTime hora_suspension;

    @Column(name = "sus_detalle_motivo")
    private String detalle_motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sus_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    public Suspensiones(String codigo, LocalDate fecha_suspension, LocalDate fecha_expiracion, String motivo_corto,
            LocalTime hora_suspension, String detalle_motivo, Usuario usuario) {
        this.codigo = codigo;
        this.fecha_suspension = fecha_suspension;
        this.fecha_expiracion = fecha_expiracion;
        this.motivo_corto = motivo_corto;
        this.hora_suspension = hora_suspension;
        this.detalle_motivo = detalle_motivo;
        this.usuario = usuario;
    }

    public Suspensiones() {
    }

    @Override
    public String toString() {
        return "Suspensiones{" +
                "codigo='" + codigo + '\'' +
                ", fecha_suspension=" + fecha_suspension +
                ", fecha_expiracion=" + fecha_expiracion +
                ", motivo_corto='" + motivo_corto + '\'' +
                ", hora_suspension=" + hora_suspension +
                ", detalle_motivo='" + detalle_motivo + '\'' +
                ", usuario=" + usuario +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha_suspension() {
        return fecha_suspension;
    }

    public void setFecha_suspension(LocalDate fecha_suspension) {
        this.fecha_suspension = fecha_suspension;
    }

    public LocalDate getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(LocalDate fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getMotivo_corto() {
        return motivo_corto;
    }

    public void setMotivo_corto(String motivo_corto) {
        this.motivo_corto = motivo_corto;
    }

    public LocalTime getHora_suspension() {
        return hora_suspension;
    }

    public void setHora_suspension(LocalTime hora_suspension) {
        this.hora_suspension = hora_suspension;
    }

    public String getDetalle_motivo() {
        return detalle_motivo;
    }

    public void setDetalle_motivo(String detalle_motivo) {
        this.detalle_motivo = detalle_motivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(String codigoUsuario) {
        if (codigoUsuario != null) {
            Usuario usuario = new Usuario();
            usuario.setCodigo(codigoUsuario);
            this.usuario = usuario;
        }
    }
}
