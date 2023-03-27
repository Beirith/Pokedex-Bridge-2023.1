package pokemon;

import java.util.Objects;
import java.util.Random;

public class GeraDadosPokemon {
    final String[] normalVantagem = {};
    final String[] normalDesvantagem = {};

    final String[] fogoVantagem = {"Grama", "Gelo"};
    final String[] fogoDesvantagem = {"Fogo", "Água"};

    final String[] aguaVantagem = {"Fogo"};
    final String[] aguaDesvantagem = {"Água", "Grama"};

    final String[] eletricoVantagem = {"Água"};
    final String[] eletricoDesvantagem = {"Elétrico", "Grama"};

    final String[] gramaVantagem = {"Água"};
    final String[] gramaDesvantagem = {"Grama", "Fogo"};

    final String[] geloVantagem = {"Grama", "Gelo"};
    final String[] geloDesvantagem = {"Gelo", "Fogo", "Água"};

    final String[] lutadorVantagem = {"Normal", "Gelo"};
    final String[] lutadorDesvantagem = {};

    public void geraRaridade(Pokemon pokemon) {
        Random raridadeAleatoria = new Random();
        int raridade = raridadeAleatoria.nextInt(201) - 100;
        pokemon.setRaridade(raridade);
    }

    public void geraVatangemDesvantagem(Pokemon pokemon) {
        if (Objects.equals(pokemon.getTipo(), "Normal")) {
            pokemon.setVantagem(normalVantagem);
            pokemon.setDesvantagem(normalDesvantagem);
        }
        else if (Objects.equals(pokemon.getTipo(), "Fogo")) {
            pokemon.setVantagem(fogoVantagem);
            pokemon.setDesvantagem(fogoDesvantagem);
        }
        else if (Objects.equals(pokemon.getTipo(), "Água")) {
            pokemon.setVantagem(aguaVantagem);
            pokemon.setDesvantagem(aguaDesvantagem);
        }
        else if (Objects.equals(pokemon.getTipo(), "Elétrico")) {
            pokemon.setVantagem(eletricoVantagem);
            pokemon.setDesvantagem(eletricoDesvantagem);
        }
        else if (Objects.equals(pokemon.getTipo(), "Grama")) {
            pokemon.setVantagem(gramaVantagem);
            pokemon.setDesvantagem(gramaDesvantagem);

        }
        else if (Objects.equals(pokemon.getTipo(), "Gelo")) {
            pokemon.setVantagem(geloVantagem);
            pokemon.setDesvantagem(geloDesvantagem);
        }
        else{
            pokemon.setVantagem(lutadorVantagem);
            pokemon.setDesvantagem(lutadorDesvantagem);
        }
    }
}
