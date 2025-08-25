package haras.negocio;

import haras.basicas.Animal;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAnimal {
    private List<Animal> animais = new ArrayList<>();

    public void cadastrarAnimal(Animal animal) throws HarasException {
        if (animal == null) {
            throw new HarasException("Animal inválido.");
        }
        animais.add(animal);
    }

    public void editarAnimal(Animal animal) throws HarasException {
        if (animal == null) {
            throw new HarasException("Animal inválido.");
        }
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                animais.set(i, animal);
                return;
            }
        }
        throw new HarasException("Animal não encontrado.");
    }

    public boolean excluirAnimal(int id) {
        return animais.removeIf(a -> a.getId() == id);
    }

    public List<Animal> listarAnimais() {
        return animais;
    }
}
