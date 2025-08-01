package com.na.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "tr_codigo", nullable = false, unique = true, length = 4)
    private String codigo;

    @Column(name = "tr_nombre", nullable = false, length = 100)
    private String nombre;

    public Rol() {
    }

    public Rol(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
