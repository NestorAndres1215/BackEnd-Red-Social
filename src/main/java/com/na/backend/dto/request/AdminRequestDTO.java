package com.na.backend.dto.request;

import java.time.LocalDate;

public class AdminRequestDTO {

    private String codigoUsuarioAdmin;
    private String codigoAdmin;
    private String nombreAdmin;
    private String apellidoAdmin;
    private String correoAdmin;
    private String telefonoAdmin;
    private int edadAdmin;
    private LocalDate fechaNacimientoAdmin; // O puedes usar `LocalDate` si manejas fechas
    private String usernameAdmin;
    private String passwordAdmin;

    public String getCodigoUsuarioAdmin() {
        return codigoUsuarioAdmin;
    }

    public void setCodigoUsuarioAdmin(String codigoUsuarioAdmin) {
        this.codigoUsuarioAdmin = codigoUsuarioAdmin;
    }

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

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }
}
