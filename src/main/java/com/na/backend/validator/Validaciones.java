package com.na.backend.validator;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

// Clase para validar datos de entrada 
public class Validaciones {

    public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    public boolean correoEsValido(String correo) {
        // Expresión regular para validar el formato del correo
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("^[A-Za-z\\s]+$");
    }

    public String convertirArchivoABase64(MultipartFile archivo) throws IOException {
        byte[] contenido = archivo.getBytes();
        return Base64.getEncoder().encodeToString(contenido);
    }



    
}