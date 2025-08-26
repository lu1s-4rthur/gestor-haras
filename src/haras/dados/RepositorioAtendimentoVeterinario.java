package haras.dados;

import haras.basicas.AtendimentoVeterinario;
import java.io.*;
import java.util.List;

public class RepositorioAtendimentoVeterinario {
    private String arquivo = "dados/data/atendimentos.dat";

    public void salvarAtendimentos(List<AtendimentoVeterinario> atendimentos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(atendimentos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AtendimentoVeterinario> carregarAtendimentos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<AtendimentoVeterinario>) ois.readObject();
        }
    }
}
