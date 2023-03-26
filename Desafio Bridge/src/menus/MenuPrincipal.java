package menus;

import calculos.CadastroPokemon;
import calculos.CalculoPokemonMediano;
import pokemon.CriarPokemon;
import pokemon.GeraDadosPokemon;
import pokemon.Pokemon;

import java.util.Objects;
import java.util.Scanner;

public class MenuPrincipal {

    private boolean rodar = true;
    private String nomeTreinador;

    private GeraDadosPokemon gerarDados = new GeraDadosPokemon();
    private CadastroPokemon cadastrosPokemons = new CadastroPokemon();
    private CriarPokemon criadorDePokemon = new CriarPokemon();
    private CalculoPokemonMediano calculaMediano = new CalculoPokemonMediano();
    private Scanner scan = new Scanner(System.in);

    public void testes() {
        Pokemon poke1 = new Pokemon();
        poke1.setNome("A");
        poke1.setRaridade(-5);

        Pokemon poke2 = new Pokemon();
        poke2.setNome("B");
        poke2.setRaridade(5);

        Pokemon poke3 = new Pokemon();
        poke3.setNome("C");
        poke3.setRaridade(-1);

        Pokemon poke4 = new Pokemon();
        poke4.setNome("E");
        poke4.setRaridade(-1);

        Pokemon poke5 = new Pokemon();
        poke5.setNome("E");
        poke5.setRaridade(-1);

        Pokemon poke6 = new Pokemon();
        poke6.setNome("F");
        poke6.setRaridade(-1);

        cadastrosPokemons.adiconarPokemonsArray(poke1);
        cadastrosPokemons.adiconarPokemonsArray(poke2);
        cadastrosPokemons.adiconarPokemonsArray(poke3);
        cadastrosPokemons.adiconarPokemonsArray(poke4);
        cadastrosPokemons.adiconarPokemonsArray(poke5);
        cadastrosPokemons.adiconarPokemonsArray(poke6);
    }

    public void menuInicial() {
        while (rodar) {
            String nomeTreinadorEntrada;
            int entrada;

            if (nomeTreinador == null) {
                System.out.println("Boas vindas, treinador!");
                System.out.println("Por favor, digite o seu nome: ");
                nomeTreinadorEntrada = scan.nextLine();

                while (Objects.equals(nomeTreinadorEntrada, "")) {
                    System.out.println("Nome inválido! Por favor, tente novamente: ");
                    nomeTreinadorEntrada = scan.nextLine();
                }

                nomeTreinador = nomeTreinadorEntrada;
            }

            System.out.println("============ Menu Pokédex ============");
            System.out.println("1 - Visualizar Pokémons");
            System.out.println("2 - Cadastrar Pokémons");
            System.out.println("3 - Campeonato Pokémon");
            System.out.println("0 - Sair");
            System.out.println("======================================");
            System.out.println("Selecione uma opção: ");
            entrada = scan.nextInt();

            switch (entrada) {
                case 0:
                    System.out.println("Fechando Pokédex...");
                    System.out.printf("Até logo, %s!", nomeTreinador);
                    rodar = false;
                    break;

                case 1:
                    menuVisualizar();
                    break;

                case 2:
                    menuCadastro();
                    break;

                case 3:
                    assert true;

                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
            }
        }
    }

    public void menuVisualizar() {
        boolean rodarVisualizar = true;
        while (rodarVisualizar) {
            int entrada1;
            System.out.println("============ Visualizar Pokémons ============");
            System.out.println("1 - Visualizar todos os Pokémons");
            System.out.println("2 - Visualizar dados de um Pokémon");
            System.out.println("3 - Visualizar Pokémon mais mediano");
            System.out.println("4 - Checar vantagem");
            System.out.println("0 - Voltar");
            System.out.println("=============================================");
            System.out.println("Selecione uma opção: ");
            entrada1 = scan.nextInt();

            switch (entrada1) {
                case 0:
                    rodarVisualizar = false;
                    break;

                case 1:
                   cadastrosPokemons.listarPokemons();
                    break;

                case 2:
                    cadastrosPokemons.visualizarDadosPokemon(scan);
                    break;

                case 3:
                    calculaMediano.calcularPokemonMaisMediano(scan, cadastrosPokemons.retornaListaPokemons());
                    break;

                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
            }
        }
    }

    public void menuCadastro() {
        boolean rodarCadastro = true;
        while (rodarCadastro) {
            int entrada2;
            System.out.println("============= Cadastrar Pokémons =============");
            System.out.println("1 - Cadastrar novo Pokémon");
            System.out.println("2 - Remover Pokémon");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");
            System.out.println("Selecione uma opção: ");
            entrada2 = scan.nextInt();

            switch (entrada2) {
                case 0:
                    rodarCadastro = false;
                    break;

                case 1:
                    criarPokemon();
                    rodarCadastro = false;
                    break;

                case 2:
                    cadastrosPokemons.removerPokemon(scan);
                    rodarCadastro = false;
                    break;

                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
            }
        }
    }

    public void criarPokemon() {
        Pokemon novoPokemon = new Pokemon();
        criadorDePokemon.iniciarCriacao(novoPokemon, gerarDados);
        criadorDePokemon.selecionarNome(novoPokemon, scan, cadastrosPokemons);
        criadorDePokemon.selecionarTipo(novoPokemon, scan, cadastrosPokemons);

    }
}

