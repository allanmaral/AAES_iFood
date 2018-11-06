/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Dominio.Adicional;
import br.ufjf.dcc078.Dominio.ListaDeProdutos;
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
public class DetalhesProdutoAction implements Action{

    private RequestDispatcher despachante;
    private final List<Produto> produtos = ListaDeProdutos.getInstance();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        /* ESSE É UM CÓDIGO TEMPORARIO
         * QUANDO A ESTRUTURA DE BANCO DE DADOS ESTIVER BEM DEFINIDA, AQUI VIRÁ
         * A BUSCA DO PRODUTO PELO ID RECEBIDO
         */
        
        String idProduto = request.getParameter("idPdt");
        
        Produto produto = new Produto();
        produto.setNome("X Bacon").setDescricao("Pão de hamburguer classico, bife, ovo, extra bacon, queijo, batata palha e salada").setPreco(10.0);
        Adicional adicional1 = new Adicional();
        adicional1.setNome("Maionese").setPreco(0.5);
        Adicional adicional2 = new Adicional();
        adicional2.setNome("Molho Barbecue").setPreco(2.0);
        produto.adicionarComponente(adicional1);
        produto.adicionarComponente(adicional2);
        
        request.setAttribute("produto", produto);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/DetalhesProduto.jsp");
        despachante.forward(request, response);
    }
    
}
