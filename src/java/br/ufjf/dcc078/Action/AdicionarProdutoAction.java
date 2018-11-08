/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Adicional;
import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.ListaDePedidos;
import br.ufjf.dcc078.Modelo.ListaDeProdutos;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Persistencia.ComponenteDAO;
import br.ufjf.dcc078.Persistencia.ComponentePedidoDAO;
import br.ufjf.dcc078.Persistencia.PedidoDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author allan
 */
public class AdicionarProdutoAction implements Action{

    private List<Pedido> pedidos = ListaDePedidos.getInstance();
    
    private void montarComponente(Componente componente, HttpServletRequest request) {
        int quantidade = 0;
        if(request.getParameter("prodId" + componente.getId()) != null) {
            quantidade = Integer.parseInt(request.getParameter("prodId" + componente.getId()));
        }
        
        componente.setQuantidade(quantidade);
        
        if(componente.temSubProduto()) {
            Produto p = (Produto) componente;
            for(Iterator<Componente> it = p.getComponentes().iterator(); it.hasNext(); ) {
                montarComponente(it.next(), request);
            }
        }
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        /* ESSE É UM CÓDIGO TEMPORARIO
         * QUANDO A ESTRUTURA DE BANCO DE DADOS ESTIVER BEM DEFINIDA, AQUI VIRÁ
         * A BUSCA DO PRODUTO PELO ID RECEBIDO
         */
        Usuario usuario = new Usuario(2, "Douglas Baumgratz", "doug", "douglas@gmail.com", "123");
        Pedido carrinho = PedidoDAO.getInstance().readCart(usuario);
        request.setAttribute("carrinho", carrinho);
        
        String idProduto = request.getParameter("idPdt");
        int id = Integer.parseInt(idProduto);
        
        Componente componente = ComponenteDAO.getInstance().readById(id);
        montarComponente(componente, request);
        
        try {
            ComponentePedidoDAO.getInstance().addComponent(carrinho.getId(), componente);
        } catch (SQLException ex) {
            Logger.getLogger(AdicionarProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(componente.getPreco());
        System.out.println(componente.getPrecoTotal());
        
        RequestDispatcher despachante = request.getRequestDispatcher("FrontController?action=ExibirCarrinho");
        despachante.forward(request, response);
    }
    
}
