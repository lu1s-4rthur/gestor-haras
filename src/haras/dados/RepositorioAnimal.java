package haras.dados;

import haras.basicas.Animal;
import java.io.*;
import java.util.List;

public class RepositorioAnimal {
private String arquivo = "dados/data/animais.dat";

    public void salvarAnimais(List<Animal> animais) throws IOException {
        new File("dados/data").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(animais);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Animal> carregarAnimais() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Animal>) ois.readObject();
        }
    }
}
