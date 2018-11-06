/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Pagamento;

/**
 *
 * @author allan
 */
public class PagamentoCartao extends Pagamento {

    @Override
    Boolean autenticarPagamento() {
        // VALIDA O PAGAMENTO COM O SERVIDOR EXTERNO
        return true;
    }
    
}
