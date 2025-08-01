package com.na.backend.dto.request;

public class VerificacionRequestDTO {
        private String correo;
    private String usuario;

    @Override
    public String toString() {
        return "VerificacionDTO{" +
                "correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    public VerificacionRequestDTO(String correo, String usuario) {
        this.correo = correo;
        this.usuario = usuario;
    }

    public VerificacionRequestDTO() {
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
