package haras.dados;

import haras.basicas.Animal;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class RepositorioAnimal {
private String arquivo = "dados/data/animais.dat";
private String arquivoCsv = "dados/data/animais.csv";

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

    // ===== Persistência CSV (OpenCSV) =====
    public void salvarAnimaisCSV(List<Animal> animais) throws IOException {
        new File("dados/data").mkdirs();
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCsv))) {
            writer.writeNext(new String[]{"id","nome","raca","genero","cor","idade","estadoSaude","reprodutor"});
            for (Animal a : animais) {
                writer.writeNext(new String[]{
                    String.valueOf(a.getId()),
                    a.getNome(),
                    a.getRaca(),
                    a.getGenero(),
                    a.getCor(),
                    String.valueOf(a.getIdade()),
                    a.getEstadoSaude(),
                    String.valueOf(a.isReprodutor())
                });
            }
        }
    }

    public List<Animal> carregarAnimaisCSV() throws IOException {
        List<Animal> animais = new ArrayList<>();
        File file = new File(arquivoCsv);
        if (!file.exists()) {
            return animais;
        }
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] linha;
            boolean headerLido = false;
            while ((linha = reader.readNext()) != null) {
                if (!headerLido) { // pular cabeçalho
                    headerLido = true;
                    continue;
                }
                if (linha.length < 8) continue;
                // ordem: id,nome,raca,genero,cor,idade,estadoSaude,reprodutor
                int id = Integer.parseInt(linha[0]);
                String nome = linha[1];
                String raca = linha[2];
                String genero = linha[3];
                String cor = linha[4];
                int idade = Integer.parseInt(linha[5]);
                String estadoSaude = linha[6];
                boolean reprodutor = Boolean.parseBoolean(linha[7]);

                Animal a = new Animal(nome, raca, genero, cor, idade, estadoSaude);
                a.setId(id);
                a.setReprodutor(reprodutor);
                animais.add(a);
            }
        }
        return animais;
    }
}
