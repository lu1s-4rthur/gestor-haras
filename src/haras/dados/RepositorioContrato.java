package haras.dados;

import haras.basicas.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RepositorioContrato {
    private String arquivo = "dados/data/contratos.dat";
    private String arquivoCsv = "dados/data/contratos.csv";

    public void salvarContratos(List<Contrato> contratos) throws IOException {
        new File("dados/data").mkdirs();
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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarContratosCSV(List<Contrato> contratos) throws IOException {
        new File("dados/data").mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","clienteId","animalId","servicoId","dataInicio","dataFim","status"});
            for (Contrato c : contratos) {
                writer.writeNext(new String[]{
                    String.valueOf(c.getId()),
                    String.valueOf(c.getCliente().getId()),
                    String.valueOf(c.getAnimal().getId()),
                    String.valueOf(c.getServico().getId()),
                    sdf.format(c.getDataInicio()),
                    sdf.format(c.getDataFim()),
                    c.getStatus()
                });
            }
        }
    }

    public List<Contrato> carregarContratosCSV(Function<Integer, Cliente> resolveCliente,
                                               Function<Integer, Animal> resolveAnimal,
                                               Function<Integer, Servico> resolveServico) throws IOException, CsvValidationException, NumberFormatException {
        List<Contrato> contratos = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return contratos;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 7) continue;
                int id = Integer.parseInt(linha[0]);
                int clienteId = Integer.parseInt(linha[1]);
                int animalId = Integer.parseInt(linha[2]);
                int servicoId = Integer.parseInt(linha[3]);
                java.util.Date inicio;
                java.util.Date fim;
                try {
                    inicio = sdf.parse(linha[4]);
                    fim = sdf.parse(linha[5]);
                } catch (ParseException ex) { continue; }
                String status = linha[6];
                Cliente cliente = resolveCliente.apply(clienteId);
                Animal animal = resolveAnimal.apply(animalId);
                Servico servico = resolveServico.apply(servicoId);
                if (cliente == null || animal == null || servico == null) continue;
                try {
                    Contrato c = new Contrato(cliente, animal, servico, inicio, fim, status);
                    c.setId(id);
                    contratos.add(c);
                } catch (IllegalArgumentException ignore) { }
            }
        }
        return contratos;
    }
}
