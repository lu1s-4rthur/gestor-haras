package haras.basicas;

import java.io.Serializable;

public class Baia implements Serializable {
    private int numero;
    private boolean manutencao;
    private Animal animalOcupante;

    public Baia(int numero) {
        this.numero = numero;
        this.manutencao = false;
        this.animalOcupante = null;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public boolean isManutencao() {
        return manutencao;
    }
    public void setManutencao(boolean manutencao) {
        this.manutencao = manutencao;
    }

    public Animal getAnimalOcupante() {
        return animalOcupante;
    }

    public void setAnimalOcupante(Animal animalOcupante) {
        this.animalOcupante = animalOcupante;
    }

    public void liberarBaia() {
        this.animalOcupante = null;
    }

    @Override
    public String toString() {
        return "{" +
                "numero=" + numero +
                ", manutencao=" + manutencao +
                ", animalOcupante=" + (animalOcupante != null ? animalOcupante.getNome() : "Vazia") +
                '}';
    }

}
