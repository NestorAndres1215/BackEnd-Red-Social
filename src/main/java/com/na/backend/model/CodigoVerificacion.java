package com.na.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "codigo_verificacion")
public class CodigoVerificacion {

    @Id
    @Column(name = "cv_codigo")
    private String codigo;

    @Column(name = "cv_correo")
    private String correo;

    @Column(name = "cv_usuario")
    private String usuario;

    @Column(name = "cv_codigo_verificado")
    private String codigo_verificacion;

    @Column(name = "cv_fecha_generacion")
    private LocalDate fecha_generacion;
    
    @Column(name = "cv_hora_generacion")
    private LocalTime hora_generacion;

    public CodigoVerificacion() {
    }

    @Override
    public String toString() {
        return "CodigoVerificacion{" +
                "codigo='" + codigo + '\'' +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", codigo_verificacion='" + codigo_verificacion + '\'' +
                ", fecha_generacion=" + fecha_generacion +
                ", hora_generacion=" + hora_generacion +
                '}';
    }

    public CodigoVerificacion(String codigo, String correo, String usuario, String codigo_verificacion,
            LocalDate fecha_generacion, LocalTime hora_generacion) {
        this.codigo = codigo;
        this.correo = correo;
        this.usuario = usuario;
        this.codigo_verificacion = codigo_verificacion;
        this.fecha_generacion = fecha_generacion;
        this.hora_generacion = hora_generacion;
    }

    public LocalTime getHora_generacion() {
        return hora_generacion;
    }

    public void setHora_generacion(LocalTime hora_generacion) {
        this.hora_generacion = hora_generacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigo_verificacion() {
        return codigo_verificacion;
    }

    public void setCodigo_verificacion(String codigo_verificacion) {
        this.codigo_verificacion = codigo_verificacion;
    }

    public LocalDate getFecha_generacion() {
        return fecha_generacion;
    }

    public void setFecha_generacion(LocalDate fecha_generacion) {
        this.fecha_generacion = fecha_generacion;
    }
}
