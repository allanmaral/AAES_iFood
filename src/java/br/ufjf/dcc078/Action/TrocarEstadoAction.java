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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author douglas
 */
public class TrocarEstadoAction implements Action {

    private RequestDispatcher despachante;

    private void AtualizarPedido(String methodName, String idPedidoStr) {
        if(idPedidoStr != null) {
            int idPedido = Integer.parseInt(idPedidoStr);
            Pedido pedidoSelecionado = PedidoDAO.getInstance().readById(idPedido);
            
            if(methodName != null) {
                try {
                    Class c = pedidoSelecionado.getClass();
                    Method method = c.getDeclaredMethod(methodName);
                    System.out.println(method);
                    method.invoke(pedidoSelecionado);
                    
                    PedidoDAO.getInstance().updateState(pedidoSelecionado);
                
                } catch (SecurityException | 
                         IllegalArgumentException | 
                         NoSuchMethodException | 
                         IllegalAccessException | 
                         InvocationTargetException
                         ex) {
                    System.out.println(ex.getCause());
                    Logger.getLogger(TrocarEstadoAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String methodName = request.getParameter("estado");
        String idPedidoStr = request.getParameter("pedido");
        
        AtualizarPedido(methodName, idPedidoStr);
        
        ArrayList<Pedido> listaPedidos = PedidoDAO.getInstance().readAll();
        
        request.setAttribute("listaPedidos", listaPedidos);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/TrocarEstadoPedido.jsp");
        despachante.forward(request, response);
    }

}
