/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Dominio.ListaDePedidos;
import br.ufjf.dcc078.Dominio.Pedido;
import br.ufjf.dcc078.Dominio.Produto;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author allan
 */
public class ControlePedidosAction implements Action{

    private RequestDispatcher despachante;
    private List<Pedido> pedidos = new ListaDePedidos().getInstance();    
    private List<Produto> produto;
    private Pedido pedido;
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        request.setAttribute("pedidos", pedidos);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/PedidosSolicitados.jsp");
        despachante.forward(request, response);
    }
    
}
