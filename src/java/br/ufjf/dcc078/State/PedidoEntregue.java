/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;

/**
 *
 * @author Gabriel Maia
 */
public class PedidoEntregue implements EstadoPedido{

    @Override
    public void aguardarConfirmacao(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void colocarEmProducao(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void encaminhar(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.setEstado(new PedidoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }
    
    @Override
    public String getNome() {
        return "Entregue";
    }
    
    @Override
    public String toString() {
        return "Entregue";
    }
    
}
