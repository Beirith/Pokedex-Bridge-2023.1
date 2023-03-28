package main;

import calculos.*;
import menus.MenuCadastro;
import menus.MenuCampeonato;
import menus.MenuVisualizar;
import pokemon.CadastroPokemon;
import pokemon.CriarPokemon;
import pokemon.GeraDadosPokemon;
import pokemon.Pokemon;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/* Esta é a classe Pokédex. Ela é responsável por todas as funcionalidades do programa.
*  A classe possui diversos objetos responsáveis por funionalidades da Pokédex, e também possui
*  métodos que chamam outros métodos de seus determinados objetos. */

public class Pokedex {

    private boolean rodarMenuInicial = true; // Condição para que o loop do menu inicial continue rodando e, desta forma, a Pokédex continue aberta.
    private String nomeTreinador; // Nome do treinador, que é requisitado ao inicializar a Pokédex.
    private final Scanner scan = new Scanner(System.in); // Objeto Scanner da Pokédex, utilizado para receber as entradas do usuário.

    private final MenuCadastro menuCadastro = new MenuCadastro(); // Objeto que trata do menu de cadastro de Pokémons.
    private final MenuVisualizar menuVisualizar = new MenuVisualizar(); // Objeto que trata do menu de visualização.
    private final MenuCampeonato menuCampeonato  = new MenuCampeonato(); // Objeto que trata do menu de campeonato Pokémon.

    private final GeraDadosPokemon gerarDados = new GeraDadosPokemon(); // Objeto que gera a raridade, vantagens e desvantagens dos Pokémons.
    private final CadastroPokemon cadastrosPokemons = new CadastroPokemon(); // Objeto responsável por realizar verificações, armazenar e cadastrar Pokémons.
    private final CriarPokemon criadorDePokemon = new CriarPokemon(); // Objeto responsável por determinar o tipo e criar o objeto Pokémon.
    private final CalculoPokemonMediano calculaMediano = new CalculoPokemonMediano(); // Objeto responsável por calcular o Pokémon mais mediano conhecido.
    private final BuscaVantagemPokemon buscaVantagem = new BuscaVantagemPokemon(); // Objeto responsável por buscar Pokémons que possuam vantagem contra determinado Pokémon.
    private final OrganizaEquipes organizarEquipes = new OrganizaEquipes(); // Objeto responsável por organizar equipes (A e B) para o campeonato Pokémon.
    private final CampeonatoPokemon campeonatoPokemon = new CampeonatoPokemon(); // Objeto responsável por realizar o campeonato Pokémon.

    // Método utilizado para validações. Gera 20 Pokémons aleatórios.
    public void testes() {
        String[] tipos = {"Normal", "Água", "Fogo", "Elétrico", "Grama", "Gelo", "Lutador"};
        int tipoIndex;

        for (int i = 0; i < 20; i++) {
            Pokemon pokemon = new Pokemon();
            pokemon.setNome("teste" + i);

            Random indexAleatorio = new Random();
            tipoIndex = indexAleatorio.nextInt(tipos.length);

            pokemon.setTipo(tipos[tipoIndex]);

            gerarDados.geraVatangemDesvantagem(pokemon);
            gerarDados.geraRaridade(pokemon);
            cadastrosPokemons.adiconarPokemons(pokemon);
            organizarEquipes.adiconarPokemonsListaCampeonato(pokemon, cadastrosPokemons.retornaListaPokemons());
        }
    }

    // Método que roda o menu inicial da Pokédex. É a partir deste menu que os outros menus são iniciados.
    // O loop do menu inicial é o que mantém a Pokédex ativada. Caso ele seja encerrado, a Pokédex é fechada.
    public void menuInicial() {
        while (rodarMenuInicial) {
            String nomeTreinadorEntrada;
            int entrada;                                                  // Variável de entrada, responsável por armazenar a entrada do usuário.

            if (nomeTreinador == null) {                                  // Se o nome do treinado for null, então é o primeiro acesso do treinador.
                System.out.println("Boas vindas, treinador(a)!");
                System.out.println("Por favor, digite o seu nome: ");
                nomeTreinadorEntrada = scan.next();
                nomeTreinador = nomeTreinadorEntrada;                     // Atribui o nome digitado pelo usuário ao atributo nomeTreinador.
            }

            System.out.println("============ Menu Pokédex ============"); // Interface do menu inicial.
            System.out.println("1 - Visualizar Pokémons");                // Acesso ao menu de visualização.
            System.out.println("2 - Cadastrar Pokémons");                 // Acesso ao menu de cadastro.
            System.out.println("3 - Campeonato Pokémon");                 // Acesso ao menu de campeonato.
            System.out.println("0 - Sair");                               // Fecha a Pokédex, encerrando a aplicação.
            System.out.println("======================================");
            System.out.println("Selecione uma opção: ");

            try {                                                          // A próxima entrada precisa ser um número entre 0 e 3.
                entrada = scan.nextInt();                                  // Dessa forma, utilza-se a função nextInt do Scanner.
            } catch (InputMismatchException e) {                           // Caso a entrada não for um inteiro, a função nextLine
                scan.nextLine();                                           // avalia o valor de entrada, para evitar a exceção.
                entrada = -1;                                              // Atribui um valor inteiro inválido a variável entrada, para que
            }                                                              // o switch case seguinte selecione 'default'.

            switch (entrada) {                                             // Avalia os valores de entrada.
                case 0 -> {
                    System.out.println("Fechando Pokédex...");
                    System.out.printf("Até logo, %s!", nomeTreinador);
                    rodarMenuInicial = false;                              // Altera o atributo rodarMenuInicial para false, encerrando o loop e fechando a Pokédex.
                }
                case 1 -> menuVisualizar();                                                         // Inicializa o menu de visualização.
                case 2 -> menuCadastro();                                                           // Inicializa o menu de cadastro.
                case 3 -> menuCampeonato();                                                         // Inicializa o menu de campeonato.
                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");   // Printa um aviso acerca do valor inválido digitado como entrada.
            }
        }
    }

    // Inicializa o menu de visualização.
    public void menuVisualizar() {
        menuVisualizar.inicializarMenu(scan, cadastrosPokemons, calculaMediano, buscaVantagem);
    }

    // Inicializa o menu de cadastro.
    public void menuCadastro() {
        menuCadastro.inicializarMenu(scan, cadastrosPokemons, criadorDePokemon, organizarEquipes, gerarDados);
    }

    // Inicializa o menu de campeonato.
    public void menuCampeonato() {
        menuCampeonato.inicializarMenu(scan, campeonatoPokemon, organizarEquipes, cadastrosPokemons);
    }
}