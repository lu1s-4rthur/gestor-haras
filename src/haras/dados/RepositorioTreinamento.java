package haras.dados;

import haras.basicas.Treinamento;
import haras.basicas.Animal;
import haras.basicas.Treinador;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RepositorioTreinamento {
    private String arquivo = "dados/data/treinamentos.dat";
    private String arquivoCsv = "dados/data/treinamentos.csv";

    public void salvarTreinamentos(List<Treinamento> treinamentos) throws IOException {
        new File("dados/data").mkdirs();
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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarTreinamentosCSV(List<Treinamento> treinamentos) throws IOException {
        new File("dados/data").mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","animalId","treinadorId","data","tipo","observacoes"});
            for (Treinamento t : treinamentos) {
                writer.writeNext(new String[]{
                    String.valueOf(t.getId()),
                    String.valueOf(t.getAnimal().getId()),
                    String.valueOf(t.getTreinador().getId()),
                    sdf.format(t.getData()),
                    t.getTipo(),
                    t.getObservacoes()
                });
            }
        }
    }

    public List<Treinamento> carregarTreinamentosCSV(Function<Integer, Animal> resolveAnimal,
                                                     Function<Integer, Treinador> resolveTreinador) throws IOException, CsvValidationException, NumberFormatException {
        List<Treinamento> treinamentos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return treinamentos;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 6) continue;
                int id = Integer.parseInt(linha[0]);
                int animalId = Integer.parseInt(linha[1]);
                int treinadorId = Integer.parseInt(linha[2]);
                java.util.Date data;
                try { data = sdf.parse(linha[3]); } catch (ParseException ex) { continue; }
                String tipo = linha[4];
                String obs = linha[5];
                Animal animal = resolveAnimal.apply(animalId);
                Treinador treinador = resolveTreinador.apply(treinadorId);
                if (animal == null || treinador == null) continue;
                Treinamento t = new Treinamento(animal, treinador, data, tipo, obs);
                t.setId(id);
                treinamentos.add(t);
            }
        }
        return treinamentos;
    }
}
