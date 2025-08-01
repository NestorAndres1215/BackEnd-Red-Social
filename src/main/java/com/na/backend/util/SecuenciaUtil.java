package com.na.backend.util;

public final class SecuenciaUtil {

    private static final String DEFAULT_SEQUENCE = "0000";
    private static final int NUMERIC_LENGTH = 2;

    private SecuenciaUtil() {
    }

    public static String generarSiguienteCodigo(String secuenciaActual) {
        if (secuenciaActual == null) {
            secuenciaActual = DEFAULT_SEQUENCE;
        }

        String parteAlfabetica = secuenciaActual.substring(0, secuenciaActual.length() - NUMERIC_LENGTH);
        String parteNumerica = secuenciaActual.substring(secuenciaActual.length() - NUMERIC_LENGTH);

        try {
            int numero = Integer.parseInt(parteNumerica);
            numero++;

            if (numero > 99) {
                parteAlfabetica = incrementarParteAlfabetica(parteAlfabetica);
                numero = 0;
            }

            return parteAlfabetica + String.format("%02d", numero);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La parte numérica de la secuencia no es válida: " + secuenciaActual, e);
        }
    }

    private static String incrementarParteAlfabetica(String prefijo) {
        char[] letras = prefijo.toCharArray();

        for (int i = letras.length - 1; i >= 0; i--) {
            if (letras[i] == 'Z') {
                letras[i] = 'A';
            } else {
                letras[i]++;
                break;
            }
        }

        return new String(letras);
    }
}
