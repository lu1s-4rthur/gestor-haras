package haras.dados;

import haras.basicas.Servico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RepositorioServico {
    private String arquivo = "dados/data/servicos.dat";
    private String arquivoCsv = "dados/data/servicos.csv";

    public void salvarServicos(List<Servico> servicos) throws IOException {
        new File("dados/data").mkdirs();
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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarServicosCSV(List<Servico> servicos) throws IOException {
        new File("dados/data").mkdirs();
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","tipo","valor"});
            for (Servico s : servicos) {
                writer.writeNext(new String[]{
                    String.valueOf(s.getId()),
                    s.getTipo(),
                    String.valueOf(s.getValor())
                });
            }
        }
    }

    public List<Servico> carregarServicosCSV() throws IOException, CsvValidationException, NumberFormatException {
        List<Servico> servicos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return servicos;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 3) continue;
                int id = Integer.parseInt(linha[0]);
                String tipo = linha[1];
                double valor = Double.parseDouble(linha[2]);
                Servico s = new Servico(tipo, valor);
                s.setId(id);
                servicos.add(s);
            }
        }
        return servicos;
    }
}
