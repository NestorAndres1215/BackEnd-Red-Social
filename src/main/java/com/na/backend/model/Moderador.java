package com.na.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "moderador")
public class Moderador {
    @Id
    @Column(name = "mod_codigo")
    private String codigo;

    @Column(name = "mod_nombre")
    private String nombre;

    @Column(name = "mod_apellido")
    private String apellido;

    @Column(name = "mod_correo")
    private String correo;

    @Column(name = "mod_telefono")
    private String telefono;

    @Column(name = "mod_edad")
    private Integer edad;

    @Column(name = "mod_fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "mod_estado")
    private String estado;

    @Column(name = "mod_fechaRegistro")
    private LocalDate fechaRegistro;

    // Genero
    @Column(name = "mod_genero")
    private String genero;
    // Ultimo Acceso
    @Column(name = "mod_ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    public Moderador() {
    }

    public Moderador(String codigo, String nombre, String apellido, String correo, String telefono,Integer edad,
            LocalDate fechaNacimiento, String estado, LocalDate fechaRegistro, String genero,
            LocalDateTime ultimoAcceso, Usuario usuario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.genero = genero;
        this.ultimoAcceso = ultimoAcceso;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Moderador{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estado='" + estado + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", genero='" + genero + '\'' +
                ", ultimoAcceso=" + ultimoAcceso +
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(String codigoUsuario) {
        if (codigoUsuario != null) {
            Usuario usuario = new Usuario(); // Crear un objeto Usuario
            usuario.setCodigo(codigoUsuario); // Asignar el código al usuario
            this.usuario = usuario; // Asignarlo al administrador
        }
    }

}
