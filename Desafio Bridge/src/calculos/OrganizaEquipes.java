package calculos;

import pokemon.Pokemon;

import java.util.*;

public class OrganizaEquipes {

    private ArrayList<Pokemon> listaEscolhaPokemons;
    private ArrayList<Pokemon> listaEquipeA = new ArrayList<>();
    private ArrayList<Pokemon> listaEquipeB = new ArrayList<>();
    private boolean rodarEquipes = true;


    public void selecionarPokemon(Scanner scan, ArrayList<Pokemon> listaPokemons, CadastroPokemon cadastrosPokemons) {
        listaEscolhaPokemons = new ArrayList<>(listaPokemons);
        listaEscolhaPokemons.removeAll(listaEquipeA);
        listaEscolhaPokemons.removeAll(listaEquipeB);

        rodarEquipes = true;
        String entrada;
        System.out.println(listaPokemons.size());

        while (rodarEquipes) {
            System.out.println("\n=========== Pokémons disponíveis ===========");
            if (listaPokemons.size() != 0) {
                for (Pokemon p : listaEscolhaPokemons) {
                    System.out.printf("Nome: %s, Tipo: %s, Raridade: %d\n", p.getNome(), p.getTipo(), p.getRaridade());
                }
            } else {
                System.out.println("Nenhum Pokémon disponível!");
                System.out.println("É possível remover Pokémons qualquer equipe");
                System.out.println("Para torná-los disponíveis para seleção!\n");
            }

            System.out.println("==============================================");
            mostrarEquipes();
            System.out.println("\nEscolha um Pokémon para adicionar a uma das equipes.");
            System.out.println("Para sair, digite '0000'.");
            System.out.println("Digite o nome do Pokémon escolhido: ");
            entrada = scan.next();

            if (verificaSair(entrada)) {
                break;
            }

            while (cadastrosPokemons.checaNomeDisponivel(entrada, listaEscolhaPokemons) && !(verificaSair(entrada))) {
                System.out.println("\nO Pokémon selecionado não está na lista!");
                System.out.println("Para sair, digite '0000'.");
                System.out.println("Por favor, tente novamente: ");
                entrada = scan.next();
            }

            if (verificaSair(entrada)) {
                break;
            }
            selecionarEquipe(scan, entrada);
        }
    }

    public boolean verificaSair(String entrada) {
        if (Objects.equals(entrada, "0000")) {
            rodarEquipes = false;
            return true;
        }
        return false;
    }

    public void selecionarEquipe(Scanner scan, String entrada) {
        String equipe;
        System.out.println("Selecione uma equipe (A ou B).");
        System.out.println("Digite o nome da equipe escolhida: ");
        equipe = scan.next();
        equipe = equipe.toUpperCase(Locale.ROOT);

        while (!Objects.equals(equipe, "A") && !Objects.equals(equipe, "B")) {
            System.out.println("Equipe inválida!");
            System.out.println("Por favor, selecione uma equipe válida (A ou B).");
            System.out.println("Digite o nome da equipe escolhida: ");
            equipe = scan.next();
        }
        adicionarPokemon(scan, entrada, equipe);
    }

    public void adicionarPokemon(Scanner scan, String nomePokemon, String equipe)  {
        if (Objects.equals(equipe, "A") && (listaEquipeA.size() == 5)) {
            System.out.println("\nA equipe A já possui Pokémons suficientes para o Campeonato!");
            System.out.println("Remova algum Pokémon para conseguir adicionar outro");
            System.out.println("Pokémon na equipe!");
        }
        else if (Objects.equals(equipe, "B") && (listaEquipeB.size() == 5)) {
            System.out.println("\nA equipe B já possui Pokémons suficientes para o Campeonato!");
            System.out.println("Remova algum Pokémon para conseguir adicionar outro");
            System.out.println("Pokémon na equipe!");
        }
        else {
            for (int i = 0; i < listaEscolhaPokemons.size(); i++) {
                Pokemon p;
                p = listaEscolhaPokemons.get(i);
                if (Objects.equals(nomePokemon, p.getNome())) {
                    listaEscolhaPokemons.remove(p);
                    if (Objects.equals(equipe, "A")) {
                        listaEquipeA.add(p);
                    }
                    else {
                        listaEquipeB.add(p);
                    }
                }
            }
        }

    }

    public void removerPokemon(Scanner scan) {
        if (listaEquipeA.size() == 0 && listaEquipeB.size() == 0) {
            System.out.println("Não há nenhum Pokémon para ser removido!");
            System.out.println("Ambas as equipes estão vazias!\n");
        } else {
            String nomePokemon;
            boolean remover = false;

            System.out.println("Digite o nome do Pokémon que deseja remover: ");
            nomePokemon = scan.next();

            for (int i = 0; i < listaEquipeA.size(); i++) {
                Pokemon pokemonA = listaEquipeA.get(i);

                if (Objects.equals(nomePokemon, pokemonA.getNome())) {
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da equipe A!\n");
                    listaEquipeA.remove(i);
                    remover = true;
                    break;
                }
            }

            for (int j = 0; j < listaEquipeB.size(); j++) {
                Pokemon pokemonB = listaEquipeB.get(j);

                if (Objects.equals(nomePokemon, pokemonB.getNome())) {
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da equipe B!\n");
                    listaEquipeB.remove(j);
                    remover = true;
                    break;
                }
            }

            if (!remover) {
                    System.out.println("\nO Pokémon " + nomePokemon + " não pertence a nenhuma equipe!\n");

            }
        }
    }

    public void remocaoCadastro(Pokemon pokemon) {
        listaEscolhaPokemons.remove(pokemon);
        listaEquipeA.remove(pokemon);
        listaEquipeB.remove(pokemon);
    }

    public void mostrarEquipes() {
        System.out.print("|| Equipe A || ");
        for (Pokemon p : listaEquipeA) {
            System.out.printf(p.getNome() + " || ");
        }
        System.out.print("\n|| Equipe B || ");

        for (Pokemon p : listaEquipeB) {
            System.out.printf(p.getNome() + " || ");
        }
        System.out.println("\n==============================================");

    }

    public boolean verificaTamanhoEquipes() {
        return listaEquipeA.size() == 5 && listaEquipeB.size() == 5;
    }
}
