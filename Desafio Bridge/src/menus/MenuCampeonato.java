package menus;

import pokemon.CadastroPokemon;
import calculos.CampeonatoPokemon;
import calculos.OrganizaEquipes;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Classe responsável pela interface do menu de campeonato,
 *  utilizando as entradas do usuário para acessar as funionalidades do menu. */

public class MenuCampeonato {

    boolean rodarCampeonato; // Condição para loop do menu.

    public void inicializarMenu(Scanner scan, CampeonatoPokemon campeonatoPokemon, OrganizaEquipes organizarEquipes, CadastroPokemon cadastrosPokemons) {

        rodarCampeonato = true;             // Torna a condição verdadeira para que menu fique em loop.

        while (rodarCampeonato) {
        int entrada;
        System.out.println("============= Campeonato Pokémon =============");   // Interface do menu de campeonato.
        System.out.println("1 - Iniciar campeonato");                           // Realiza o campeonato Pokémon.
        System.out.println("2 - Adicionar Pokémon as equipes");                 // Adiciona Pokémons a equipes A ou B.
        System.out.println("3 - Remover Pokémon das equipes");                  // Remove Pokémons das equipes A ou B.
        System.out.println("0 - Voltar");                                       // Volta para o menu inicial.
        System.out.println("==============================================");
        System.out.println("Selecione uma opção: ");

        try {                                                                   // A próxima entrada precisa ser um número entre 0 e 3.
            entrada = scan.nextInt();                                           // Dessa forma, utilza-se a função nextInt do Scanner.
        } catch (InputMismatchException e) {                                    // Caso a entrada não for um inteiro, a função nextLine
            scan.nextLine();                                                    // avalia o valor de entrada, para evitar a exceção.
            entrada = -1;                                                       // Atribui um valor inteiro inválido a variável entrada, para que
        }                                                                       // o switch case seguinte selecione 'default'.

        switch (entrada) { // Avalia os valores de entrada.
            case 0 -> rodarCampeonato = false;                                                                                          // Encerra o loop, voltando para o menu inicial.
            case 1 -> campeonatoPokemon.iniciarCampeonato(scan, organizarEquipes);                                                      // Realiza o cálculo do campeonato.
            case 2 -> organizarEquipes.selecionarPokemon(scan, cadastrosPokemons.retornaListaPokemons(), cadastrosPokemons);            // Adiciona Pokémons a equipes A e B para o campeonato.
            case 3 -> organizarEquipes.removerPokemon(scan);                                                                            // Remove Pokémons das equipes A e B para o campeonato.
            default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");                                           // Alerta entrada inválida.
            }
        }
    }
}