/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.ListaDePedidos;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
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
public class ExibirPedidoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String id = request.getParameter("id");
        Integer id_num = Integer.parseInt(id);
        
        Pedido pedido = PedidoDAO.getInstance().readById(id_num);
        
        request.setAttribute("pedido", pedido);
        request.setAttribute("idPedido", pedido.getId());
        RequestDispatcher despachante = request.getRequestDispatcher("WEB-INF/jsp/ExibirPedido.jsp");
        despachante.forward(request, response);
    }
    
}
