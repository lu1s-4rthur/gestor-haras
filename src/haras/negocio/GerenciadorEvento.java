package haras.negocio;

import haras.basicas.Animal;
import haras.basicas.Evento;
import haras.exception.HarasException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorEvento {
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
    private List<Evento> historicoEventos = new ArrayList<>();

    public void cadastrarEvento(Evento evento) throws HarasException {
        if (evento == null) {
            throw new HarasException("Evento inválido.");
        }
        if (evento.getData().before(new Date())) {
            throw new HarasException("Não é permitido cadastrar evento com data no passado.");
        }
        historicoEventos.add(evento);
    }

    public List<Evento> listarEventos() {
        return historicoEventos;
    }
}
