package haras.basicas;

import java.util.Date;

public class Administrador extends Colaborador {
    public Administrador(String nome, String endereco, String telefone, String cargo, Date admissao, String formacao) {
        super(nome, endereco, telefone, cargo, admissao, formacao);
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", cargo='" + getCargo() + '\'' +
                ", admissao=" + getAdmissao() +
                ", formacao='" + getFormacao() + '\'' +
                '}';
    }
    
}
