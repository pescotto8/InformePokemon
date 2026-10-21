import java.util.ArrayList;

public class GeneradorInforme {

    public static String generar(Estadisticas estadisticas) {
        String linea = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        Pokemon mayorNivel = estadisticas.getMayorNivel();
        Pokemon mayorPs = estadisticas.getMayorPs();

        sb.append("=========================").append(linea);
        sb.append(" INFORME DE POKÉMON").append(linea);
        sb.append("=========================").append(linea);
        sb.append("Total de Pokémon: ").append(estadisticas.getTotal()).append(linea);
        sb.append("Nivel medio: ").append(formatear(estadisticas.getNivelMedio())).append(linea);
        sb.append("PS medios: ").append(formatear(estadisticas.getPsMedio())).append(linea);

        sb.append("Pokémon con mayor nivel:").append(linea);
        if (mayorNivel != null) {
            sb.append(mayorNivel.getNombre()).append(" (Nivel ").append(mayorNivel.getNivel()).append(")").append(linea);
        } else {
            sb.append("Sin datos").append(linea);
        }

        sb.append("Pokémon con más PS:").append(linea);
        if (mayorPs != null) {
            sb.append(mayorPs.getNombre()).append(" (PS ").append(mayorPs.getPs()).append(")").append(linea);
        } else {
            sb.append("Sin datos").append(linea);
        }

        sb.append("-------------------------").append(linea);
        sb.append("POKÉMON POR TIPO").append(linea);
        sb.append("-------------------------").append(linea);

        for (String tipo : estadisticas.getTiposOrdenados()) {
            ArrayList<String> nombres = estadisticas.getPorTipo().get(tipo);
            sb.append(tipo).append(": ").append(nombres.size()).append(linea);
            for (String nombre : nombres) {
                sb.append(nombre).append(linea);
            }
        }

        return sb.toString();
    }

    private static String formatear(double valor) {
        long centesimas = Math.round(valor * 100);
        long entero = centesimas / 100;
        long decimales = centesimas % 100;
        return entero + "." + (decimales < 10 ? "0" : "") + decimales;
    }
}
