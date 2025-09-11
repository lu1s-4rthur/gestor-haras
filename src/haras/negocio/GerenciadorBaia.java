package haras.negocio;

import haras.basicas.Baia;
import haras.dados.RepositorioBaia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import haras.basicas.Animal;
import haras.exception.HarasException;

public class GerenciadorBaia {
    private RepositorioBaia repositorio = new RepositorioBaia();
    private List<Baia> baias;

     public GerenciadorBaia() {
        try {
            this.baias = repositorio.carregarBaias();
        } catch (Exception e) {
            this.baias = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarBaias(this.baias);
        } catch (IOException e) {
            System.err.println("Falha ao salvar o estado das baias: " + e.getMessage());
        }
    }

    public void alocarAnimalEmBaia(Baia baia, Animal animal) throws HarasException {
        if (baia.isManutencao()) {
            throw new HarasException("A baia está em manutenção.");
        }
        if (baia.getAnimalOcupante() != null) {
            throw new HarasException("A baia já está ocupada por outro animal.");
        }
        baia.setAnimalOcupante(animal);

        salvar();
    }

    public void liberarBaia(Baia baia) throws HarasException {
        if (baia.getAnimalOcupante() == null) {
            throw new HarasException("A baia já está vazia.");
        }
        baia.liberarBaia();
        salvar();
    }

    public Baia localizarOuCriarBaia(int numero) {
        Optional<Baia> baiaExistente = baias.stream().filter(b -> b.getNumero() == numero).findFirst();
        if (baiaExistente.isPresent()) {
            return baiaExistente.get();
        } else {
            Baia novaBaia = new Baia(numero);
            baias.add(novaBaia);
            salvar();
            return novaBaia;
        }
    }

    public List<Baia> listarBaias() {
        return this.baias;
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
