package com.na.backend.dto.response;

import java.time.LocalDate;

public class AdminResponseDTO {

    private String codigoAdmin;
    private String nombreAdmin;
    private String apellidoAdmin;
    private String correoAdmin;
    private String telefonoAdmin;
    private int edadAdmin;
    private LocalDate fechaNacimientoAdmin;
    private String estadoAdmin;
    private LocalDate fechaRegistroAdmin;
    private String codigoUsuarioAdmin;
    private String usernameAdmin;
    private String rolAdmin;

    // Getters y Setters

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getApellidoAdmin() {
        return apellidoAdmin;
    }

    public void setApellidoAdmin(String apellidoAdmin) {
        this.apellidoAdmin = apellidoAdmin;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getTelefonoAdmin() {
        return telefonoAdmin;
    }

    public void setTelefonoAdmin(String telefonoAdmin) {
        this.telefonoAdmin = telefonoAdmin;
    }

    public int getEdadAdmin() {
        return edadAdmin;
    }

    public void setEdadAdmin(int edadAdmin) {
        this.edadAdmin = edadAdmin;
    }

    public LocalDate getFechaNacimientoAdmin() {
        return fechaNacimientoAdmin;
    }

    public void setFechaNacimientoAdmin(LocalDate fechaNacimientoAdmin) {
        this.fechaNacimientoAdmin = fechaNacimientoAdmin;
    }

    public String getEstadoAdmin() {
        return estadoAdmin;
    }

    public void setEstadoAdmin(String estadoAdmin) {
        this.estadoAdmin = estadoAdmin;
    }

    public LocalDate getFechaRegistroAdmin() {
        return fechaRegistroAdmin;
    }

    public void setFechaRegistroAdmin(LocalDate fechaRegistroAdmin) {
        this.fechaRegistroAdmin = fechaRegistroAdmin;
    }

    public String getCodigoUsuarioAdmin() {
        return codigoUsuarioAdmin;
    }

    public void setCodigoUsuarioAdmin(String codigoUsuarioAdmin) {
        this.codigoUsuarioAdmin = codigoUsuarioAdmin;
    }

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getRolAdmin() {
        return rolAdmin;
    }

    public void setRolAdmin(String rolAdmin) {
        this.rolAdmin = rolAdmin;
    }
}
