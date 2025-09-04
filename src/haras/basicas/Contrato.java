package haras.basicas;

import java.util.Date;

import java.io.Serializable;

public class Contrato implements Serializable {
    private static int contador = 1;
    private int id;
    private Cliente cliente;
    private Animal animal;
    private Servico servico;
    private Date dataInicio;
    private Date dataFim;
    private String status;

    public Contrato(Cliente cliente, Animal animal, Servico servico, Date dataInicio, Date dataFim, String status) {
        this.id = contador++;
        if (dataInicio.before(new Date())) {
            throw new IllegalArgumentException("Data de início do contrato não pode ser no passado.");
        }
        if (dataFim.before(dataInicio)) {
            throw new IllegalArgumentException("Data de fim do contrato deve ser após a data de início.");
        }
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
        return "Contrato{id=" + id + ", cliente='" + cliente.getNome() + "', animal='" + animal.getNome() + "', servico='" + servico.getTipo() + "', status='" + status + "'}";
    }

    public static void ajustarContadorAPartirDoMaximo(int maxId) {
        if (maxId + 1 > contador) {
            contador = maxId + 1;
        }
    }

}
