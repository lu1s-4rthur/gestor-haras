package haras.dados;

import haras.basicas.Evento;
import java.io.*;
import java.util.List;

public class RepositorioEvento {
    private String arquivo = "dados/data/eventos.dat";

    public void salvarEventos(List<Evento> eventos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(eventos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Evento> carregarEventos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Evento>) ois.readObject();
        }
    }
}
