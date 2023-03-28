package calculos;

import pokemon.Pokemon;

import java.util.*;

/* Classe responsável por cálcular as Batalhas do
*  campeonato Pokémon. */

public class CampeonatoPokemon {

    private ArrayList<Pokemon> equipeA;
    private ArrayList<Pokemon> equipeB;
    int rodadas = 0;

    // Verifica se ambas as equipes estão cheias e, caso estejam, se o usuário
    // quer iniciar o campeonato com as equipes que estão formadas.
    public void iniciarCampeonato(Scanner scan, OrganizaEquipes organizarEquipes) {
        if (!organizarEquipes.verificaTamanhoEquipes()) {                                   // Verifica se ambas as equipes estão totalmente preenchidas.
            System.out.println("As equipes ainda não estão totalmente preenchidas!");       // Caso não estejam, um erro é printado e o usuário retorna para o
            System.out.println("Cada equipe deve possuir 5 pokémons para");                 // menu de campeonato.
            System.out.println("que o torneio seja iniciado!\n");
        } else{                                                                             // Caso estejam, verifica se o usuário deseja iniciar o campeonato
            String entrada;                                                                 // com as equipes que estão formadas. Caso o usuário não deseje
            organizarEquipes.mostrarEquipes();                                              // inciar o campeonato, ele retorna para o menu de campeonato.
            System.out.println("Deseja iniciar o torneio com estas equipes? (S/N)");
            entrada = scan.next();
            entrada = entrada.toUpperCase(Locale.ROOT);

            while (!Objects.equals(entrada, "S") && !Objects.equals(entrada, "N")) {        // Verifica se as entradas do usuário são válidas.
                System.out.println("Valor inválido! Por favor, selecione um valor válido.");
                System.out.println("Deseja iniciar o torneio com estas equipes? (S/N)");
                entrada = scan.next();
            }

            if (entrada.equals("S")) {                                                            // Caso o usuário deseje iniciar o campeonato,
                equipeA = new ArrayList<>(organizarEquipes.retornaEquipeA());                     // a função iniciar batalhas é chamada.
                equipeB = new ArrayList<>(organizarEquipes.retornaEquipeB());
                rodadas = 0;
                iniciarBatalhas();
            }
            else {
                assert true;
            }
        }
    }

    // Realiza as batalhas até que uma equipe não possua mais Pokémons.
    public void iniciarBatalhas() {
        while (equipeA.size() != 0 && equipeB.size() != 0){                 // Enquanto uma das equipes não tiver nenhum Pokémon, as batalhas continuam.
            Pokemon pokemonA = sortearPokemon(equipeA);
            Pokemon pokemonB = sortearPokemon(equipeB);                     // Pokémons de cada equipe são sorteados para batalharem entre si.
            batalhaPokemon(pokemonA, pokemonB);
        }
        if (equipeA.size() == 0) {                                          // Caso a equipe A não possua mais Pokémons, a equipe B é declarada vencedora.
            System.out.println("\nA equipe B vendeu o campeonato!");
            System.out.println("Restaram " + equipeB.size() + " Pokémons na equipe!");
        }

        if (equipeB.size() == 0) {                                          // Caso a equipe B não possua mais Pokémons, a equipe B é declarada vencedora.
            System.out.println("\nA equipe A vendeu o campeonato!");
            System.out.println("Restaram " + equipeA.size() + " Pokémons na equipe!");
        }
    }

    // Sorteia um Pokémon aleatório de uma equipe.
    public Pokemon sortearPokemon(ArrayList<Pokemon> equipe) {
        Random sorteiaPokemon = new Random();
        int indicePokemonSorteado = sorteiaPokemon.nextInt(equipe.size());
        return equipe.get(indicePokemonSorteado);
    }

    // Calcula a pontução de um Pokémon em relação a outro.
    public int calcularPontosPokemon(Pokemon pokemon1, Pokemon pokemon2) {
        int pontosPokemon1 = 0;

        for (String vantagemA : pokemon1.getVantagem()) {                   // Caso o tipo do Pokémon2 esteja na lista de vantagens do Pokémon1,
            if (Objects.equals(vantagemA, pokemon2.getTipo())) {            // A pontuação do Pokémon1 em relação ao Pokémon2 sobe 1 ponto.
                pontosPokemon1++;
            }
        }
        for (String desvantagemA : pokemon1.getDesvantagem()) {             // Caso o tipo do Pokémon2 esteja na lista de desvantagens do Pokémon1
            if (Objects.equals(desvantagemA, pokemon2.getTipo())) {         // pontuação do Pokémon1 em relação ao PokémonB desce 1 ponto.
                pontosPokemon1--;
            }
        }

        return pontosPokemon1;                                              // Retorna a pontuação do Pokémon1 em relação ao Pokémon2.
    }

    // Realiza as batalhas entre dois Pokémons aleatórios das equipes.
    public void batalhaPokemon(Pokemon pokemonA, Pokemon pokemonB) {
        rodadas++;      // Número da rodada, aumenta 1 a cada batalha.
        int pontosPokemonA = calcularPontosPokemon(pokemonA, pokemonB);         // Calcula a pontuação que cada Pokémon possue sobre o outro.
        int pontosPokemonB = calcularPontosPokemon(pokemonB, pokemonA);

        System.out.println("Rodada número " + rodadas +"!");
        System.out.println("Pokémon " + pokemonA.getNome() + " da Equipe A vs Pokémon " + pokemonB.getNome() + " da Equipe B!");

        if (pontosPokemonA > pontosPokemonB) {                                          // Remove o perdedor de sua respectiva equipe no campeonato.
            System.out.println("Pokémon " + pokemonA.getNome() + " vence a batalha!");
            equipeB.remove((pokemonB));                                                 // Remove o perdedor de sua respectiva equipe no campeonato.
        }
        else if (pontosPokemonB > pontosPokemonA) {                                     // Caso a pontuação do PokémonB seja maior, ele vence a batalha.
            System.out.println("Pokémon " + pokemonB.getNome() + " vence a batalha!");
            equipeA.remove((pokemonA));                                                 // Remove o perdedor de sua respectiva equipe no campeonato.
        }
        else {                                                                          // Caso as pontuações sejam iguais, a função calculaDesempate é chamada.
            calculaDesempate(pokemonA, pokemonB);
        }
    }

    // Calcula o vencedor de uma batalha quando ambos Pokémons possuem a mesma pontuação.
    public void calculaDesempate(Pokemon pokemonA, Pokemon pokemonB) {
        if (pokemonA.getRaridade() > pokemonB.getRaridade()) {                          // Caso a raridade do PokémonA seja maior que a raridade do PokémonB ele vence a batalha.
            System.out.println("Pokémon " + pokemonA.getNome() + " vence a batalha!");
            equipeB.remove((pokemonB));                                                 // Remove o perdedor de sua respectiva equipe no campeonato.
        }
        else if (pokemonB.getRaridade() > pokemonA.getRaridade()) {                     // Caso contrário, o PokémonB vence.
            System.out.println("Pokémon " + pokemonB.getNome() + " vence a batalha!");
            equipeA.remove((pokemonA));                                                 // Remove o perdedor de sua respectiva equipe no campeonato.
        }
        else {                                                                          // Caso as raridades sejam iguais, o vencedor é sorteado aleatoriamente.
            ArrayList<Pokemon> listaSorteio = new ArrayList<>();
            listaSorteio.add(pokemonA);                                                 // Adiciona ambos os Pokémons a um ArrayList e sorteia um dele para vencer.
            listaSorteio.add(pokemonB);
            Pokemon pokemonSorteado = sortearPokemon(listaSorteio);

            listaSorteio.remove(pokemonSorteado);
            System.out.println("Pokémon " + pokemonSorteado.getNome() + " vence a batalha!");
            equipeA.remove((listaSorteio.get(0)));
            equipeB.remove((listaSorteio.get(0)));
        }
}
}