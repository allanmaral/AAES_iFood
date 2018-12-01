package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Adicional;
import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.ListaDeProdutos;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Persistencia.ComponenteDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetalhesProdutoAction implements Action{

    private RequestDispatcher despachante;
    private final List<Produto> produtos = ListaDeProdutos.getInstance();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        
        String idProduto = request.getParameter("idPdt");
        int id = Integer.parseInt(idProduto);
        
        Componente componente = ComponenteDAO.getInstance().readById(id);
        Produto produto = null;
        if(componente instanceof Produto) {
            produto = (Produto) componente;
        }
        
        request.setAttribute("produto", produto);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/DetalhesProduto.jsp");
        despachante.forward(request, response);
    }
    
}
