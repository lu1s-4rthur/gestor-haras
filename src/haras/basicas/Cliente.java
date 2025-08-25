package haras.basicas;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private static int contador = 1;
    private int id;
    private List<Contrato> contratos;

    public Cliente(String nome, String endereco, String telefone) {
        super(nome, endereco, telefone);
        this.id = contador++;
        this.contratos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addContrato(Contrato contrato) {
        this.contratos.add(contrato);
    }

    public void removeContrato(Contrato contrato) {
        this.contratos.remove(contrato);
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", contratos=" + contratos +
                '}';
    }

}
