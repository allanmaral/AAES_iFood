package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;

public class PedidoCarrinho implements EstadoPedido{

    public void PedidoAguardandoConfirmacao(){
    }
    
    @Override
    public void aguardarConfirmacao(Pedido pedido) {
        pedido.setEstado(new PedidoAguardandoConfirmacao());
    }

    @Override
    public void colocarEmProducao(Pedido pedido) {
        
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
        return "Carrinho";
    }
    
    @Override
    public String toString() {
        return "Carrinho";
    }
    
}
