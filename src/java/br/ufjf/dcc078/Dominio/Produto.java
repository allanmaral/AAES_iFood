package br.ufjf.dcc078.Dominio;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author douglas
 */
public class Produto extends Componente {
    
    private ArrayList<Componente> componentes;
    
    public Produto()
    {
        this.componentes = new ArrayList<>();
    }
    
    public Produto(String nome, Integer quantidade, Double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.componentes = new ArrayList<>();
    }
    
    public void adicionarComponente(Componente componente)
    {
        this.componentes.add(componente);
    }
    
    @Override
    public String toString() {
        return "produtos{" + "nome=" + nome + '}';
    }

    @Override
    public Double getPrecoTotal() {
        Double precoTotal = getQuantidade() * getPreco();
        for(Iterator<Componente> it = componentes.iterator(); it.hasNext(); ) {
            precoTotal += it.next().getPrecoTotal();
        }
        return precoTotal;
    }
    
    
}
