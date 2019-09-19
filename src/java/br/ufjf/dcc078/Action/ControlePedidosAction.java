package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlePedidosAction implements Action{
    private Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "allanmaralr@gmail.com", "123");
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        Pedido carrinho = PedidoDAO.getInstance().readCart(usuario);
        if(carrinho == null){
            PedidoDAO.getInstance().saveCart(usuario);
        }
        
        List<Pedido> pedidos = PedidoDAO.getInstance().readList(usuario);
        
        
        request.setAttribute("pedidos", pedidos);
        RequestDispatcher despachante = request.getRequestDispatcher("WEB-INF/jsp/PedidosSolicitados.jsp");
        despachante.forward(request, response);
    }
    
}
