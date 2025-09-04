package haras.negocio;

import haras.dados.RepositorioTreinamento;
import java.io.IOException;

import haras.basicas.Treinamento;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTreinamento {
    private RepositorioTreinamento repositorio = new RepositorioTreinamento();
    private List<Treinamento> treinamentos;

    public GerenciadorTreinamento() {
        try {
            treinamentos = repositorio.carregarTreinamentos();
        } catch (Exception e) {
            treinamentos = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarTreinamentos(treinamentos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar treinamentos: " + e.getMessage());
        }
    }

    public void registrarTreinamento(Treinamento treinamento) throws HarasException {
        if (treinamento == null) {
            throw new HarasException("Treinamento inválido.");
        }
        treinamentos.add(treinamento);
        salvar();
    }

    public List<Treinamento> listarTreinamentos() {
        return treinamentos;
    }

    public boolean excluirTreinamento(int id) {
        boolean removido = treinamentos.removeIf(t -> t.getId() == id);
        if (removido)
            salvar();
        return removido;
    }

    // ===== CSV helpers =====
    public void exportarCsv() throws IOException {
        repositorio.salvarTreinamentosCSV(treinamentos);
    }

    public void importarCsv(java.util.function.Function<Integer, haras.basicas.Animal> resolveAnimal,
                            java.util.function.Function<Integer, haras.basicas.Treinador> resolveTreinador) throws IOException {
        treinamentos = repositorio.carregarTreinamentosCSV(resolveAnimal, resolveTreinador);
        salvar();
    }
}
