package haras.basicas;

import java.util.Date;

public class Contrato {
    private int id;
    private Cliente cliente;
    private Animal animal;
    private Servico servico;
    private Date dataInicio;
    private Date dataFim;
    private String status;

    public Contrato(int id, Cliente cliente, Animal animal, Servico servico, Date dataInicio, Date dataFim, String status) {
        this.id = id;
        this.cliente = cliente;
        cliente.addContrato(this);
        this.animal = animal;
        this.servico = servico;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", animal=" + animal +
                ", servico=" + servico +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", status='" + status + '\'' +
                '}';
    }
    
}
