package com.na.backend.util;

import java.util.UUID;

public class CodigoUtil {

    public static String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

}
