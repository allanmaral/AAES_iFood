/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.strategy;

/**
 *
 * @author ice
 */
public abstract class AplicaPromocao {
    
 protected Promocao promocao;
    protected String nome;

    public int desconto() {
        return promocao.obterDesconto();
    }

    public String getNome() {
        return nome;
    }

    public String getPromocao(){
        return promocao.obterPromocao();
    }
 
    
    
}
