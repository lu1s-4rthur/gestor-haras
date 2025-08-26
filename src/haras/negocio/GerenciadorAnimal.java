package haras.negocio;
import haras.dados.RepositorioAnimal;
import java.io.IOException;

import haras.basicas.Animal;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAnimal {
    private RepositorioAnimal repositorio = new RepositorioAnimal();
    private List<Animal> animais;

    public GerenciadorAnimal() {
        try {
            animais = repositorio.carregarAnimais();
        } catch (Exception e) {
            animais = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarAnimais(animais);
        } catch (IOException e) {
            System.err.println("Erro ao salvar animais: " + e.getMessage());
        }
    }

    public void cadastrarAnimal(Animal animal) throws HarasException {
        if (animal == null) {
            throw new HarasException("Animal inválido.");
        }
        animais.add(animal);
        salvar();
    }

    public void editarAnimal(Animal animal) throws HarasException {
        if (animal == null) {
            throw new HarasException("Animal inválido.");
        }
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                animais.set(i, animal);
                salvar();
                return;
            }
        }
        throw new HarasException("Animal não encontrado.");
    }

    public boolean excluirAnimal(int id) {
        boolean removido = animais.removeIf(a -> a.getId() == id);
        if (removido) salvar();
        return removido;
    }

    public List<Animal> listarAnimais() {
        return animais;
    }
}
