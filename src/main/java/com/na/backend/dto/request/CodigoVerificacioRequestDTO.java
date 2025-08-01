package com.na.backend.dto.request;
public class CodigoVerificacioRequestDTO {
    private String correo;
    private String codigo;


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
