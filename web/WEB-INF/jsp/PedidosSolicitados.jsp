
<%@page import="br.ufjf.dcc078.Dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="br.ufjf.dcc078.Dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../jspf/cabecalho.jspf" %>

<div class="container" style="margin-top:30px">
    <div class="text-center">
        <h2>Pedidos</h2><br>
    </div>
    <table border = 1 class="table table-dark table-striped text-center">        
        <th>Situação</th>
        <th>Abertura</th>
        <th>Encerramento</th>
        <th>Total Pedido</th>
        <th>Itens Solicitados</th>
        <th>Add Produto</th>
        <th>Fechar Pedido</th>  
        
        
            <%
                int i = 0;
                for (Pedido pedido : (List<Pedido>) request.getAttribute("pedido")) {
            %>  
        <tr>                            
            <td><%=((pedido.getSituacao()) ? "Aberto" : "Fechado")%></a></td>            
            <td><%=pedido.getHorarioAbertura()%></td>
            <td><%=pedido.getHorarioEncerramento()%></td>  
            <td><label>R$:<%=pedido.getTotal()%></label></td>    
            <td><a href="">Visualizar</a></td>
            <td><a href="">Adicionar</a></td>
            <td><a href=""><label>Finalizar Pedido</label></a></td>
        </tr>
        <%
                i++;
            }
        %>        
    </table><br>
    
</div>

<%@include file="../jspf/rodape.jspf" %>