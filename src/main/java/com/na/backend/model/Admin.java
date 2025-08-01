package com.na.backend.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "ad_codigo")
    private String codigo;

    @Column(name = "ad_nombre")
    private String nombre;

    @Column(name = "ad_apellido")
    private String apellido;

    @Column(name = "ad_correo")
    private String correo;

    @Column(name = "ad_telefono")
    private String telefono;

    @Column(name = "ad_edad")
    private int edad;

    @Column(name = "ad_fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "ad_estado")
    private String estado;

    @Column(name = "ad_fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ad_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    public Admin(String codigo, Usuario usuario, String estado, LocalDate fechaNacimiento, int edad, String telefono,
            String correo, LocalDate fechaRegistro, String apellido, String nombre) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estado='" + estado + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuario=" + usuario +
                '}';
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
