package com.na.backend.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class ArchivoUtils {

    public static String convertirABase64(MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío o es nulo");
        }
        byte[] contenido = archivo.getBytes();
        return Base64.getEncoder().encodeToString(contenido);
    }

}
