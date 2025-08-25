package haras.basicas;

public class Prescricao {
    private static int contador = 1;
    private int id;
    private String descricao;
    private Veterinario veterinario;
    private Animal animal;

    public Prescricao(String descricao, Veterinario veterinario, Animal animal) {
        this.id = contador++;
        this.descricao = descricao;
        this.veterinario = veterinario;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Prescricao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", veterinario=" + veterinario +
                ", animal=" + animal +
                '}';
    }
}
