package haras.negocio;

import haras.dados.RepositorioAtendimentoVeterinario;
import java.io.IOException;

import haras.basicas.AtendimentoVeterinario;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.List;

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

}
