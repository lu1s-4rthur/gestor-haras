package haras.basicas;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import java.io.Serializable;

public class Evento implements Serializable {
    public List<Animal> getParticipantes() {
        return participantes;
    }

    private List<Animal> participantes = new ArrayList<>();
    private static int contador = 1;
    private int id;
    private String tipo;
    private Date data;
    private String local;

    public Evento(String tipo, Date data, String local) {
        this.id = contador++;
        this.tipo = tipo;
        this.data = data;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Evento{id=" + id + ", tipo='" + tipo + "', data=" + data + ", local='" + local + "', participantes=" + participantes.size() + "}";
    }
}
