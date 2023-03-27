package calculos;

import pokemon.Pokemon;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CadastroPokemon {

    private ArrayList<Pokemon> listaDePokemons = new ArrayList<>();

    public ArrayList<Pokemon> retornaListaPokemons() {
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
            System.out.println("\n======== Lista de Pokémons conhecidos ========");
            for (Pokemon p : listaDePokemons) {
                System.out.printf("Nome: %s, Tipo: %s, Raridade: %d\n", p.getNome(), p.getTipo(), p.getRaridade());
            }
            System.out.println("==============================================\n");
        }
    }

    public void removerPokemon(Scanner scan, OrganizaEquipes organizarEquipes) {
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
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da lista de Pokémons conhecidos!\n");
                    listaDePokemons.remove(i);
                    organizarEquipes.remocaoCadastro(pokemon);
                    break;

                } else if (i == listaDePokemons.size() - 1) {
                    System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                }
            }
        }
    }

    public boolean checaNomeValido(String nomes) {
        return !Objects.equals(nomes, "");
    }

    public boolean checaNomeDisponivel(String nomePokemon, ArrayList<Pokemon> listaDePokemons) {
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
        }
        else {
            String nomePokemon;
            System.out.println("Digite o nome do Pokémon: ");
            nomePokemon = scan.next();

            if (checaNomeDisponivel(nomePokemon, listaDePokemons)) {
                System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                System.out.println("Verifique se o nome foi digitado corretamente e tente novamente!\n");
            }
            else {
                for (Pokemon pokemon : listaDePokemons) {
                    if (Objects.equals(nomePokemon, pokemon.getNome())) {
                        pokemon.dadosPokemon();
                        break;
                    }
                }
            }
        }
    }
    
    public boolean buscaPokemon(Scanner scan, ArrayList<Pokemon> listaDePokemons, CadastroPokemon cadastrosPokemons) {
        if (listaDePokemons.size() == 0) {
            System.out.println("Não há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
            return false;
        }
        else {
            String nomePokemon;
            nomePokemon = scan.next();

            if (cadastrosPokemons.checaNomeDisponivel(nomePokemon, listaDePokemons)) {
                System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                System.out.println("Verifique se o nome foi digitado corretamente e tente novamente!\n");
                return false;
            }
            else {
                return true;
            }
        }
    }
}

