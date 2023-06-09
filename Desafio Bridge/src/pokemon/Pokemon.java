package pokemon;

/* Esta é a classe que contém os dados do Pokémon.
*  Os atributos da classe são as características do Pokémon.
*  A classe possui um método (dadosPokemon) que exibe as
*  características do Pokémon, e também possui getters e setters. */

public class Pokemon {

    private String nome; // Nome do Pokémon.
    private String tipo; // Tipo do Pokémon.
    private int raridade; // Raridade do Pokémon.
    private String[] vantagem; // Vetor com as vantagens do Pokémon.
    private String[] desvantagem; // Vetor com as desvantagens do Pokémon.

    // Setters. Utilizados para atribuir valores aos atributos do Pokémon.
    public void setNome(String nomeP) {
        nome = nomeP;
    }
    public void setTipo(String tipoP) {
        tipo = tipoP;
    }
    public void setRaridade(int raridadeP) {
        raridade = raridadeP;
    }
    public void setVantagem(String[] vantagemP) {
        vantagem = vantagemP;
    }
    public void setDesvantagem(String[] desvantagemP) {
        desvantagem = desvantagemP;
    }

    // Getters. Utilizados para retornar os valores dos atributos do Pokémon.
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public int getRaridade() {
        return raridade;
    }
    public String[] getVantagem() {
        return vantagem;
    }
    public String[] getDesvantagem() {
        return desvantagem;
    }

    // Função que printa os valores do Pokémon. Utilizada para consultar um Pokémon específico.
    public void dadosPokemon() {
        System.out.printf("======= Dados do Pokémon %s =======\n", nome);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Raridade: " + raridade);
        System.out.print("Vantagem: ");

        if (vantagem.length == 0) {
            System.out.print("Nenhuma ");
        } else {
            for (String vantagemI : vantagem) {
                System.out.printf("%s ", vantagemI);
            }
        }
        System.out.print("\n");

        System.out.print("Desvantagem: ");
        if (desvantagem.length == 0) {
            System.out.print("Nenhuma ");
        } else {
            for (String desvantagemI : desvantagem) {
                System.out.printf("%s ", desvantagemI);
            }
        }
        System.out.print("\n");
        System.out.println("======================================\n");
    }
}