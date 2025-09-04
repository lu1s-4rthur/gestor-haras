package haras.dados;

import haras.basicas.Evento;
import haras.basicas.Animal;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RepositorioEvento {
    private String arquivo = "dados/data/eventos.dat";
    private String arquivoCsv = "dados/data/eventos.csv";

    public void salvarEventos(List<Evento> eventos) throws IOException {
        new File("dados").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(eventos);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Evento> carregarEventos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Evento>) ois.readObject();
        }
    }

    // ===== Persistência CSV (OpenCSV) =====
    // Participantes serão exportados como lista de IDs separados por ';'
    public void salvarEventosCSV(List<Evento> eventos) throws IOException {
        new File("dados/data").mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","tipo","data","local","participantes"});
            for (Evento e : eventos) {
                String participantes = e.getParticipantes()
                    .stream()
                    .map(a -> String.valueOf(a.getId()))
                    .reduce((a,b) -> a+";"+b)
                    .orElse("");
                writer.writeNext(new String[]{
                    String.valueOf(e.getId()),
                    e.getTipo(),
                    sdf.format(e.getData()),
                    e.getLocal(),
                    participantes
                });
            }
        }
    }

    public List<Evento> carregarEventosCSV(Function<Integer, Animal> resolveAnimal) throws IOException {
        List<Evento> eventos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return eventos;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 5) continue;
                int id = Integer.parseInt(linha[0]);
                String tipo = linha[1];
                java.util.Date data;
                try { data = sdf.parse(linha[2]); } catch (ParseException ex) { continue; }
                String local = linha[3];
                String participantesStr = linha[4];
                Evento e = new Evento(tipo, data, local);
                e.setId(id);
                if (participantesStr != null && !participantesStr.isEmpty()) {
                    for (String pid : participantesStr.split(";")) {
                        try {
                            int animalId = Integer.parseInt(pid.trim());
                            Animal a = resolveAnimal.apply(animalId);
                            if (a != null) {
                                e.getParticipantes().add(a);
                            }
                        } catch (NumberFormatException ignore) {}
                    }
                }
                eventos.add(e);
            }
        }
        return eventos;
    }
}
