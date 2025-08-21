package haras.basicas;

import java.util.Date;

public class Colaborador extends Pessoa {
    private String cargo;
    private Date admissao;
    private String formacao;

    public Colaborador(String nome, String endereco, String telefone, String cargo, Date admissao, String formacao) {
        super(nome, endereco, telefone);
        this.cargo = cargo;
        this.admissao = admissao;
        this.formacao = formacao;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getAdmissao() {
        return admissao;
    }
    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getFormacao() {
        return formacao;
    }
    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", cargo='" + cargo + '\'' +
                ", admissao=" + admissao +
                ", formacao='" + formacao + '\'' +
                '}';
    }
}
