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
public class CompraDobro implements Promocao {

    @Override
    public int obterDesconto() {
        return 50;
    }

    @Override
    public String obterPromocao() {
        return "Compra em Dobro";
    }
}
