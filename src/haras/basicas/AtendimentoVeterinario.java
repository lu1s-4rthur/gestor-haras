package haras.basicas;

import java.util.Date;

import java.io.Serializable;

public class AtendimentoVeterinario implements Serializable {
    private static int contador = 1;
    private int id;
    private Animal animal;
    private Veterinario veterinario;
    private Date data;
    private String descricao;

    public AtendimentoVeterinario(Animal animal, Veterinario veterinario, Date data, String descricao) {
        this.id = contador++;
        this.animal = animal;
        this.veterinario = veterinario;
        this.data = data;
        this.descricao = descricao;
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

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", animal=" + animal +
                ", veterinario=" + veterinario +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
