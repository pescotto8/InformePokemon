public class GestorFicheros {

    public static String leer(String ruta) throws java.io.IOException {
        StringBuilder sb = new StringBuilder();
        try (java.io.BufferedReader lector = new java.io.BufferedReader(
                new java.io.InputStreamReader(new java.io.FileInputStream(ruta), "UTF-8"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                sb.append(linea).append('\n');
            }
        }
        return sb.toString();
    }

    public static void escribir(String ruta, String contenido) throws java.io.IOException {
        try (java.io.BufferedWriter escritor = new java.io.BufferedWriter(
                new java.io.OutputStreamWriter(new java.io.FileOutputStream(ruta), "UTF-8"))) {
            escritor.write(contenido);
        }
    }
}
