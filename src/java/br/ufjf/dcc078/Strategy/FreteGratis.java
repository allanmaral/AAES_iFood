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
public class FreteGratis implements Promocao {

    @Override
    public int obterDesconto() {
        return 10;
    }

    @Override
    public String obterPromocao() {
        return "Frete Grátis";
    }

}
