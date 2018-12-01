package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Modelo.Pedido;

public interface EstadoPedido {
    
    public void aguardarConfirmacao(Pedido pedido);

    public void colocarEmProducao(Pedido pedido);
       
    public void encaminhar(Pedido pedido);
      
    public void entregar(Pedido pedido);
    
    public void cancelar(Pedido pedido);
    
    public String getNome();
}
