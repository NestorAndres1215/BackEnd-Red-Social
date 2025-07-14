package com.na.backend.dto;

public class CodigoVerificacioDTO {
    private String correo;
    private String codigo;

    public CodigoVerificacioDTO() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
