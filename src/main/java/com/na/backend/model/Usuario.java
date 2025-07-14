package com.na.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "us_codigo")
    private String codigo; // Código único del usuario

    @Column(name = "us_usuario", nullable = false)
    private String username; // Nombre de usuario

    @Column(name = "us_correo", nullable = false)
    private String correo; // Correo electrónico del usuario

    @Column(name = "us_telefono", nullable = false)
    private String telefono; // Teléfono del usuario

    @Column(name = "us_contra", nullable = false)
    private String password; // Contraseña encriptada

    @Column(name = "us_estado", nullable = false)
    private String estado; // Estado del usuario (activo/inactivo)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_rol", referencedColumnName = "tr_codigo")
    @JsonIgnore
    private Rol rol;

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(String codigoRol) {
        if (codigoRol != null) {
            Rol rol = new Rol();
            rol.setCodigo(codigoRol); // Crear un objeto Rol con el código
            this.rol = rol; // Asignar el objeto Rol al usuario
        }
    }

}