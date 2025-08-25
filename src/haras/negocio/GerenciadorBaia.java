package haras.negocio;

import haras.basicas.Baia;
import haras.basicas.Animal;
import haras.exception.HarasException;

public class GerenciadorBaia {
    
    public void alocarAnimalEmBaia(Baia baia, Animal animal) throws HarasException {
        if (baia.isManutencao()) {
            throw new HarasException("A baia está em manutenção.");
        }
        if (baia.getAnimalOcupante() != null) {
            throw new HarasException("A baia já está ocupada por outro animal.");
        }
        baia.setAnimalOcupante(animal);
    }

    public void liberarBaia(Baia baia) throws HarasException {
        if (baia.getAnimalOcupante() == null) {
            throw new HarasException("A baia já está vazia.");
        }
        baia.liberarBaia();
    }

    public void colocarBaiaEmManutencao(Baia baia) throws HarasException {
        if (baia.getAnimalOcupante() != null) {
            throw new HarasException("Não é possível colocar a baia em manutenção enquanto estiver ocupada.");
        }
        baia.setManutencao(true);
    }

    public void retirarBaiaDeManutencao(Baia baia) throws HarasException {
        if (!baia.isManutencao()) {
            throw new HarasException("A baia não está em manutenção.");
        }
        baia.setManutencao(false);
    }
}
