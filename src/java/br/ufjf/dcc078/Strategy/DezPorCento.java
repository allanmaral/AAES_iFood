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
public class DezPorCento implements Promocao {

    @Override
    public double obterDesconto() {
        return 0.9;
    }

    @Override
    public String obterCodigo() {
        return "DEZPORCENTO";
    }

}