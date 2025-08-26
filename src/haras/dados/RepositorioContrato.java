package haras.dados;

import haras.basicas.Contrato;
import java.io.*;
import java.util.List;

public class RepositorioContrato {
    private String arquivo = "dados/data/contratos.dat";

    public void salvarContratos(List<Contrato> contratos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(contratos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Contrato> carregarContratos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Contrato>) ois.readObject();
        }
    }
}
