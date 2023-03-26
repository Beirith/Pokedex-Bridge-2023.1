package pokemon;

import java.util.Random;

public class GeraDadosPokemon {

    private String vantagemGerada;
    private String desvantagemGerada;

    public int geraRaridade() {
        Random raridadeAleatoria = new Random();
        int raridade = raridadeAleatoria.nextInt(201) - 100;
        return raridade;
    }

    public String geraVatangem() {
        vantagemGerada = "Nada";
        return vantagemGerada;
    }

    public String geraDesvantagem() {
        desvantagemGerada = "Nada";
        return desvantagemGerada;
    }
}
