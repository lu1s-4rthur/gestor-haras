package haras.negocio;

import haras.exception.HarasException;
import haras.basicas.Animal;
import haras.basicas.Reproducao;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorReproducao {

    private List<Reproducao> historicoReproducoes = new ArrayList<>();

        public Reproducao realizarReproducao(int id, String tipo, String descricao, double valor, Animal pai, Animal mae) throws HarasException {
        if (!pai.isReprodutor()) {
            throw new HarasException("O pai " + pai.getNome() + " não é um reprodutor.");
        }
        if (!mae.isReprodutor()) {
            throw new HarasException("A mãe " + mae.getNome() + " não é uma reprodutora.");
        }

        Reproducao reproducao = new Reproducao(descricao, descricao, valor, pai, mae);
        historicoReproducoes.add(reproducao);



        System.out.println("Reprodução realizada com sucesso entre " + pai.getNome() + " e " + mae.getNome() + ".");
        return reproducao;
    }

    public List<Reproducao> getHistoricoReproducoes() {
        return historicoReproducoes;
    }

}
