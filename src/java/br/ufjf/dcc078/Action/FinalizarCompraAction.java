/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Pagamento.Pagamento;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author allan
 */
public class FinalizarCompraAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        /* ESSE É UM CÓDIGO TEMPORARIO
         * QUANDO A ESTRUTURA DE BANCO DE DADOS ESTIVER BEM DEFINIDA, AQUI VIRÁ
         * A BUSCA DO PRODUTO PELO ID RECEBIDO
         */
        Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "douglas@gmail.com", "123");
        Pedido carrinho = PedidoDAO.getInstance().readCart(usuario);
        request.setAttribute("carrinho", carrinho);
        
        // PROCESSAR PAGAMENTO
        Pagamento cadeiaDePagamento = usuario.getPreferenciasPagamento();
        
        if(cadeiaDePagamento.processarPagamento(carrinho)) {
            carrinho.aguardarConfirmacao();
            PedidoDAO.getInstance().updateState(carrinho);
        }
        
        RequestDispatcher despachante = request.getRequestDispatcher("FrontController?action=ExibirPedido&id=" + carrinho.getId());
        despachante.forward(request, response);
    }
    
}
