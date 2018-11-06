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
public abstract class Componente {
    protected String nome;
    protected String descricao;
    protected Integer quantidade;    
    protected Double preco;

    public Componente() {
    }
    
    public Componente(String nome) {
        this.nome = nome;
        this.quantidade = 1;
        this.preco = 1.0;
    }

    public String getNome() {
        return nome;
    }

    public Componente setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public Componente setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Componente setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public Double getPreco() {
        return preco;
    }

    public Componente setPreco(Double preco) {
        this.preco = preco;
        return this;
    }
    
    abstract public Double getPrecoTotal() ;
    
}
