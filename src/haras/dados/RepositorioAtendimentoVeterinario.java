package haras.dados;

import haras.basicas.AtendimentoVeterinario;
import haras.basicas.Animal;
import haras.basicas.Veterinario;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.function.BiFunction;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RepositorioAtendimentoVeterinario {
    private String arquivo = "dados/data/atendimentos.dat";
    private String arquivoCsv = "dados/data/atendimentos.csv";

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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarAtendimentosCSV(List<AtendimentoVeterinario> atendimentos) throws IOException {
        new File("dados/data").mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","animalId","veterinarioId","data","descricao"});
            for (AtendimentoVeterinario a : atendimentos) {
                writer.writeNext(new String[]{
                    String.valueOf(a.getId()),
                    String.valueOf(a.getAnimal().getId()),
                    String.valueOf(a.getVeterinario().getId()),
                    sdf.format(a.getData()),
                    a.getDescricao()
                });
            }
        }
    }

    public List<AtendimentoVeterinario> carregarAtendimentosCSV(BiFunction<Integer,Integer, Veterinario> resolveVetByIds,
                                                                java.util.function.Function<Integer, Animal> resolveAnimal) throws IOException {
        List<AtendimentoVeterinario> atendimentos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return atendimentos;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 5) continue;
                int id = Integer.parseInt(linha[0]);
                int animalId = Integer.parseInt(linha[1]);
                int vetId = Integer.parseInt(linha[2]);
                java.util.Date data;
                try { data = sdf.parse(linha[3]); } catch (ParseException ex) { continue; }
                String descricao = linha[4];
                Animal animal = resolveAnimal.apply(animalId);
                Veterinario vet = resolveVetByIds.apply(vetId, vetId);
                if (animal == null || vet == null) continue;
                AtendimentoVeterinario a = new AtendimentoVeterinario(animal, vet, data, descricao);
                a.setId(id);
                atendimentos.add(a);
            }
        }
        return atendimentos;
    }
}
