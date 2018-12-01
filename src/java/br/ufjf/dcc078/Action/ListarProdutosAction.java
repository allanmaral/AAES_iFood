package br.ufjf.dcc078.Action;

import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Persistencia.ComponenteDAO;
import br.ufjf.dcc078.Servlet.Action;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarProdutosAction implements Action{

    private RequestDispatcher despachante;
    private ArrayList<Componente> produtos;
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {

        produtos = ComponenteDAO.getInstance().readList();
        
        request.setAttribute("produtos", produtos);
        despachante = request.getRequestDispatcher("WEB-INF/jsp/ProdutosListados.jsp");
        despachante.forward(request, response);
    }
    
}
