package haras.negocio;

import haras.dados.RepositorioEvento;
import java.io.IOException;

import haras.basicas.Animal;
import haras.basicas.Evento;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorEvento {
    private RepositorioEvento repositorio = new RepositorioEvento();

    public String emitirRelatorioEventos() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Eventos:\n");
        for (Evento e : historicoEventos) {
            relatorio.append("ID: ").append(e.getId())
                    .append(" | Tipo: ").append(e.getTipo())
                    .append(" | Data: ").append(e.getData())
                    .append(" | Local: ").append(e.getLocal())
                    .append(" | Participantes: ").append(e.getParticipantes().size())
                    .append("\n");
        }
        return relatorio.toString();
    }

    private void salvar() {
        try {
            repositorio.salvarEventos(historicoEventos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public void adicionarParticipante(Evento evento, Animal animal) throws HarasException {
        if (evento == null || animal == null) {
            throw new HarasException("Evento ou animal inválido.");
        }
        if (!cadastroCompleto(animal)) {
            throw new HarasException("Cadastro do animal está incompleto.");
        }
        evento.getParticipantes().add(animal);
    }

    private boolean cadastroCompleto(Animal animal) {
        return animal.getNome() != null && !animal.getNome().isEmpty()
                && animal.getRaca() != null && !animal.getRaca().isEmpty()
                && animal.getGenero() != null && !animal.getGenero().isEmpty()
                && animal.getCor() != null && !animal.getCor().isEmpty()
                && animal.getEstadoSaude() != null && !animal.getEstadoSaude().isEmpty();
    }

    private List<Evento> historicoEventos;

    public GerenciadorEvento() {
        try {
            historicoEventos = repositorio.carregarEventos();
        } catch (Exception e) {
            historicoEventos = new ArrayList<>();
        }
    }

    public void cadastrarEvento(Evento evento) throws HarasException {
        if (evento == null) {
            throw new HarasException("Evento inválido.");
        }
        if (evento.getData().before(new Date())) {
            throw new HarasException("Não é permitido cadastrar evento com data no passado.");
        }
        historicoEventos.add(evento);
        salvar();
    }

    public List<Evento> listarEventos() {
        return historicoEventos;
    }

    // ===== CSV helpers =====
    public void exportarCsv() throws IOException {
        repositorio.salvarEventosCSV(historicoEventos);
    }

    public void importarCsv(java.util.function.Function<Integer, Animal> resolveAnimal) throws IOException {
        historicoEventos = repositorio.carregarEventosCSV(resolveAnimal);
        salvar();
    }
}
