package com.na.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "historial_usuario")
public class Historial {

    @Id
    @Column(name = "hu_codigo")
    private String codigo;

    @Column(name = "hu_fecha")
    private LocalDate fecha;

    @Column(name = "hu_hora")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "hu_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    @Column(name = "hu_detalle", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "hu_estado", nullable = false)
    private String estado;

    public Historial(String codigo, LocalDate fecha, LocalTime hora, Usuario usuario, String detalle, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
        this.detalle = detalle;
        this.estado = estado;
    }

    public Historial() {
    }

    @Override
    public String toString() {
        return "Historial{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", usuario=" + usuario +
                ", detalle='" + detalle + '\'' +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
