package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Persistencia.ComponenteDAO;
import br.ufjf.dcc078.Persistencia.ComponentePedidoDAO;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionarProdutoAction implements Action{
    private Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "allanmaralr@gmail.com", "123");
    
    private void montarComponente(Componente componente, HttpServletRequest request) {
        int quantidade = 0;
        if(request.getParameter("prodId" + componente.getId()) != null) {
            quantidade = Integer.parseInt(request.getParameter("prodId" + componente.getId()));
        }
        
        componente.setQuantidade(quantidade);
        
        if(componente.temSubProduto() && quantidade > 0) {
            Produto p = (Produto) componente;
            for(Iterator<Componente> it = p.getComponentes().iterator(); it.hasNext(); ) {
                montarComponente(it.next(), request);
            }
        }
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        Pedido carrinho = PedidoDAO.getInstance().readCart(usuario);
        request.setAttribute("carrinho", carrinho);
        
        String idProduto = request.getParameter("idPdt");
        int id = Integer.parseInt(idProduto);
        
        Componente componente = ComponenteDAO.getInstance().readById(id);
        montarComponente(componente, request);
        
        ComponentePedidoDAO.getInstance().addComponent(carrinho.getId(), componente);
        
        
        RequestDispatcher despachante = request.getRequestDispatcher("FrontController?action=ExibirCarrinho");
        despachante.forward(request, response);
    }
    
}
