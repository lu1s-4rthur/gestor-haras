package haras.dados;

import haras.basicas.Servico;
import java.io.*;
import java.util.List;

public class RepositorioServico {
    private String arquivo = "dados/data/servicos.dat";

    public void salvarServicos(List<Servico> servicos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(servicos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Servico> carregarServicos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Servico>) ois.readObject();
        }
    }
}
