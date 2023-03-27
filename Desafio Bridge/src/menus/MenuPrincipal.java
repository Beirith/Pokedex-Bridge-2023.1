package menus;

import calculos.BuscaVantagemPokemon;
import calculos.CadastroPokemon;
import calculos.CalculoPokemonMediano;
import calculos.OrganizaEquipes;
import pokemon.CriarPokemon;
import pokemon.GeraDadosPokemon;
import pokemon.Pokemon;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class MenuPrincipal {

    private boolean rodar = true;
    private String nomeTreinador;

    private final GeraDadosPokemon gerarDados = new GeraDadosPokemon();
    private final CadastroPokemon cadastrosPokemons = new CadastroPokemon();
    private final CriarPokemon criadorDePokemon = new CriarPokemon();
    private final CalculoPokemonMediano calculaMediano = new CalculoPokemonMediano();
    private final BuscaVantagemPokemon buscaVantagem = new BuscaVantagemPokemon();
    private final OrganizaEquipes organizarEquipes = new OrganizaEquipes();
    private final Scanner scan = new Scanner(System.in);

    public void testes() {
        Pokemon poke1 = new Pokemon();
        poke1.setNome("A");
        poke1.setRaridade(0);

        Pokemon poke2 = new Pokemon();
        poke2.setNome("B");
        poke2.setRaridade(0);

        Pokemon poke3 = new Pokemon();
        poke3.setNome("C");
        poke3.setRaridade(-1);

        Pokemon poke4 = new Pokemon();
        poke4.setNome("D");
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
            int entrada = 0;

            if (nomeTreinador == null) {
                System.out.println("Boas vindas, treinador(a)!");
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
            
            try {
                entrada = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.nextLine();
                entrada = -1;
            }

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
                    menuCampeonato();
                    break;

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

            try {
                entrada1 = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.nextLine();
                entrada1 = -1;
            }

            switch (entrada1) {
                case 0 -> rodarVisualizar = false;
                case 1 -> cadastrosPokemons.listarPokemons();
                case 2 -> cadastrosPokemons.visualizarDadosPokemon(scan);
                case 3 -> {
                    calculaMediano.calcularPokemonMaisMediano(scan, cadastrosPokemons.retornaListaPokemons());
                }
                case 4 -> {
                    buscaVantagem.buscarPokemon(scan, cadastrosPokemons.retornaListaPokemons(), cadastrosPokemons);
                }

                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
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
                case 0 -> rodarCadastro = false;
                case 1 -> {
                    criarPokemon();
                }
                case 2 -> {
                    cadastrosPokemons.removerPokemon(scan, organizarEquipes);
                }
                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
            }
        }
    }

    public void menuCampeonato() {
        boolean rodarCampeonato = true;
        while (rodarCampeonato) {
            int entrada;
            System.out.println("============= Campeonato Pokémon =============");
            System.out.println("1 - Iniciar campeonato");
            System.out.println("2 - Adicionar Pokémon as equipes");
            System.out.println("3 - Remover Pokémon das equipes");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");
            System.out.println("Selecione uma opção: ");

            try {
                entrada = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.nextLine();
                entrada = -1;
            }

            switch (entrada) {
                case 0 -> rodarCampeonato = false;
                //case 1 ->
                case 2 -> organizarEquipes.selecionarPokemon(scan, cadastrosPokemons.retornaListaPokemons(), cadastrosPokemons);
                case 3 -> organizarEquipes.removerPokemon(scan);
                default -> System.out.println("\nOpção inválida! Por favor, tente novamente.\n");
            }
        }
    }

    public void criarPokemon() {
        Pokemon novoPokemon = new Pokemon();
        criadorDePokemon.selecionarNome(novoPokemon, scan, cadastrosPokemons);
        criadorDePokemon.selecionarTipo(novoPokemon, scan, cadastrosPokemons);
        criadorDePokemon.iniciarCriacao(novoPokemon, gerarDados);

    }
}

