/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.strategy;

/**
 *
 * @author douglas
 */
public class PedidoCompraDobro extends Pedido{

    public PedidoCompraDobro() {
        this.nome = "Pedido em Compra Dobro";
        this.promocao = new CompraDobro();
    }
    
}
