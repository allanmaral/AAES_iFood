package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;

public class PedidoEmProducao implements EstadoPedido{

    @Override
    public void aguardarConfirmacao(Pedido pedido) {
        System.out.println("Não é possível alcançar o estado.");
    }

    @Override
    public void colocarEmProducao(Pedido pedido) {
        pedido.setEstado(new PedidoEmProducao());
    }

    @Override
    public void encaminhar(Pedido pedido) {
        pedido.setEstado(new PedidoEncaminhado());
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
        return "Em Produção";
    }
    
    @Override
    public String toString() {
        return "Em Producao";
    }
    
}
