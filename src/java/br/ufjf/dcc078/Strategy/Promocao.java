package br.ufjf.dcc078.Strategy;

public abstract class Promocao {
    
    private int id;
    
    public int getId() {
        return this.id;
    }
    
    public Promocao setId(int id) {
        this.id = id;
        return this;
    }
    
    public abstract double obterDesconto();
    public abstract String obterCodigo();
     
    
}
