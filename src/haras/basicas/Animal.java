package haras.basicas;

import java.io.Serializable;

public class Animal implements Serializable {
    private static int contador = 1;
    private int id;
    private String nome;
    private String raca;
    private String genero;
    private String cor;
    private int idade;
    private String estadoSaude;
    private boolean reprodutor;

    public Animal(String nome, String raca, String genero, String cor, int idade, String estadoSaude) {
        this.id = contador++;
        this.nome = nome;
        this.raca = raca;
        this.genero = genero;
        this.cor = cor;
        this.idade = idade;
        this.estadoSaude = estadoSaude;
        this.reprodutor = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEstadoSaude() {
        return estadoSaude;
    }

    public void setEstadoSaude(String estadoSaude) {
        this.estadoSaude = estadoSaude;
    }

    public boolean isReprodutor() {
        return reprodutor;
    }

    public void setReprodutor(boolean reprodutor) {
        this.reprodutor = reprodutor;
    }

    public static void ajustarContadorAPartirDoMaximo(int maxId) {
        if (maxId + 1 > contador) {
            contador = maxId + 1;
        }
    }

    @Override
    public String toString() {
        return "Animal{id=" + id + ", nome='" + nome + "', raca='" + raca + "', genero='" + genero + "', cor='" + cor + "', idade=" + idade + "}";
    }

}