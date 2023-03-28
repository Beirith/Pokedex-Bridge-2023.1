package calculos;

import pokemon.CadastroPokemon;
import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BuscaVantagemPokemon {

    private ArrayList<Pokemon> listaPokemonComVantagem = new ArrayList<>();
    private ArrayList<Pokemon> listaPokemon = new ArrayList<>();

    public void buscarPokemon(Scanner scan, ArrayList<Pokemon> listaDePokemons, CadastroPokemon cadastrosPokemons) {
        listaPokemon = listaDePokemons;

        if (listaDePokemons.size() == 0) {
            System.out.println("\nNão há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        }
        else {
            System.out.println("Digite o nome do Pokémon: ");
            String nomePokemon;
            nomePokemon = scan.next();

            if (cadastrosPokemons.checaNomeDisponivel(nomePokemon, cadastrosPokemons.retornaListaPokemons())) {
                System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                System.out.println("Verifique se o nome foi digitado corretamente e tente novamente!\n");
            }
            else {
                Pokemon pokemonDigitado;
                for (Pokemon pokemon : listaDePokemons) {
                    if (Objects.equals(nomePokemon, pokemon.getNome())) {
                        pokemonDigitado = pokemon;
                        buscarVantagem(pokemonDigitado);
                        break;
                    }
                }
            }
        }
    }

    public void buscarVantagem(Pokemon pokemonDigitado) {
        String[] listaVantagem;
        String tipoP;
        String vatangemP;

        tipoP = pokemonDigitado.getTipo();

        for (Pokemon pokemon : listaPokemon) {
            listaVantagem = pokemon.getVantagem();

            for (String vantagemLista : listaVantagem) {
                vatangemP = vantagemLista;

                if (Objects.equals(tipoP, vatangemP) && !Objects.equals(pokemonDigitado, pokemon)) {
                    listaPokemonComVantagem.add(pokemon);
                }
            }
        }
        retornaListaVantagem(pokemonDigitado);

    }

    public void retornaListaVantagem(Pokemon pokemonDigitado) {
        if (listaPokemonComVantagem.size() == 0) {
            System.out.println("\nNão existem Pokémons conhecidos que possuam vantagem contra o Pokémon " + pokemonDigitado.getNome() + "\n");
        }
        else {
            if (listaPokemonComVantagem.size() == 1) {
                System.out.println("\nO Pokémon que possui vantagem contra o Pokémon " + pokemonDigitado.getNome() + " é: ");
                for (Pokemon pokemon : listaPokemonComVantagem) {
                    System.out.println("Nome: " + pokemon.getNome() + ", Tipo: " + pokemon.getTipo() + ", Raridade: " + pokemon.getRaridade());
                }
            }
            else {
                System.out.println("\nOs Pokémons que possuem vantagem contra o Pokémon " + pokemonDigitado.getNome() + " são: ");
                for (Pokemon pokemon : listaPokemonComVantagem) {
                    System.out.println("Nome: " + pokemon.getNome() + ", Tipo: " + pokemon.getTipo() + ", Raridade: " + pokemon.getRaridade());
                }
            }
            System.out.print("\n");
            listaPokemonComVantagem.clear();
        }
    }
}