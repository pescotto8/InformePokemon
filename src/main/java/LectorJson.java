import java.util.ArrayList;

public class LectorJson {

    private final String texto;
    private int pos;

    public LectorJson(String texto) {
        this.texto = texto.startsWith("﻿") ? texto.substring(1) : texto;
        this.pos = 0;
    }

    public ArrayList<Pokemon> leerPokemons() {
        ArrayList<Pokemon> lista = new ArrayList<>();
        saltarEspacios();
        esperar('[');
        saltarEspacios();
        if (actual() == ']') {
            pos++;
            return lista;
        }
        while (true) {
            lista.add(leerObjeto());
            saltarEspacios();
            char c = actual();
            pos++;
            if (c == ']') {
                break;
            }
            if (c != ',') {
                throw error("se esperaba ',' o ']'");
            }
            saltarEspacios();
        }
        return lista;
    }

    private Pokemon leerObjeto() {
        int id = 0;
        int nivel = 0;
        int ps = 0;
        String nombre = null;
        String tipo = null;

        saltarEspacios();
        esperar('{');
        saltarEspacios();
        if (actual() == '}') {
            pos++;
            return new Pokemon(id, nombre, tipo, nivel, ps);
        }
        while (true) {
            saltarEspacios();
            String clave = leerCadena();
            saltarEspacios();
            esperar(':');
            saltarEspacios();
            char c = actual();
            String valorTexto = null;
            double valorNumero = 0;
            boolean esTexto = false;
            boolean esNumero = false;
            if (c == '"') {
                valorTexto = leerCadena();
                esTexto = true;
            } else if (c == '-' || (c >= '0' && c <= '9')) {
                valorNumero = leerNumero();
                esNumero = true;
            } else {
                leerLiteral();
            }
            switch (clave) {
                case "id":
                    if (esNumero) {
                        id = (int) valorNumero;
                    }
                    break;
                case "nombre":
                    if (esTexto) {
                        nombre = valorTexto;
                    }
                    break;
                case "tipo":
                    if (esTexto) {
                        tipo = valorTexto;
                    }
                    break;
                case "nivel":
                    if (esNumero) {
                        nivel = (int) valorNumero;
                    }
                    break;
                case "ps":
                    if (esNumero) {
                        ps = (int) valorNumero;
                    }
                    break;
                default:
                    break;
            }
            saltarEspacios();
            char separador = actual();
            pos++;
            if (separador == '}') {
                break;
            }
            if (separador != ',') {
                throw error("se esperaba ',' o '}'");
            }
        }
        return new Pokemon(id, nombre, tipo, nivel, ps);
    }

    private String leerCadena() {
        esperar('"');
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (pos >= texto.length()) {
                throw error("cadena sin cerrar");
            }
            char c = texto.charAt(pos++);
            if (c == '"') {
                break;
            }
            if (c == '\\') {
                if (pos >= texto.length()) {
                    throw error("secuencia de escape incompleta");
                }
                char e = texto.charAt(pos++);
                switch (e) {
                    case 'n':
                        sb.append('\n');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'u':
                        if (pos + 4 > texto.length()) {
                            throw error("secuencia unicode incompleta");
                        }
                        sb.append((char) Integer.parseInt(texto.substring(pos, pos + 4), 16));
                        pos += 4;
                        break;
                    default:
                        sb.append(e);
                        break;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private double leerNumero() {
        int inicio = pos;
        while (pos < texto.length() && "+-.eE0123456789".indexOf(texto.charAt(pos)) >= 0) {
            pos++;
        }
        try {
            return Double.parseDouble(texto.substring(inicio, pos));
        } catch (NumberFormatException e) {
            throw error("número no válido");
        }
    }

    private void leerLiteral() {
        int inicio = pos;
        while (pos < texto.length() && Character.isLetter(texto.charAt(pos))) {
            pos++;
        }
        if (inicio == pos) {
            throw error("valor no soportado");
        }
    }

    private void saltarEspacios() {
        while (pos < texto.length() && Character.isWhitespace(texto.charAt(pos))) {
            pos++;
        }
    }

    private char actual() {
        if (pos >= texto.length()) {
            throw error("fin de fichero inesperado");
        }
        return texto.charAt(pos);
    }

    private void esperar(char esperado) {
        if (actual() != esperado) {
            throw error("se esperaba '" + esperado + "'");
        }
        pos++;
    }

    private IllegalArgumentException error(String mensaje) {
        return new IllegalArgumentException("JSON no válido en la posición " + pos + ": " + mensaje);
    }
}
