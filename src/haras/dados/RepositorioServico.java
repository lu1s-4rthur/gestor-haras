package haras.dados;

import haras.basicas.Servico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RepositorioServico {
    private String arquivo = "dados/data/servicos.dat";
    private String arquivoCsv = "dados/data/servicos.csv";

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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarServicosCSV(List<Servico> servicos) throws IOException {
        new File("dados/data").mkdirs();
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","tipo","descricao","valor"});
            for (Servico s : servicos) {
                writer.writeNext(new String[]{
                    String.valueOf(s.getId()),
                    s.getTipo(),
                    s.getDescricao(),
                    String.valueOf(s.getValor())
                });
            }
        }
    }

    public List<Servico> carregarServicosCSV() throws IOException {
        List<Servico> servicos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return servicos;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 4) continue;
                int id = Integer.parseInt(linha[0]);
                String tipo = linha[1];
                String descricao = linha[2];
                double valor = Double.parseDouble(linha[3]);
                Servico s = new Servico(tipo, descricao, valor);
                s.setId(id);
                servicos.add(s);
            }
        }
        return servicos;
    }
}
