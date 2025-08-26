package haras.dados;

import haras.basicas.Treinamento;
import java.io.*;
import java.util.List;

public class RepositorioTreinamento {
    private String arquivo = "dados/data/treinamentos.dat";

    public void salvarTreinamentos(List<Treinamento> treinamentos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(treinamentos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Treinamento> carregarTreinamentos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Treinamento>) ois.readObject();
        }
    }
}
