package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;
import java.util.Observable;

public class PedidoEncaminhado implements EstadoPedido{

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
        pedido.setEstado(new PedidoEncaminhado());
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.setEstado(new PedidoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }
    
    @Override
    public String getNome() {
        return "Encaminhado";
    }
    
    @Override
    public String toString() {
        return "Encaminhado";
    }
    
}
