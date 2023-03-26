package pokemon;

// Esta é a classe que contém os dados do Pokémon.
// Os atributos da classe são as características do Pokémon.
// A classe possui um método (dadosPokemon) que exibe as
// características do Pokémon, e também possui getters e setters.

public class Pokemon {

    private String nome;
    private String tipo;
    private int raridade;
    private String vantagem;
    private String desvantagem;

    public void setNome(String nomeP) {
        nome = nomeP;
    }

    public void setTipo(String tipoP) {
        tipo = tipoP;
    }

    public void setRaridade(int raridadeP) {
        raridade = raridadeP;
    }

    public void setVantagem(String vantagemP) {
        vantagem = vantagemP;
    }

    public void setDesvantagem(String desvantagemP) {
        desvantagem = desvantagemP;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getRaridade() {
        return raridade;
    }

    public String getVantagem() {
        return vantagem;
    }

    public String getDesvantagem() {
        return desvantagem;
    }

    public void dadosPokemon() {
        System.out.printf("======= Dados do Pokémon %s =======\n", nome);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Raridade: " + raridade);
        System.out.println("Vantagem: " + vantagem);
        System.out.println("Desvantagem: " + desvantagem);
        System.out.println("======================================\n");
    }
}
