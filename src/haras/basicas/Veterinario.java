package haras.basicas;

import java.util.Date;

public class Veterinario extends Colaborador {
    private String crmv;

    public Veterinario(String nome, String endereco, String telefone, String cargo, Date admissao, String formacao, String crmv) {
        super(nome, endereco, telefone, cargo, admissao, formacao);
        this.crmv = crmv;
    }

    public Prescricao emitirPrescricao(int id, String descricao, Animal animal) {
        return new Prescricao(descricao, this, animal);
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "crmv='" + crmv + '\'' +
                "} " + super.toString();
    }
}
