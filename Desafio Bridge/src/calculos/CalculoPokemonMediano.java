package calculos;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculoPokemonMediano {

    private ArrayList<Pokemon> listaPokemonsMedianos = new ArrayList<>();
    private ArrayList<Pokemon> listaPokemonsMedianosPositiva = new ArrayList<>();
    private ArrayList<Pokemon> listaPokemonsMedianosNegativa = new ArrayList<>();
    private boolean raridadeNegativa = false;

    public void calcularPokemonMaisMediano(Scanner scan, ArrayList<Pokemon> listaDePokemons) {
        ArrayList<Pokemon> listaDePokemonsCalculo = listaDePokemons;

        if (listaDePokemons.size() == 0) {
            System.out.println("Não há nenhum Pokémon para ser visualizado!");
            System.out.println("A lista de Pokémons conhecidos está vazia!");
            System.out.println("Cadastre algum Pokémon no menu de cadastros para preenchê-la!\n");
        } else {
            Pokemon pokemonMediano = listaDePokemons.get(0);
            int repeticoes = 0;
            int raridadeMediana = pokemonMediano.getRaridade();
            int raridadeLista;

            if (raridadeMediana < 0) {
                raridadeMediana = Math.abs(pokemonMediano.getRaridade());
            } else {
                raridadeMediana = pokemonMediano.getRaridade();
            }

            for (int i = 1; i < listaDePokemons.size(); i++) {
                Pokemon pokemonLista = listaDePokemons.get(i);
                raridadeLista = pokemonLista.getRaridade();

                if (raridadeLista < 0) {
                    raridadeLista = Math.abs(raridadeLista);
                }

                if (raridadeLista < raridadeMediana) {
                    raridadeMediana = raridadeLista;
                    pokemonMediano = pokemonLista;
                    repeticoes = 0;
                } else if (raridadeLista == raridadeMediana) {
                    repeticoes++;
                }
            }
            if (repeticoes != 0)  {
                calcularEmpate(pokemonMediano, listaDePokemonsCalculo);
            } else {
                pokemonMediano.dadosPokemon();
                listaPokemonsMedianos.add(pokemonMediano);
            }
        }
    }

    public void calcularEmpate(Pokemon pokemonMediano, ArrayList<Pokemon> listaDePokemons) {
        for (int i = 0; i < listaDePokemons.size(); i++) {
            Pokemon pokemonLista = listaDePokemons.get(i);
            if (Math.abs(pokemonMediano.getRaridade()) == Math.abs(pokemonLista.getRaridade())) {
                listaPokemonsMedianos.add(pokemonLista);
            }
        }

        for (int j = 0; j < listaPokemonsMedianos.size(); j++) {
            Pokemon pokemonLista = listaPokemonsMedianos.get(j);

            if (pokemonLista.getRaridade() <= 0) {
                listaPokemonsMedianosNegativa.add(pokemonLista);
            }
            else if (pokemonLista.getRaridade() > 0) {
                listaPokemonsMedianosPositiva.add(pokemonLista);
            }
        }

        retornaPokemonMediano();
    }

    public void retornaPokemonMediano() {
        if (listaPokemonsMedianosPositiva.size() != 0) {
            for (Pokemon pokemonLista : listaPokemonsMedianosPositiva) {
                pokemonLista.dadosPokemon();
            }
            } else {
            for (Pokemon pokemonLista : listaPokemonsMedianosNegativa) {
                pokemonLista.dadosPokemon();
            }
            }
        listaPokemonsMedianos.clear();
        listaPokemonsMedianosNegativa.clear();
        listaPokemonsMedianosPositiva.clear();
    }
}
