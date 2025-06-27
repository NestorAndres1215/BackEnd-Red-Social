package com.na.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "normal")
public class Normal {
    // Codigo
    @Id
    @Column(name = "no_codigo")
    private String codigo;
    // Nombre
    @Column(name = "no_nombre")
    private String nombre;
    // Apellido
    @Column(name = "no_apellido")
    private String apellido;
    // Correo
    @Column(name = "no_correo")
    private String correo;
    // Telefono
    @Column(name = "no_telefono")
    private String telefono;
    // Edad
    @Column(name = "no_edad")
    private int edad;
    // Fecha Nacimiento
    @Column(name = "no_fechanacimiento")
    private LocalDate fechaNacimiento;
    // Estado Cuenta
    @Column(name = "no_estado")
    private String estado;
    // Fecha de Registro de la cuenta
    @Column(name = "no_fechaRegistro")
    private LocalDate fechaRegistro;
    // Foto de Perfil
    @Column(name = "no_perfil")
    private byte[] perfil;
    // Genero
    @Column(name = "no_genero")
    private String genero;
    // Nacionalidad
    @Column(name = "no_nacionalidad")
    private String nacionalidad;
    // Presentacion
    @Column(name = "no_presentacion")
    private String presentacion;
    // Ultimo Acceso
    @Column(name = "no_ultimo_acceso")
    private LocalDateTime ultimoAcceso;
    // Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "no_usuario", referencedColumnName = "us_codigo")
    private Usuario usuario;

    public Normal() {
    }

    public Normal(String codigo, String nombre, String apellido, String correo, String telefono, int edad,
            LocalDate fechaNacimiento, String estado, LocalDate fechaRegistro, byte[] perfil,
            String genero, String nacionalidad, String presentacion, LocalDateTime ultimoAcceso,
            Usuario usuario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.perfil = perfil;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.presentacion = presentacion;
        this.ultimoAcceso = ultimoAcceso;
        this.usuario = usuario;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public byte[] getPerfil() {
        return perfil;
    }

    public void setPerfil(byte[] perfil) {
        this.perfil = perfil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
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