package pokemon;

import calculos.OrganizaEquipes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/* Esta é a classe responsável por adicionar, remover e armazenar
*  todos os Pokémons cadastrados na Pokédex, além de realizar as
*  devidas validações acerca para cadastrar um novo Pokémon e
*  também expor os dados de um determinado Pokémon. */

public class CadastroPokemon {

    // Lista que contém todos os Pokémons cadastrados (conhecidos).
    private ArrayList<Pokemon> listaDePokemons = new ArrayList<>();

    // Retorna a lista de Pokémons conhecidos.
    public ArrayList<Pokemon> retornaListaPokemons() {
        return listaDePokemons;
    }

    // Adiona novos Pokémons a lista de Pokémons conhecidos.
    public void adiconarPokemons(Pokemon pokemonCriado) {
        listaDePokemons.add(pokemonCriado);
    }

    // Printa a lista de Pokémons conhecidos.
    public void listarPokemons() {
        if (listaDePokemons.size() == 0) {                                                      // Caso a lista de Pokémons conhecida esteja vazia, o usuário é alertado.
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            System.out.println("\n======== Lista de Pokémons conhecidos ========");
            for (Pokemon p : listaDePokemons) {                                                 // Percorre a lista de Pokémons e printa seus respectivos nomes, tipos e raridades.
                System.out.printf("Nome: %s, Tipo: %s, Raridade: %d\n", p.getNome(), p.getTipo(), p.getRaridade());
            }
            System.out.println("==============================================\n");
        }
    }

    // Remove um Pokémon da lista.
    public void removerPokemon(Scanner scan, OrganizaEquipes organizarEquipes) {
        if (listaDePokemons.size() == 0) {                                                  // Caso a lista de Pokémons conhecida esteja vazia, o usuário é alertado.
            System.out.println("Não há nenhum Pokémon para ser removido!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            String nomePokemon;
            System.out.println("Digite o nome do Pokémon que deseja remover: ");
            nomePokemon = scan.next();                                                      // Recebe o nome do Pokémon a ser removido.

            for (int i = 0; i < listaDePokemons.size(); i++) {
                Pokemon pokemon = listaDePokemons.get(i);

                if (Objects.equals(nomePokemon, pokemon.getNome())) {                       // Busca o Pokémon na lista.
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da lista de Pokémons conhecidos!\n");
                    listaDePokemons.remove(i);                                              // Remove o Pokémon da lista, caso ele faça parte dela.
                    organizarEquipes.remocaoViaCadastro(pokemon);
                    break;

                } else if (i == listaDePokemons.size() - 1) {                               // Se o Pokémon não fizer parte da lista, o usuário é alertado.
                    System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");
                }
            }
        }
    }

    // Verifica se já existe um Pokémon com o nome digitado pelo usuário.
    public boolean checaNomeDisponivel(String nomePokemon, ArrayList<Pokemon> listaDePokemons) {
        for (Pokemon pokemon : listaDePokemons) {                               // Percorre a lista e verifica se existe um Pokémon com o nome selecionado.
            if (Objects.equals(nomePokemon, pokemon.getNome())) {
                return false;                                                   // Caso existe, retorna false.
            }
        } return true;                                                          // Caso não existe, retorna true.
    }

    // Expõe os dados de um Pokémon específico digitado pelo usuário.
    public void visualizarDadosPokemon(Scanner scan) {
        if (listaDePokemons.size() == 0) {                                                  // Caso a lista de Pokémons conhecida esteja vazia, o usuário é alertado.
            System.out.println("Não há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        }
        else {
            String nomePokemon;
            System.out.println("Digite o nome do Pokémon: ");
            nomePokemon = scan.next();                                                                                  // Recebe o nome do Pokémon a ser visualizado.

            if (checaNomeDisponivel(nomePokemon, listaDePokemons)) {                                                    // Verifica se o nome digitado está disponível.
                System.out.println("\nO Pokémon " + nomePokemon + " não está na lista de Pokémons conhecidos!\n");      // Caso esteja, o usuário é alertado de que o Pokémon não existe na lista.
                System.out.println("Verifique se o nome foi digitado corretamente e tente novamente!\n");
            }
            else {                                                                                                      // Caso o nome não esteja disponível,
                for (Pokemon pokemon : listaDePokemons) {                                                               // a lista é percorrida e os dados do Pokémon são printados.
                    if (Objects.equals(nomePokemon, pokemon.getNome())) {
                        pokemon.dadosPokemon();
                        break;
                    }
                }
            }
        }
    }
}