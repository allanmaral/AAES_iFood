/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Strategy;

/**
 *
 * @author douglas
 */
public class PedidoFreteGratis extends AplicarPromocao{

    public PedidoFreteGratis() {
        this.nome = "Pedido Frete Grátis";
        this.promocao = new FreteGratis();
    }
    
}
