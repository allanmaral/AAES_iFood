/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Dominio.Pedido;

/**
 *
 * @author Gabriel Maia
 */
public interface EstadoPedido {
    
    public void aguardarConfirmacao(Pedido pedido);

    public void colocarEmProducao(Pedido pedido);
       
    public void encaminhar(Pedido pedido);
      
    public void entregar(Pedido pedido);
    
    public void cancelar(Pedido pedido);
}
