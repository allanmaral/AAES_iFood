
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.ufjf.dcc078.Modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="br.ufjf.dcc078.Modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../jspf/cabecalho.jspf" %>

<div class="container" style="margin-top:30px">
    <div class="text-center">
        <h2>Pedidos</h2><br>
    </div>
    <table border = 1 class="table table-dark table-striped text-center">        
        <th>Situação</th>
        <th>Total Pedido</th>
        <th>Itens Solicitados</th> 
        
        <c:choose>
            <c:when test="${not empty pedidos}">
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>                            
                        <td><c:out value="${pedido.getEstado().getNome()}"/></td>
                        <td><label>R$ <c:out value="${pedido.getTotal()}"/></label></td>    
                        <td><a href="FrontController?action=ExibirPedido&id=<c:out value="${pedido.getId()}" />">Visualizar</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
    </table><br>
    
</div>

<%@include file="../jspf/rodape.jspf" %>