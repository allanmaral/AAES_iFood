/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;
import java.util.Observable;

/**
 *
 * @author Gabriel Maia
 */
public class PedidoCancelado implements EstadoPedido{

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
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }
    
    @Override
    public String getNome() {
        return "Cancelado";
    }
}
