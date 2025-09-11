package haras.negocio;

import haras.dados.RepositorioAtendimentoVeterinario;
import java.io.IOException;

import haras.basicas.AtendimentoVeterinario;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

public class GerenciadorAtendimentoVeterinario {
    private RepositorioAtendimentoVeterinario repositorio = new RepositorioAtendimentoVeterinario();
    private List<AtendimentoVeterinario> atendimentos;

    public GerenciadorAtendimentoVeterinario() {
        try {
            atendimentos = repositorio.carregarAtendimentos();
        } catch (Exception e) {
            atendimentos = new ArrayList<>();
        }
    }

    private void salvar() {
        try {
            repositorio.salvarAtendimentos(atendimentos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar atendimentos: " + e.getMessage());
        }
    }

    public void registrarAtendimento(AtendimentoVeterinario atendimento) throws HarasException {
        if (atendimento == null) {
            throw new HarasException("Atendimento inválido.");
        }
        atendimentos.add(atendimento);
        salvar();
    }

    public List<AtendimentoVeterinario> listarAtendimentos() {
        return atendimentos;
    }

    public boolean excluirAtendimento(int id) {
        boolean removido = atendimentos.removeIf(a -> a.getId() == id);
        if (removido)
            salvar();
        return removido;
    }

    public void exportarCsv() throws IOException {
        repositorio.salvarAtendimentosCSV(atendimentos);
    }

    public void importarCsv(java.util.function.Function<Integer, haras.basicas.Animal> resolveAnimal,
                            java.util.function.Function<Integer, haras.basicas.Veterinario> resolveVet) throws IOException, CsvValidationException, NumberFormatException {
        atendimentos = repositorio.carregarAtendimentosCSV((id1, id2) -> resolveVet.apply(id1), resolveAnimal);
        salvar();
    }
}
