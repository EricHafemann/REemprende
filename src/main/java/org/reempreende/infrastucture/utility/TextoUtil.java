package org.reempreende.infrastucture.utility;

public class TextoUtil {
    public static String transformar(String texto) {
        StringBuilder sb = new StringBuilder();

        for (char c : texto.toCharArray()) {
            sb.append(convert(c));
        }

        return sb.toString();
    }

    private static String convert(char c) {

        // Letras maiúsculas A-Z
        if (c >= 'A' && c <= 'Z') {
            return new String(Character.toChars(0x1D504 + (c - 'A')));
        }

        // Letras minúsculas a-z
        if (c >= 'a' && c <= 'z') {
            return new String(Character.toChars(0x1D51E + (c - 'a')));
        }

        // Mantém caracteres especiais (ç, ã, espaços, etc.)
        return String.valueOf(c);
    }
}
