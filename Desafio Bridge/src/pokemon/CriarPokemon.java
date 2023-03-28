package pokemon;

import calculos.OrganizaEquipes;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/* Esta é a classe CriarPokemon. Ela é responsável por criar o objeto Pokémon, obter o nome e o tipo do
*  Pokémon a partir de entradas do usuário e também como chamar as funções que geram a raridade
*  e as vantagens e desvantagens, para utilizar estes dados na criação do objeto Pokémon. */

public class CriarPokemon {

    // Método que cria o Pokémon.
    public void criarPokemon(Scanner scan, CadastroPokemon cadastrosPokemons, CriarPokemon criadorDePokemon, GeraDadosPokemon gerarDados, OrganizaEquipes organizarEquipes) {
        Pokemon novoPokemon = new Pokemon();                                                                            // Cria um nobo objeto Pókemon.
        criadorDePokemon.selecionarNome(novoPokemon, scan, cadastrosPokemons);                                          // Seleciona o nome.
        criadorDePokemon.selecionarTipo(novoPokemon, scan);                                                             // Seleciona o tipo.
        criadorDePokemon.obterDados(novoPokemon, gerarDados);                                                           // Obtem os dados de raridade, vantagens e desvantagens.
        cadastrosPokemons.adiconarPokemons(novoPokemon);                                                                // Adiciona o Pokémon a lista de Pokémons conhecidos.
        organizarEquipes.adiconarPokemonsListaCampeonato(novoPokemon, cadastrosPokemons.retornaListaPokemons());        // Adiciona o Pokémon a lista de campeonato.
        System.out.println("\nO Pokémon " + novoPokemon.getNome() + " foi cadastrado com sucesso!\n");
    }

    // Método utilizado para selecionar o nome do Pokémon a partir da entrada do usuário.
    public void selecionarNome(Pokemon novoPokemon, Scanner scan, CadastroPokemon cadastrosPokemons) {
        String nomePokemon;

        System.out.println("Digite o nome do Pokémon: ");
        nomePokemon = scan.next();

        // Enquanto o nome do Pokémon não estiver disponível, a entrada não será aceita.
        while (!cadastrosPokemons.checaNomeDisponivel(nomePokemon, cadastrosPokemons.retornaListaPokemons())) {
            System.out.println("Nome indisponível! Por favor, tente novamente: ");
            nomePokemon = scan.next();
        }

        // O nome do Pokémon não pode ser '0000', pois este é um código utilizado para voltar de alguns menus da aplicação.
        while (Objects.equals(nomePokemon.toUpperCase(), "0000")) {
            System.out.println("Nome Inválido! Por favor, tente novamente: ");
            nomePokemon = scan.next();
        }

        novoPokemon.setNome(nomePokemon); // Caso o nome esteja disponível, ele é atribuido ao Pokémon.
    }

    // Método utilizado para selecionar o tipo do Pokémon.
    public void selecionarTipo(Pokemon novoPokemon, Scanner scan) {
        int selecionaTipo;
        boolean rodarTipo = true;                                       // Condição que mantém o loop ativo. É desativada caso alguma entrada válida seja digitada.
        while (rodarTipo) {
            System.out.println("Selecione o tipo do Pokémon");
            System.out.println("1 - Normal ");
            System.out.println("2 - Fogo ");
            System.out.println("3 - Água ");
            System.out.println("4 - Elétrico");
            System.out.println("5 - Grama");
            System.out.println("6 - Gelo");
            System.out.println("7 - Lutador");

            try {                                                       // A próxima entrada precisa ser um número entre 0 e 3.
                selecionaTipo = scan.nextInt();                         // Dessa forma, utilza-se a função nextInt do Scanner.
            } catch (InputMismatchException e) {                        // Caso a entrada não for um inteiro, a função nextLine
                scan.nextLine();                                        // avalia o valor de entrada, para evitar a exceção.
                selecionaTipo = -1;                                     // Atribui um valor inteiro inválido a variável entrada, para que
            }                                                           // o switch case seguinte selecione 'default'.

            switch (selecionaTipo) {
                case 1 -> {                                             // Caso a entrada seja válida, atribui o tipo selecionado ao Pokémon e encerra o loop.
                    novoPokemon.setTipo("Normal");
                    rodarTipo = false;
                }
                case 2 -> {
                    novoPokemon.setTipo("Fogo");
                    rodarTipo = false;
                }
                case 3 -> {
                    novoPokemon.setTipo("Água");
                    rodarTipo = false;
                }
                case 4 -> {
                    novoPokemon.setTipo("Elétrico");
                    rodarTipo = false;
                }
                case 5 -> {
                    novoPokemon.setTipo("Grama");
                    rodarTipo = false;
                }
                case 6 -> {
                    novoPokemon.setTipo("Gelo");
                    rodarTipo = false;
                }
                case 7 -> {
                    novoPokemon.setTipo("Lutador");
                    rodarTipo = false;
                }
                default -> System.out.println("Tipo inválido! Por favor, tente novamente: "); // Caso a entrada seja inválida, printa um aviso e NÃO encerra o loop.
            }
        }
    }

    // Método utilizado para gerar e setar os dados de rarirdade, vantagem e desvantagens do Pókemon.
    public void obterDados(Pokemon novoPokemon, GeraDadosPokemon gerarDados) {
        gerarDados.geraRaridade(novoPokemon);                           // Chama a função que gera o valor de raridade e atribui-o ao Pokémon.
        gerarDados.geraVatangemDesvantagem(novoPokemon);                // Chama a função que gera as vantagens e desvantagens e atribui-os ao Pokémon.
    }
}