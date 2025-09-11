package haras.dados;

import haras.basicas.Baia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioBaia {
    private String arquivo = "dados/data/baias.dat";

    public void salvarBaias(List<Baia> baias) throws IOException {
        new File("dados/data").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(baias);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Baia> carregarBaias() throws IOException, ClassNotFoundException {
        File f = new File(arquivo);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Baia>) ois.readObject();
        }
    }
}
