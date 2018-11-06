/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Strategy;

/**
 *
 * @author ice
 */
public abstract class AplicarPromocao {

    protected Promocao promocao;
    protected String codigo;

    public double desconto() {
        return promocao.obterDesconto();
    }

    public String getNome() {
        return codigo;
    }

    public String getPromocao() {
        return promocao.obterCodigo();
    }

}
