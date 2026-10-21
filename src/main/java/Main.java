import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final String FICHERO_JSON = "pokemon.json";
    private static final String FICHERO_INFORME = "informe_pokemon.txt";

    public static void main(String[] args) {
        try {
            String contenido = GestorFicheros.leer(FICHERO_JSON);
            ArrayList<Pokemon> pokemons = new LectorJson(contenido).leerPokemons();
            Estadisticas estadisticas = new Estadisticas(pokemons);
            String informe = GeneradorInforme.generar(estadisticas);
            GestorFicheros.escribir(FICHERO_INFORME, informe);
            System.out.println("Informe generado en " + FICHERO_INFORME);
        } catch (IOException e) {
            System.err.println("Error de lectura o escritura: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }
    }
}
