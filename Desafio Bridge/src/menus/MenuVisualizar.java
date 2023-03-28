package menus;

import calculos.BuscaVantagemPokemon;
import pokemon.CadastroPokemon;
import calculos.CalculoPokemonMediano;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Classe responsável pela interface do menu de visualização,
*  utilizando as entradas do usuário para acessar as funionalidades do menu. */

public class MenuVisualizar {

    boolean rodarMenu; // Condição para loop do menu.

    public void inicializarMenu(Scanner scan, CadastroPokemon cadastrosPokemons, CalculoPokemonMediano calculaMediano, BuscaVantagemPokemon buscaVantagem) {
        rodarMenu = true;                   // Torna a condição verdadeira para que menu fique em loop.

        while (rodarMenu) {
            int entrada;
            System.out.println("============ Visualizar Pokémons ============");    // Interface do menu inicial.
            System.out.println("1 - Visualizar todos os Pokémons");                 // Printa a lista de todos os Pokédex conhecidos.
            System.out.println("2 - Visualizar dados de um Pokémon");               // Printa os dados de um Pokémons específico definido pelo usuário.
            System.out.println("3 - Visualizar Pokémon mais mediano");              // Printa os dados do Pokémon que possui raridade mais próxima de zero.
            System.out.println("4 - Checar vantagem");                              // Verifica e printa os Pokémons que possuem vantagem em relação a um determinado Pokémon.
            System.out.println("0 - Voltar");                                       // Volta para o menu inicial.
            System.out.println("=============================================");
            System.out.println("Selecione uma opção: ");

            try {                                                                   // A próxima entrada precisa ser um número entre 0 e 3.
                entrada = scan.nextInt();                                           // Dessa forma, utilza-se a função nextInt do Scanner.
            } catch (InputMismatchException e) {                                    // Caso a entrada não for um inteiro, a função nextLine
                scan.nextLine();                                                    // avalia o valor de entrada, para evitar a exceção.
                entrada = -1;                                                       // Atribui um valor inteiro inválido a variável entrada, para que
            }                                                                       // o switch case seguinte selecione 'default'.

            switch (entrada) {                                                      // Avalia os valores de entrada.
                case 0 -> rodarMenu = false;                                        // Encerra o loop, voltando para o menu inicial.
                case 1 -> cadastrosPokemons.listarPokemons();                       // Printa a lista de Pokémons conhecidos.
                case 2 -> cadastrosPokemons.visualizarDadosPokemon(scan);           // Printa os dados de um Pokémon.
                case 3 -> {
                    calculaMediano.calcularPokemonMaisMediano(cadastrosPokemons.retornaListaPokemons());             // Calcula o Pokémon com a raridade mais mediana.
                }
                case 4 -> {
                    buscaVantagem.buscarPokemon(scan, cadastrosPokemons.retornaListaPokemons(), cadastrosPokemons);       // Lista os Pokémons que pussuem vantagem contra um Pokémon.
                }
                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");                         // Alerta entrada inválida.
            }
        }
    }
}