package haras.negocio;

import haras.basicas.Servico;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorServico {
    private List<Servico> servicos = new ArrayList<>();

    public void cadastrarServico(Servico servico) throws HarasException {
        if (servico == null) {
            throw new HarasException("Serviço inválido.");
        }
        servicos.add(servico);
    }

    public void editarServico(Servico servico) throws HarasException {
        if (servico == null) {
            throw new HarasException("Serviço inválido.");
        }
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getId() == servico.getId()) {
                servicos.set(i, servico);
                return;
            }
        }
        throw new HarasException("Serviço não encontrado.");
    }

    public boolean excluirServico(int id) {
        return servicos.removeIf(s -> s.getId() == id);
    }

    public List<Servico> listarServicos() {
        return new ArrayList<>(servicos);
    }
}
