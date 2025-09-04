package haras.basicas;

public class Reproducao extends Servico {
    private Animal pai;
    private Animal mae;

    public Reproducao(String tipo, double valor, Animal pai, Animal mae) {
        super(tipo,valor);
        this.pai = pai;
        this.mae = mae;
    }

    public Animal getPai() {
        return pai;
    }
    public void setPai(Animal pai) {
        this.pai = pai;
    }

    public Animal getMae() {
        return mae;
    }
    public void setMae(Animal mae) {
        this.mae = mae;
    }
    
}
