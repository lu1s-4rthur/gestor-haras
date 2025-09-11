package haras.dados;

import haras.basicas.Colaborador;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class RepositorioColaborador {
    private String arquivo = "dados/data/colaboradores.dat";
    private String arquivoCsv = "dados/data/colaboradores.csv";

    public void salvarColaboradores(List<Colaborador> colaboradores) throws IOException {
        new File("dados/data").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(colaboradores);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Colaborador> carregarColaboradores() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Colaborador>) ois.readObject();
        }
    }

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarColaboradoresCSV(List<Colaborador> colaboradores) throws IOException {
        new File("dados/data").mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","nome","endereco","telefone","cargo","admissao","formacao"});
            for (Colaborador c : colaboradores) {
                writer.writeNext(new String[]{
                    String.valueOf(c.getId()),
                    c.getNome(),
                    c.getEndereco(),
                    c.getTelefone(),
                    c.getCargo(),
                    sdf.format(c.getAdmissao()),
                    c.getFormacao()
                });
            }
        }
    }

    public List<Colaborador> carregarColaboradoresCSV() throws IOException, CsvValidationException, NumberFormatException {
        List<Colaborador> colaboradores = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) return colaboradores;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean header = false;
            while ((linha = reader.readNext()) != null) {
                if (!header) { header = true; continue; }
                if (linha.length < 7) continue;
                int id = Integer.parseInt(linha[0]);
                String nome = linha[1];
                String endereco = linha[2];
                String telefone = linha[3];
                String cargo = linha[4];
                try {
                    java.util.Date adm = sdf.parse(linha[5]);
                    String formacao = linha[6];
                    Colaborador c = new Colaborador(nome, endereco, telefone, cargo, adm, formacao);
                    c.setId(id);
                    colaboradores.add(c);
                } catch (ParseException e) {
                    // ignora linha inválida
                }
            }
        }
        return colaboradores;
    }
}
