package com.na.backend.validator;

public class Secuencia {
    // Clase para manejar la secuencia alfanumérica
    // Metodo para incrementar la secuencia alfanumérica
    public static String incrementarSecuencia(String secuencia) {
        // Si la secuencia es nula, inicializarla a "0000"
        if (secuencia == null) {
            secuencia = "0000";
        }
        System.out.print(secuencia);
        String alphaPart = secuencia.substring(0, secuencia.length() - 2);
        String numericPart = secuencia.substring(secuencia.length() - 2);
        try {
            int num = Integer.parseInt(numericPart);
            num++;
            // Si el número es mayor a 99, incrementar el prefijo alfanumérico
            if (num > 99) {
                alphaPart = incrementarPrefijo(alphaPart);
                num = 0;
            }
            return alphaPart + String.format("%02d", num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo para incrementar el prefijo alfanumérico
    private static String incrementarPrefijo(String prefix) {
        char[] chars = prefix.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'Z') {
                chars[i] = 'A';
            } else {
                chars[i]++;
                break;
            }
        }
        return new String(chars);
    }
}