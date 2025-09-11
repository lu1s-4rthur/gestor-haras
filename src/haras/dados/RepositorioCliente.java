package haras.dados;

import haras.basicas.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RepositorioCliente {
    private String arquivo = "dados/data/clientes.dat";
    private String arquivoCsv = "dados/data/clientes.csv";

    public void salvarClientes(List<Cliente> clientes) throws IOException {
        new File("dados/data").mkdirs();
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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarClientesCSV(List<Cliente> clientes) throws IOException {
        new File("dados/data").mkdirs();
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","nome","endereco","telefone"});
            for (Cliente c : clientes) {
                writer.writeNext(new String[]{
                    String.valueOf(c.getId()),
                    c.getNome(),
                    c.getEndereco(),
                    c.getTelefone()
                });
            }
        }
    }

    public List<Cliente> carregarClientesCSV() throws IOException, CsvValidationException, NumberFormatException {
        List<Cliente> clientes = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) {
            return clientes;
        }
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean headerLido = false;
            while ((linha = reader.readNext()) != null) {
                if (!headerLido) {
                    headerLido = true;
                    continue;
                }
                if (linha.length < 4) continue;
                int id = Integer.parseInt(linha[0]);
                String nome = linha[1];
                String endereco = linha[2];
                String telefone = linha[3];

                Cliente c = new Cliente(nome, endereco, telefone);
                c.setId(id);
                clientes.add(c);
            }
        }
        return clientes;
    }
}
