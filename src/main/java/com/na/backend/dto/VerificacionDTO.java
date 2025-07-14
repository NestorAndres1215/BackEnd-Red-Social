package com.na.backend.dto;

public class VerificacionDTO {

    private String correo;
    private String usuario;

    @Override
    public String toString() {
        return "VerificacionDTO{" +
                "correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    public VerificacionDTO(String correo, String usuario) {
        this.correo = correo;
        this.usuario = usuario;
    }

    public VerificacionDTO() {
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
}
