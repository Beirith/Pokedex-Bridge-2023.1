package pokemon;

import java.util.Objects;
import java.util.Random;

/* Esta é a classe GeraDadosPokemon. Ela é responsável gerar aleatoriamente
*  o valor da raridade do Pokémon e também por atribuir as vantagens e desvantagens
*  de cada Pokémon de acordo com seu respectivo tipo. */

public class GeraDadosPokemon {
    // As vantagens e desvantagens pré-definidas são os atributos do objeto.
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

    // Método que gera aleatoriamente um valor de raridade entre -100 e 100 e atribui-o ao Pokémon.
    public void geraRaridade(Pokemon pokemon) {
        Random raridadeAleatoria = new Random();                            // Cria um objeto da classe Random.
        int raridade = raridadeAleatoria.nextInt(201) - 100;         // Calcula um valor aleatório entre -100 e 100 e atribui a variável raridade.
        pokemon.setRaridade(raridade);                                     // Seta a raridade ao Pokémon.
    }

    // Método que atribui as vantagens e desvantagens de cada Pokémon.
    public void geraVatangemDesvantagem(Pokemon pokemon) {
        // Verifica qual o tipo do Pokémon e atribui os respectivos valores
        // de vantagem e desvantagem ao Pokémon.

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