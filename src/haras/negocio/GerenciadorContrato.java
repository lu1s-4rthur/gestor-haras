package haras.negocio;
import haras.dados.RepositorioContrato;
import java.io.IOException;

import haras.basicas.Contrato;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

public class GerenciadorContrato {
    private RepositorioContrato repositorio = new RepositorioContrato();
    private List<Contrato> contratos;

    public GerenciadorContrato() {
        try {
            contratos = repositorio.carregarContratos();
        } catch (Exception e) {
            contratos = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarContratos(contratos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar contratos: " + e.getMessage());
        }
    }

    public void cadastrarContrato(Contrato contrato) throws HarasException {
        if (contrato == null) {
            throw new HarasException("Contrato inválido.");
        }
        contratos.add(contrato);
        salvar();
    }

    public void editarContrato(Contrato contrato) throws HarasException {
        if (contrato == null) {
            throw new HarasException("Contrato inválido.");
        }
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getId() == contrato.getId()) {
                contratos.set(i, contrato);
                salvar();
                return;
            }
        }
        throw new HarasException("Contrato não encontrado.");
    }

    public boolean excluirContrato(int id) {
        boolean removido = contratos.removeIf(c -> c.getId() == id);
        if (removido) salvar();
        return removido;
    }

    public List<Contrato> listarContratos() {
        return contratos;
    }

    // ===== CSV helpers =====
    public void exportarCsv() throws IOException {
        repositorio.salvarContratosCSV(contratos);
    }

    public void importarCsv(java.util.function.Function<Integer, haras.basicas.Cliente> resolveCliente,
                            java.util.function.Function<Integer, haras.basicas.Animal> resolveAnimal,
                            java.util.function.Function<Integer, haras.basicas.Servico> resolveServico) throws IOException, CsvValidationException, NumberFormatException {
        contratos = repositorio.carregarContratosCSV(resolveCliente, resolveAnimal, resolveServico);
        salvar();
    }
}
