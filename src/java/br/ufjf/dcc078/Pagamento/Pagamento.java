/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Pagamento;

import br.ufjf.dcc078.Modelo.Pedido;

/**
 *
 * @author allan
 */
public abstract class Pagamento {

    protected Pagamento sucessor;
    
    public Pagamento() {
        this.sucessor = null;
    }

    public Pagamento getSucessor() {
        return sucessor;
    }

    public void setSucessor(Pagamento sucessor) {
        this.sucessor = sucessor;
    }
    
    public Boolean pagar(Pedido pedido) {
        Boolean resultado = false;
        // Gerar nota fiscal
        // TODO
        
        if(autenticarPagamento()){
            resultado = true;
        }
        
        return resultado;        
    }
    
    Boolean processarPagamento(Pedido pedido) {
        if(!pagar(pedido)) {
            if(sucessor != null) {
                return sucessor.processarPagamento(pedido);
            }
            else {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    
    abstract Boolean autenticarPagamento();

    
}
