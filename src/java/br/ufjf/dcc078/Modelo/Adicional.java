/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Modelo;

/**
 *
 * @author allan
 */
public class Adicional extends Componente {
    public Adicional()
    {
    }
    
    public Adicional(String nome, Integer quantidade, Double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }
        
    @Override
    public String toString() {
        return "adicional{" + "nome=" + nome + '}';
    }

    @Override
    public Double getPrecoTotal() {
        return getQuantidade() * getPreco();
    }

    @Override
    public Boolean temSubProduto() {
        return false;
    }
}
