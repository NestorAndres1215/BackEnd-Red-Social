package com.na.backend.dto.request;

import java.time.LocalDate;

public class NormalRequestDTO {
    private String codigoUsuario;
    private String codigoNormal;
    private String nombreNormal;
    private String apellidoNormal;
    private String correoNormal;
    private int edadNormal;
    private String telefonoNormal;
    private LocalDate fechaNacimientoNormal;
    private String generoNormal;
    private String nacionalidadNormal;
    private String usernameNormal;
    private String passwordNormal;

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCodigoNormal() {
        return codigoNormal;
    }

    public void setCodigoNormal(String codigoNormal) {
        this.codigoNormal = codigoNormal;
    }

    public String getNombreNormal() {
        return nombreNormal;
    }

    public void setNombreNormal(String nombreNormal) {
        this.nombreNormal = nombreNormal;
    }

    public String getApellidoNormal() {
        return apellidoNormal;
    }

    public void setApellidoNormal(String apellidoNormal) {
        this.apellidoNormal = apellidoNormal;
    }

    public String getCorreoNormal() {
        return correoNormal;
    }

    public void setCorreoNormal(String correoNormal) {
        this.correoNormal = correoNormal;
    }

    public int getEdadNormal() {
        return edadNormal;
    }

    public void setEdadNormal(int edadNormal) {
        this.edadNormal = edadNormal;
    }

    public String getTelefonoNormal() {
        return telefonoNormal;
    }

    public void setTelefonoNormal(String telefonoNormal) {
        this.telefonoNormal = telefonoNormal;
    }

    public LocalDate getFechaNacimientoNormal() {
        return fechaNacimientoNormal;
    }

    public void setFechaNacimientoNormal(LocalDate fechaNacimientoNormal) {
        this.fechaNacimientoNormal = fechaNacimientoNormal;
    }

    public String getGeneroNormal() {
        return generoNormal;
    }

    public void setGeneroNormal(String generoNormal) {
        this.generoNormal = generoNormal;
    }

    public String getNacionalidadNormal() {
        return nacionalidadNormal;
    }

    public void setNacionalidadNormal(String nacionalidadNormal) {
        this.nacionalidadNormal = nacionalidadNormal;
    }

    public String getUsernameNormal() {
        return usernameNormal;
    }

    public void setUsernameNormal(String usernameNormal) {
        this.usernameNormal = usernameNormal;
    }

    public String getPasswordNormal() {
        return passwordNormal;
    }

    public void setPasswordNormal(String passwordNormalString) {
        this.passwordNormal = passwordNormalString;
    }
}
