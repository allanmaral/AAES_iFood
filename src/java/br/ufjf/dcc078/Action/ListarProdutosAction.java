/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.ListaDeProdutos;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Persistencia.ComponenteDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author allan
 */
public class ListarProdutosAction implements Action{

    private RequestDispatcher despachante;
    private ArrayList<Componente> produtos;
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        /* ESSE É UM CÓDIGO TEMPORARIO
         * QUANDO A ESTRUTURA DE BANCO DE DADOS ESTIVER BEM DEFINIDA, AQUI VIRÁ
         * A BUSCA DE PRODUTOS QUE O CLIETE PODE COLOCAR EM SEU CARRINHO
         */
        produtos = ComponenteDAO.getInstance().readList();
        
        request.setAttribute("produtos", produtos);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/ProdutosListados.jsp");
        despachante.forward(request, response);
    }
    
}
