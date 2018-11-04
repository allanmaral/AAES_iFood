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
public class TesteStrategy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Pedido produto1 = new PedidoCompraDobro();
        Pedido produto2 = new PedidoFreteGratis();

        System.out.println(produto1.getNome() + " na promoção " + produto1.getPromocao() + " com desconto de " + produto1.desconto() + "%");
        System.out.println(produto2.getNome() + " na promoção " + produto2.getPromocao() + " com desconto de " + produto2.desconto() + "%");

    }

}
