/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Strategy;

import br.ufjf.dcc078.Dominio.Produto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ice
 */
public class Pedido {

    protected Promocao promocao;
    protected String nome;
    List<Produto> lista = new ArrayList<>();

    public void addLista(Produto p) {
        this.lista.add(p);
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }

    public int desconto() {
        return promocao.obterDesconto();
    }

    public String getNome() {
        return nome;
    }

    public String getPromocao() {
        return promocao.obterPromocao();
    }

    public Double getTotal() {
        Double total = 0.0;

        for (int i = 0; i < lista.size(); i++) {
            total += lista.get(i).getQuantidade() * lista.get(i).getPreco();
        }
        return total;
    }
}
