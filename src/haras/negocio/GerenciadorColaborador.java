package haras.negocio;
import haras.dados.RepositorioColaborador;
import java.io.IOException;

import haras.basicas.Colaborador;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

public class GerenciadorColaborador {
    private RepositorioColaborador repositorio = new RepositorioColaborador();
    private List<Colaborador> colaboradores;

    public GerenciadorColaborador() {
        try {
            colaboradores = repositorio.carregarColaboradores();
        } catch (Exception e) {
            colaboradores = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarColaboradores(colaboradores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar colaboradores: " + e.getMessage());
        }
    }

    public void cadastrarColaborador(Colaborador colaborador) throws HarasException {
        if (colaborador == null) {
            throw new HarasException("Colaborador inválido.");
        }
        colaboradores.add(colaborador);
        salvar();
    }

    public void editarColaborador(Colaborador colaborador) throws HarasException {
        if (colaborador == null) {
            throw new HarasException("Colaborador inválido.");
        }
        for (int i = 0; i < colaboradores.size(); i++) {
            if (colaboradores.get(i).getId() == colaborador.getId()) {
                colaboradores.set(i, colaborador);
                salvar();
                return;
            }
        }
        throw new HarasException("Colaborador não encontrado.");
    }

    public boolean excluirColaborador(int id) {
        boolean removido = colaboradores.removeIf(c -> c.getId() == id);
        if (removido) salvar();
        return removido;
    }

    public List<Colaborador> listarColaboradores() {
        return new ArrayList<>(colaboradores);
    }

    // ===== CSV helpers =====
    public void exportarCsv() throws IOException {
        repositorio.salvarColaboradoresCSV(colaboradores);
    }

    public void importarCsv() throws IOException, CsvValidationException, NumberFormatException {
        colaboradores = repositorio.carregarColaboradoresCSV();
        salvar();
    }
}
