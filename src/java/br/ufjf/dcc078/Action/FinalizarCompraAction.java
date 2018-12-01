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

public class FinalizarCompraAction implements Action{
    private Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "allanmaralr@gmail.com", "123");
    
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        
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
