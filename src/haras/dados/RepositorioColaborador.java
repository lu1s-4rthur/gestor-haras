package haras.dados;

import haras.basicas.Colaborador;
import java.io.*;
import java.util.List;

public class RepositorioColaborador {
    private String arquivo = "dados/data/colaboradores.dat";

    public void salvarColaboradores(List<Colaborador> colaboradores) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(colaboradores);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Colaborador> carregarColaboradores() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Colaborador>) ois.readObject();
        }
    }
}
