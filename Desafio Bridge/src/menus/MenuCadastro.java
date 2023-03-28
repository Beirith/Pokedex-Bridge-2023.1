package menus;

import pokemon.CadastroPokemon;
import calculos.OrganizaEquipes;
import pokemon.CriarPokemon;
import pokemon.GeraDadosPokemon;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Classe responsável pela interface do menu de cadastro,
 * utilizando as entradas do usuário para acessar as funionalidades do menu. */

public class MenuCadastro {
    boolean rodarMenu; // Condição para loop do menu.

    public void inicializarMenu(Scanner scan, CadastroPokemon cadastrosPokemons, CriarPokemon criadorDePokemon, OrganizaEquipes organizarEquipes, GeraDadosPokemon gerarDados) {
        rodarMenu = true;               // Torna a condição verdadeira para que menu fique em loop.

        while (rodarMenu) {
            int entrada;
            System.out.println("============= Cadastrar Pokémons =============");           // Interface do menu inicial.
            System.out.println("1 - Cadastrar novo Pokémon");                               // Cadastra um novo Pokémon e adiciona-o a lista de Pokémons conhecidos.
            System.out.println("2 - Remover Pokémon");                                      // Remove um Pokémon da lista de Pokémons conhecidos.
            System.out.println("0 - Voltar");                                               // Volta para o menu inicial.
            System.out.println("==============================================");
            System.out.println("Selecione uma opção: ");

            try {                                                                           // A próxima entrada precisa ser um número entre 0 e 3.
                entrada = scan.nextInt();                                                   // Dessa forma, utilza-se a função nextInt do Scanner.
            } catch (InputMismatchException e) {                                            // Caso a entrada não for um inteiro, a função nextLine
                scan.nextLine();                                                            // avalia o valor de entrada, para evitar a exceção.
                entrada = -1;                                                               // Atribui um valor inteiro inválido a variável entrada, para que
            }                                                                               // o switch case seguinte selecione 'default'.

            switch (entrada) {                                                              // Encerra o loop, voltando para o menu inicial.
                case 0 -> rodarMenu = false;
                case 1 -> {
                    criadorDePokemon.criarPokemon(scan, cadastrosPokemons, criadorDePokemon, gerarDados, organizarEquipes);  // Efetua o cadastro do Pokémon.
                }
                case 2 -> {
                    cadastrosPokemons.removerPokemon(scan, organizarEquipes);                                                // Remove o Pokémon.
                }
                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");                            // Alerta entrada inválida.
            }
        }
    }
}