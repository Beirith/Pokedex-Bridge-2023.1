package calculos;

import pokemon.Pokemon;

import java.util.ArrayList;

public class CalculoPokemonMediano {

    private ArrayList<Pokemon> listaPokemonsMedianos = new ArrayList<>();
    private ArrayList<Pokemon> listaPokemonsMedianosPositiva = new ArrayList<>();
    private ArrayList<Pokemon> listaPokemonsMedianosNegativa = new ArrayList<>();

    public void calcularPokemonMaisMediano(ArrayList<Pokemon> listaDePokemons) {

        if (listaDePokemons.size() == 0) {                                          // Caso a lista de Pokémons esteja vazia, printa um aviso e retorna para o menu anterior.
            System.out.println("Não há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            Pokemon pokemonMediano = listaDePokemons.get(0);
            boolean repeticoes = false;                                         // Variável que verifica se o valor mais mediano se repete na lista de Pokémons.
            int raridadeMediana = pokemonMediano.getRaridade();
            int raridadeLista;

            if (raridadeMediana < 0) {
                raridadeMediana = Math.abs(pokemonMediano.getRaridade());
            } else {
                raridadeMediana = pokemonMediano.getRaridade();
            }

            for (int i = 1; i < listaDePokemons.size(); i++) {                  // Percorre a lista de Pokémons conhecidos e verifica qual Pokémon possue a menor
                Pokemon pokemonLista = listaDePokemons.get(i);                  // raridade em módulo (valor absoluto).
                raridadeLista = pokemonLista.getRaridade();

                if (raridadeLista < 0) {
                    raridadeLista = Math.abs(raridadeLista);
                }

                if (raridadeLista < raridadeMediana) {
                    raridadeMediana = raridadeLista;
                    pokemonMediano = pokemonLista;
                    repeticoes = false;
                } else if (raridadeLista == raridadeMediana) {                 // Caso existam valores iguais, a varíavel repetições assume o valor true.
                    repeticoes = true;
                }
            }
            if (repeticoes)  {                                                 // Caso a variável repetições se mantenha como true até o final do percorrimento da lista,
                calcularEmpate(pokemonMediano, listaDePokemons);               // então quer dizer que o valor mais mediano se repete sendo preciso calcular desempate.
            } else {
                pokemonMediano.dadosPokemon();                                // Caso contrário, só existe um valor mais mediano e não é preciso calcular desempate.
                listaPokemonsMedianos.add(pokemonMediano);
            }
        }
    }

    private void calcularEmpate(Pokemon pokemonMediano, ArrayList<Pokemon> listaDePokemons) {
        for (Pokemon pokemonLista : listaDePokemons) {                                                 // Verifica os Pokémons que possuem o mesmo valor de raridade em módulo.
            if (Math.abs(pokemonMediano.getRaridade()) == Math.abs(pokemonLista.getRaridade())) {
                listaPokemonsMedianos.add(pokemonLista);                                               // Adiciona estes Pokémons na lista listaPokemonsMedianos.
            }
        }

        for (Pokemon pokemonLista : listaPokemonsMedianos) {                                          // Percorre a lista que possue os Pokémons com o mesmo valor de raridade em módulo,
            if (pokemonLista.getRaridade() <= 0) {                                                    // e verifica se a raridade é negativa ou positiva.
                listaPokemonsMedianosNegativa.add(pokemonLista);                                      // Caso seja negativa, o Pokémon é adiconado a lista listaPokemonsMedianosNegativa.
            } else if (pokemonLista.getRaridade() > 0) {
                listaPokemonsMedianosPositiva.add(pokemonLista);                                      // Caso seja positiva, o Pokémon é adiconado a lista listaPokemonsMedianosPositiva.
            }
        }
        retornaPokemonMediano();
    }

    private void retornaPokemonMediano() {
        if (listaPokemonsMedianosPositiva.size() != 0) {
            for (Pokemon p : listaPokemonsMedianosPositiva) {
                System.out.println("O Pokémon mais mediano é  " + p.getNome() + "!");
                System.out.println("Sua raridade é  " + p.getRaridade());
            }
            } else {
            for (Pokemon p : listaPokemonsMedianosNegativa) {
                System.out.println("O Pokémon mais mediano é  " + p.getNome() + "!");
                System.out.println("Sua raridade é  " + p.getRaridade());
            }
        }
        listaPokemonsMedianos.clear();
        listaPokemonsMedianosNegativa.clear();
        listaPokemonsMedianosPositiva.clear();
    }
}