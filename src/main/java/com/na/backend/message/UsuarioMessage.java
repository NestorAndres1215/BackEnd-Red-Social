package com.na.backend.message;

public enum UsuarioMessage {

    ADMIN_NO_EXISTE("Administrador no existe");
    private final String mensaje;

    UsuarioMessage(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
