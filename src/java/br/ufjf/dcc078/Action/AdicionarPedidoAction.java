/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
public class AdicionarPedidoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titulo = request.getParameter("txtTitulo");
        String promocao = request.getParameter("txtPromocao");

        if (titulo.equals("") || promocao.equals("")) {
            response.sendRedirect("index.jsp");
        } else {
            try {
                
                /*  
                    TODO: Promoção
                */
                
                Pedido pedido = new Pedido(titulo);

                try {
                    PedidoDAO.getInstance().save(pedido);
                } catch (ClassNotFoundException ex) {
                    response.sendRedirect("WEB-INF/jsp/AdicionarPedidoErro.jsp");
                    Logger.getLogger(AdicionarPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("WEB-INF/jsp/AdicionarPedidoSucesso.jsp");
            } catch (SQLException ex) {
                response.sendRedirect("WEB-INF/jsp/AdicionarPedidoErro.jsp");
                ex.printStackTrace();
            }

        }
    }

}
