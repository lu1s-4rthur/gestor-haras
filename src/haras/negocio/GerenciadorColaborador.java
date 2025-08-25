package haras.negocio;

import haras.basicas.Colaborador;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorColaborador {
    private List<Colaborador> colaboradores = new ArrayList<>();

    public void cadastrarColaborador(Colaborador colaborador) throws HarasException {
        if (colaborador == null) {
            throw new HarasException("Colaborador inválido.");
        }
        colaboradores.add(colaborador);
    }

    public void editarColaborador(Colaborador colaborador) throws HarasException {
        if (colaborador == null) {
            throw new HarasException("Colaborador inválido.");
        }
        for (int i = 0; i < colaboradores.size(); i++) {
            if (colaboradores.get(i).getId() == colaborador.getId()) {
                colaboradores.set(i, colaborador);
                return;
            }
        }
        throw new HarasException("Colaborador não encontrado.");
    }

    public boolean excluirColaborador(int id) {
        return colaboradores.removeIf(c -> c.getId() == id);
    }

    public List<Colaborador> listarColaboradores() {
        return new ArrayList<>(colaboradores);
    }
}
