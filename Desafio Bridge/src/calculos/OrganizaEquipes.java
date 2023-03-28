package calculos;

import pokemon.CadastroPokemon;
import pokemon.Pokemon;

import java.util.*;

/* Classe responsável por organizar as equipes A e B para o campeonato Pokémon.
 * As equipes organizadas nesta classe disputarão o campeonato. */

public class OrganizaEquipes {

    private ArrayList<Pokemon> listaEscolhaPokemons; // Lista de Pokémons que estão disponíveis para serem escolhidos.
    private ArrayList<Pokemon> listaEquipeA = new ArrayList<>(); // Lista de Pokémons que pertencem à equipe A.
    private ArrayList<Pokemon> listaEquipeB = new ArrayList<>(); // Lista de Pokémons que pertencem à equipe A.
    private boolean rodarEquipes = true;

    // Seleciona os Pokémons disponíveis para adcionar a uma das equipes.
    public void selecionarPokemon(Scanner scan, ArrayList<Pokemon> listaPokemons, CadastroPokemon cadastrosPokemons) {
        listaEscolhaPokemons = new ArrayList<>(listaPokemons);
        listaEscolhaPokemons.removeAll(listaEquipeA);                       // Inicializa o vetor de Pokémons disponíveis e Remove todos os Pokémons das equipes A e B
        listaEscolhaPokemons.removeAll(listaEquipeB);                       // do vetor de Pokémons disponíveis, visto que estes Pokémons já pertencem a uma equipe.

        rodarEquipes = true;
        String entrada;
        System.out.println(listaPokemons.size());

        while (rodarEquipes) {
            System.out.println("\n============ Pokémons disponíveis ============");
            if (listaEscolhaPokemons.size() != 0) {                         // Caso a lista de Pokémons conhecidos NÃO esteja vazia, printa as opções de Pokémons disponíveis para escolha.
                for (Pokemon p : listaEscolhaPokemons) {
                    System.out.printf("Nome: %s, Tipo: %s, Raridade: %d\n", p.getNome(), p.getTipo(), p.getRaridade());
                }
            } else {                                                        // Caso a lista de Pokémons conhecidos esteja vazia, um aviso é printado para o usuário.
                System.out.println("Nenhum Pokémon disponível!");
                System.out.println("É necessário remover Pokémons de suas equipes");
                System.out.println("para torná-los disponíveis para seleção!");
            }

            mostrarEquipes();
            System.out.println("\nEscolha um Pokémon para adicionar a uma das equipes.");
            System.out.println("Para sair, digite '0000'.");                // A entrada '0000' retorna o usuário para o menu anterior.
            System.out.println("Digite o nome do Pokémon escolhido: ");
            entrada = scan.next();


            while (cadastrosPokemons.checaNomeDisponivel(entrada, listaEscolhaPokemons) && !(verificaSair(entrada))) {  // Caso o Pokémon não esteja na lista de Pokémons
                System.out.println("\nO Pokémon selecionado não está na lista de Pokémons disponíveis!");               // disponíveis, um aviso é printado.
                System.out.println("Para sair, digite '0000'.");
                System.out.println("Por favor, tente novamente: ");
                entrada = scan.next();
            }

            if (verificaSair(entrada)) {            // Caso o valor digitado seja '0000', o usuário para o menu anterior.
                break;
            }
            selecionarEquipe(scan, entrada);        // Chama a função que seleciona a equipe para a qual o Pokémon escolhido será adicionado.
        }
    }

    // Seleciona a equipe para adicionar o Pokémon selecionado.
    public void selecionarEquipe(Scanner scan, String entrada) {
        String equipe;
        System.out.println("Selecione uma equipe (A ou B).");
        System.out.println("Digite o nome da equipe escolhida: ");
        equipe = scan.next();
        equipe = equipe.toUpperCase(Locale.ROOT);

        while (!Objects.equals(equipe, "A") && !Objects.equals(equipe, "B")) {          // Enquanto a opção digitada seja inválida, um aviso é printado.
            System.out.println("Equipe inválida!");
            System.out.println("Por favor, selecione uma equipe válida (A ou B).");
            System.out.println("Digite o nome da equipe escolhida: ");
            equipe = scan.next();
        }
        adicionarPokemonEquipe(entrada, equipe);                            // Caso a opção digitada seja válida, a função para adicionar os Pokémons as equipes é chamada.
    }

    // Adiciona o Pokémon selecionado a equipe selecionada.
    public void adicionarPokemonEquipe(String nomePokemon, String equipe)  {
        if (Objects.equals(equipe, "A") && (listaEquipeA.size() == 5) || (Objects.equals(equipe, "B") && (listaEquipeB.size() == 5))){   // Verifica se a equipe está cheia.
            System.out.println("\nA equipe já possui Pokémons suficientes para o Campeonato!");                                                // Caso esteja, um aviso é printado.
            System.out.println("Remova algum Pokémon para conseguir adicionar outro");
            System.out.println("Pokémon na equipe desejada!");
        }
        else {                                                                      // Caso a equipe selecionada não esteja cheia,
            for (int i = 0; i < listaEscolhaPokemons.size(); i++) {                 // O Pokémon é removido da lista de Pokémons disponíveis e
                Pokemon p;                                                          // adicionado a lista da equipe selecionada.
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

    // Remove um Pokémon de sua respectiva equipe.
    public void removerPokemon(Scanner scan) {
        if (listaEquipeA.size() == 0 && listaEquipeB.size() == 0) {             // Caso ambas as equipes estejam vazias, um aviso é printado e o usuário retorna para o menu anterior.
            System.out.println("Não há nenhum Pokémon para ser removido!");
            System.out.println("Ambas as equipes estão vazias!\n");
        } else {                                                    // Caso contrário, é possível selecionar um Pokémon para ser removido.
            String nomePokemon;
            boolean remover = false;                                // Condição booleana que dita se um Pokémon foi removido das equipes ou não.

            mostrarEquipes();
            System.out.println("Digite o nome do Pokémon que deseja remover: ");
            nomePokemon = scan.next();

            for (int i = 0; i < listaEquipeA.size(); i++) {
                Pokemon pokemonA = listaEquipeA.get(i);

                if (Objects.equals(nomePokemon, pokemonA.getNome())) {                                  // Verifica se o Pokémon pertence à equipe A;
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da equipe A!\n");
                    listaEquipeA.remove(i);
                    remover = true;
                    break;
                }
            }

            for (int j = 0; j < listaEquipeB.size(); j++) {                                             // Verifica se o Pokémon pertence à equipe B;
                Pokemon pokemonB = listaEquipeB.get(j);

                if (Objects.equals(nomePokemon, pokemonB.getNome())) {
                    System.out.println("\nO Pokémon " + nomePokemon + " foi removido da equipe B!\n");
                    listaEquipeB.remove(j);
                    remover = true;
                    break;
                }
            }

            if (!remover) {                                                                 // Caso o Pokémon não tenha sido removido, ele não faz parte de nenhuma equipe.
                    System.out.println("\nO Pokémon " + nomePokemon + " não pertence a nenhuma equipe!\n");

            }
        }
    }

    // Caso um Pokémon estivesse em uma das equipes e fosse removido da lista de Pokémons conhecidos
    // via menu de cadastro, o Pokémon ainda permaneceria na sua respectiva equipe. Este método evita
    // que isso aconteça, pois remove um pokémon específico de qualquer equipe em que ele faça parte.
    // Sempre que um Pokémon é removido via cadastro, esta função é chamada.
    public void remocaoViaCadastro(Pokemon pokemon) {
        listaEscolhaPokemons.remove(pokemon);
        listaEquipeA.remove(pokemon);
        listaEquipeB.remove(pokemon);
    }

    public void adiconarPokemonsListaCampeonato(Pokemon pokemon, ArrayList<Pokemon> listaPokemons) {
        listaEscolhaPokemons = new ArrayList<>(listaPokemons);
        listaEscolhaPokemons.add(pokemon);
    }

    // Printa os Pokémons respectivos de cada equipe.
    public void mostrarEquipes() {
        System.out.println("=================== Equipes ===================");
        System.out.print("Equipe A: ");
        for (Pokemon p : listaEquipeA) {
            System.out.printf(p.getNome() + " ");
        }
        System.out.print("\nEquipe B: ");

        for (Pokemon p : listaEquipeB) {
            System.out.printf(p.getNome() + " ");
        }
        System.out.println("\n===============================================");
    }

    // Retorna a lista de Pokémons da equipe A.
    public ArrayList<Pokemon> retornaEquipeA() {
        return listaEquipeA;
    }

    // Retorna a lista de Pokémons da equipe A.
    public ArrayList<Pokemon> retornaEquipeB() {
        return listaEquipeB;
    }

    // Verifica se o valor digitado é igual ao código de saída '0000'.
    public boolean verificaSair(String entrada) {
        if (Objects.equals(entrada, "0000")) {
            rodarEquipes = false;
            return true;
        }
        return false;
    }

    // Verifica se ambas as equipes estão totalmente preenchidas para o campeonato.
    public boolean verificaTamanhoEquipes() {
        return listaEquipeA.size() == 5 && listaEquipeB.size() == 5;
    }
}