/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Dominio.Pedido;
import java.util.Observable;

/**
 *
 * @author Gabriel Maia
 */
public class PedidoAguardandoConfirmacao implements EstadoPedido{

    @Override
    public void aguardarConfirmacao(Pedido pedido) {
        pedido.setEstado(new PedidoAguardandoConfirmacao());
    }

    @Override
    public void colocarEmProducao(Pedido pedido) {
        pedido.setEstado(new PedidoEmProducao());
    }

    @Override
    public void encaminhar(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void entregar(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }
    
}
