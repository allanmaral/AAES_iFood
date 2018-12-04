/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Persistencia.PromocaoDAO;
import br.ufjf.dcc078.Servlet.Action;
import br.ufjf.dcc078.Strategy.Promocao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author allan
 */
public class AplicarPromocaoAction implements Action{
    private Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "allanmaralr@gmail.com", "123");
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        Pedido carrinho = PedidoDAO.getInstance().readCart(usuario);
        request.setAttribute("carrinho", carrinho);
        
        String codigoPromocao = request.getParameter("codigoPromo");
        
        Promocao promocao = PromocaoDAO.getInstance().read(codigoPromocao);
        carrinho.setPromocao(promocao);
        
        PedidoDAO.getInstance().update(carrinho);
        
        RequestDispatcher despachante = request.getRequestDispatcher("FrontController?action=ExibirCarrinho");
        despachante.forward(request, response);
    }
    
}
