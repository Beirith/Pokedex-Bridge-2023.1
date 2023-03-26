package menus;

import pokemon.Pokemon;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CadastroPokemon {

    private ArrayList<Pokemon> listaDePokemons = new ArrayList<>();

    public ArrayList retornaListaPokemons() {
        return listaDePokemons;
    }

    public void adiconarPokemonsArray(Pokemon pokemonCriado) {
        listaDePokemons.add(pokemonCriado);
    }

    public void listarPokemons() {
        if (listaDePokemons.size() == 0) {
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            for (int i = 0; i < listaDePokemons.size(); i++) {
                Pokemon pokemon = listaDePokemons.get(i);
                System.out.println("Nome: " + pokemon.getNome());
            }
        }
    }

    public void removerPokemon(Scanner scan) {
        if (listaDePokemons.size() == 0) {
            System.out.println("Não há nenhum Pokémon para ser removido!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            String nomePokemon;

            System.out.println("Digite o nome do Pokémon que deseja remover: ");
            nomePokemon = scan.next();

            for (int i = 0; i < listaDePokemons.size(); i++) {
                Pokemon pokemon = listaDePokemons.get(i);

                if (Objects.equals(nomePokemon, pokemon.getNome())) {
                    System.out.println("O Pokémon " + nomePokemon + " foi removido da lista de Pokémons conhecidos!\n");
                    listaDePokemons.remove(i);
                    break;

                } else {
                    System.out.println("O Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                }
            }
        }
    }

    public boolean checaNomeValido(String nomes) {
        if (Objects.equals(nomes, "")) {
            return false;
        }
        return true;
    }

    public boolean checaNomeDisponivel(String nomePokemon) {
        for (Pokemon pokemon : listaDePokemons) {
            if (Objects.equals(nomePokemon, pokemon.getNome())) {
                return false;
            }
        } return true;
    }

    public void visualizarDadosPokemon(Scanner scan) {
        if (listaDePokemons.size() == 0) {
            System.out.println("Não há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            String nomePokemon;

            System.out.println("Digite o nome do Pokémon que deseja visualizar: ");
            nomePokemon = scan.next();

            for (int i = 0; i < listaDePokemons.size(); i++) {
                Pokemon pokemon = listaDePokemons.get(i);

                if (Objects.equals(nomePokemon, pokemon.getNome())) {
                    pokemon.dadosPokemon();
                    break;

                } else {
                    System.out.println("O Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                }
            }
        }
    }

}

