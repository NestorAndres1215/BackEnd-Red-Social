package com.na.backend.message;

public enum BaseMessage {
    
    ERROR_USUARIO_ACTUAL("No se pudo obtener la información del usuario actual."),
    ERROR_SOLICITUD("Se produjo un error al procesar la solicitud."),
    CREDENCIALES_INVALIDAS("Las credenciales ingresadas no son válidas."),
    USUARIO_NO_ENCONTRADO("No se encontró un usuario con los datos proporcionados."),
    TOKEN_EXPIRADO("El token de autenticación ha expirado."),
    USUARIO_NO_AUTORIZADO("No tiene permisos para realizar esta operación."),
    TOKEN_INVALIDO("El token proporcionado no es válido o ha sido manipulado."),
    ERROR_INTERNO("Se ha producido un error interno en el servidor. Intente nuevamente más tarde.");

    private final String mensaje;

    BaseMessage(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
