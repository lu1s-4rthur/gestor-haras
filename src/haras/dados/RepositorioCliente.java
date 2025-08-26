package haras.dados;

import haras.basicas.Cliente;
import java.io.*;
import java.util.List;

public class RepositorioCliente {
    private String arquivo = "dados/data/clientes.dat";

    public void salvarClientes(List<Cliente> clientes) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(clientes);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> carregarClientes() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cliente>) ois.readObject();
        }
    }
}
