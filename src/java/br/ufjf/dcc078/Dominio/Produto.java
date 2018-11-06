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
        // REMOVER ISSO
        this.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id felis orci. Fusce gravida pulvinar iaculis. Duis ut massa mattis, accumsan metus vitae, aliquet massa. Duis euismod placerat porta. Aliquam convallis vitae justo et tincidunt. Donec enim diam, mollis at tellus sit amet, molestie mattis enim. Vivamus in ligula est. Praesent sagittis ultrices tincidunt. Mauris rhoncus id lorem non maximus. ";
    }
    
    public Produto(String nome, Integer quantidade, Double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.componentes = new ArrayList<>();
        // REMOVER ISSO
        this.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id felis orci. Fusce gravida pulvinar iaculis. Duis ut massa mattis, accumsan metus vitae, aliquet massa. Duis euismod placerat porta. Aliquam convallis vitae justo et tincidunt. Donec enim diam, mollis at tellus sit amet, molestie mattis enim. Vivamus in ligula est. Praesent sagittis ultrices tincidunt. Mauris rhoncus id lorem non maximus. ";
    }
    
    public void adicionarComponente(Componente componente)
    {
        this.componentes.add(componente);
    }
    
    public ArrayList<Componente> getComponentes() {
        return this.componentes;
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
