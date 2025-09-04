package haras.negocio;
import haras.dados.RepositorioServico;
import java.io.IOException;

import haras.basicas.Servico;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorServico {
    private RepositorioServico repositorio = new RepositorioServico();

    public String emitirRelatorioServicos() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Serviços:\n");
        for (Servico s : servicos) {
            relatorio.append("ID: ").append(s.getId())
                .append(" | Tipo: ").append(s.getTipo())
                .append(" | Descrição: ").append(s.getDescricao())
                .append(" | Valor: R$").append(s.getValor())
                .append("\n");
        }
        return relatorio.toString();
    }

    private List<Servico> servicos;

    public GerenciadorServico() {
        try {
            servicos = repositorio.carregarServicos();
        } catch (Exception e) {
            servicos = new ArrayList<>();
        }
    }

    public void cadastrarServico(Servico servico) throws HarasException {
        if (servico == null) {
            throw new HarasException("Serviço inválido.");
        }
        servicos.add(servico);
        salvar();
    }

    public void editarServico(Servico servico) throws HarasException {
        if (servico == null) {
            throw new HarasException("Serviço inválido.");
        }
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getId() == servico.getId()) {
                servicos.set(i, servico);
                salvar();
                return;
            }
        }
        throw new HarasException("Serviço não encontrado.");
    }

    public boolean excluirServico(int id) {
        boolean removido = servicos.removeIf(s -> s.getId() == id);
        if (removido) salvar();
        return removido;
    }
    private void salvar() {
        try {
            repositorio.salvarServicos(servicos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar serviços: " + e.getMessage());
        }
    }

    public List<Servico> listarServicos() {
        return new ArrayList<>(servicos);
    }

    // ===== CSV helpers =====
    public void exportarCsv() throws IOException {
        repositorio.salvarServicosCSV(servicos);
    }

    public void importarCsv() throws IOException {
        servicos = repositorio.carregarServicosCSV();
        salvar();
    }
}
