package haras.basicas;

import java.io.Serializable;

public class Servico implements Serializable {
    private static int contador = 1;
    private int id;
    private String tipo;
    private double valor;

    public Servico(String tipo, double valor) {
        this.id = contador++;
        this.tipo = tipo;
        this.valor = valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico{id=" + id + ", tipo='" + tipo + "', valor=" + valor + "}";
    }

    public static void ajustarContadorAPartirDoMaximo(int maxId) {
        if (maxId + 1 > contador) {
            contador = maxId + 1;
        }
    }
}
