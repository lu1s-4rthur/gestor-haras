package haras.negocio;

import haras.basicas.Prescricao;
import haras.basicas.Veterinario;
import haras.basicas.Animal;
import haras.exception.HarasException;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPrescricao {
    private List<Prescricao> historicoPrescricoes = new ArrayList<>();

    public void cadastrarPrescricao(Prescricao prescricao) throws HarasException {
        if (prescricao == null) {
            throw new HarasException("Prescrição inválida.");
        }
        historicoPrescricoes.add(prescricao);
    }

    public Prescricao emitirPrescricao(int id, String descricao, Veterinario veterinario, Animal animal) throws HarasException {
        if (veterinario == null) {
            throw new HarasException("Somente veterinários podem emitir prescrições.");
        }
        Prescricao prescricao = veterinario.emitirPrescricao(id, descricao,animal);
        cadastrarPrescricao(prescricao);
        return prescricao;
    }

    public List<Prescricao> listarPrescricoes() {
        return historicoPrescricoes;
    }

}
