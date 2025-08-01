package com.na.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @Column(name = "us_codigo")
    private String codigo;

    @Column(name = "us_usuario", nullable = false)
    private String username; 

    @Column(name = "us_correo", nullable = false)
    private String correo; 

    @Column(name = "us_telefono", nullable = false)
    private String telefono; 
    
    @Column(name = "us_contra", nullable = false)
    private String password;

    @Column(name = "us_estado", nullable = false)
    private String estado; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_rol", referencedColumnName = "tr_codigo")
    @JsonIgnore
    private Rol rol;

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo='" + codigo + '\'' +
                ", username='" + username + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", password='" + password + '\'' +
                ", estado='" + estado + '\'' +
                ", rol=" + rol +
                '}';
    }

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
            rol.setCodigo(codigoRol);
            this.rol = rol; 
        }
    }

}