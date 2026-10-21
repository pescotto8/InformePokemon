import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estadisticas {

    private final int total;
    private final double nivelMedio;
    private final double psMedio;
    private final Pokemon mayorNivel;
    private final Pokemon mayorPs;
    private final Map<String, ArrayList<String>> porTipo;
    private final ArrayList<String> tiposOrdenados;

    public Estadisticas(ArrayList<Pokemon> pokemons) {
        long sumaNivel = 0;
        long sumaPs = 0;
        Pokemon maxNivel = null;
        Pokemon maxPs = null;
        Map<String, ArrayList<String>> agrupados = new HashMap<>();

        for (Pokemon p : pokemons) {
            sumaNivel += p.getNivel();
            sumaPs += p.getPs();
            if (maxNivel == null || p.getNivel() > maxNivel.getNivel()) {
                maxNivel = p;
            }
            if (maxPs == null || p.getPs() > maxPs.getPs()) {
                maxPs = p;
            }
            String tipo = p.getTipo() == null ? "SIN TIPO" : p.getTipo();
            ArrayList<String> nombres = agrupados.get(tipo);
            if (nombres == null) {
                nombres = new ArrayList<>();
                agrupados.put(tipo, nombres);
            }
            nombres.add(p.getNombre());
        }

        this.total = pokemons.size();
        this.nivelMedio = total == 0 ? 0 : (double) sumaNivel / total;
        this.psMedio = total == 0 ? 0 : (double) sumaPs / total;
        this.mayorNivel = maxNivel;
        this.mayorPs = maxPs;
        this.porTipo = agrupados;
        this.tiposOrdenados = ordenar(new ArrayList<>(agrupados.keySet()));
    }

    private static ArrayList<String> ordenar(ArrayList<String> tipos) {
        for (int i = 0; i < tipos.size(); i++) {
            for (int j = i + 1; j < tipos.size(); j++) {
                if (tipos.get(j).compareTo(tipos.get(i)) < 0) {
                    String auxiliar = tipos.get(i);
                    tipos.set(i, tipos.get(j));
                    tipos.set(j, auxiliar);
                }
            }
        }
        return tipos;
    }

    public int getTotal() {
        return total;
    }

    public double getNivelMedio() {
        return nivelMedio;
    }

    public double getPsMedio() {
        return psMedio;
    }

    public Pokemon getMayorNivel() {
        return mayorNivel;
    }

    public Pokemon getMayorPs() {
        return mayorPs;
    }

    public Map<String, ArrayList<String>> getPorTipo() {
        return porTipo;
    }

    public ArrayList<String> getTiposOrdenados() {
        return tiposOrdenados;
    }
}
