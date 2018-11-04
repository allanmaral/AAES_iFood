package br.ufjf.dcc078.Servlet;

import br.ufjf.dcc078.Dominio.ListaDePedidos;
import br.ufjf.dcc078.Strategy.Pedido;
import br.ufjf.dcc078.Dominio.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlePedidos", urlPatterns = {"/ControlePedidos.html"})
public class ControleServlet extends HttpServlet {

    private RequestDispatcher despachante;
    private List<Pedido> pedidos = new ListaDePedidos().getInstance();    
    private List<Produto> produto;
    private Pedido pedido;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("/ControlePedidos.html".equals(request.getServletPath())) {
            doGetControlePedidos(request, response);
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("ControlePedidos.html");
    }

    public void doGetControlePedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pedido", pedidos);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/PedidosSolicitados.jsp");
        despachante.forward(request, response);
    }

}
