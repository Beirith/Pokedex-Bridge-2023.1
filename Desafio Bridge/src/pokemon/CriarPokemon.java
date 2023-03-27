package pokemon;

import calculos.CadastroPokemon;
import java.util.Scanner;

public class CriarPokemon {

    private boolean rodarTipo = true;

    public void selecionarNome(Pokemon novoPokemon, Scanner scan, CadastroPokemon cadastrosPokemons) {
        String nomePokemon;

        System.out.println("Digite o nome do Pokémon: ");
        nomePokemon = scan.next();

        while (!cadastrosPokemons.checaNomeDisponivel(nomePokemon)) {
            System.out.println("Nome indisponível! Por favor, tente novamente: ");
            nomePokemon = scan.next();
        }

        while (!cadastrosPokemons.checaNomeValido(nomePokemon)) {
            System.out.println("Nome inválido! Por favor, tente novamente: ");
            nomePokemon = scan.next();
        }

        novoPokemon.setNome(nomePokemon);

    }

    public void selecionarTipo(Pokemon novoPokemon, Scanner scan, CadastroPokemon cadastrosPokemons) {
        int selecionaTipo;
        while (rodarTipo) {
            System.out.println("Selecione o tipo do Pokémon");
            System.out.println("1 - Normal ");
            System.out.println("2 - Fogo ");
            System.out.println("3 - Água ");
            System.out.println("4 - Elétrico");
            System.out.println("5 - Grama");
            System.out.println("6 - Gelo");
            System.out.println("7 - Lutador");
            selecionaTipo = scan.nextInt();

            switch (selecionaTipo) {
                case 1 -> {
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
                default -> System.out.println("Tipo inválido! Por favor, tente novamente: ");
            }
        }
        cadastrosPokemons.adiconarPokemonsArray(novoPokemon);
        rodarTipo = true;
    }

    public void iniciarCriacao(Pokemon novoPokemon, GeraDadosPokemon gerarDados) {

        gerarDados.geraRaridade(novoPokemon);
        gerarDados.geraVatangemDesvantagem(novoPokemon);

        System.out.println("\nO Pokémon " + novoPokemon.getNome() + " foi cadastrado com sucesso!\n");
    }
}
