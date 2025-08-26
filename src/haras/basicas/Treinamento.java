package haras.basicas;

import java.util.Date;

import java.io.Serializable;

public class Treinamento implements Serializable {
    private static int contador = 1;
    private int id;
    private Animal animal;
    private Treinador treinador;
    private Date data;
    private String tipo;
    private String observacoes;

    public Treinamento(Animal animal, Treinador treinador, Date data, String tipo, String observacoes) {
        this.id = contador++;
        this.animal = animal;
        this.treinador = treinador;
        this.data = data;
        this.tipo = tipo;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override

    public String toString() {
        return "Treinamento{" +
                "id=" + id +
                ", animal=" + animal +
                ", treinador=" + treinador +
                ", data=" + data +
                ", tipo='" + tipo + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
