
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.ufjf.dcc078.Dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="br.ufjf.dcc078.Dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../jspf/cabecalho.jspf" %>

<div class="container" style="margin-top:30px">
    <div class="text-center">
        <h2>Produtos</h2><br>
    </div>
    <table border = 1 class="table table-dark table-striped text-center">        
        <th>Nome</th>
        <th>Quantidade</th>
        <th>Preco</th>
        <th>Adicionar ao carrinho</th>
        
        <c:choose>
            <c:when test="${not empty produtos}">
                <c:forEach var="produto" items="${produtos}">
                    <tr>                            
                        <td><c:out value="${produto.getNome()}"/></td>   
                        <td><c:out value="${produto.getQuantidade()}"/></td>    
                        <td><label>R$ <c:out value="${produto.getPreco()}"/></label></td>
                        <td><a href="">Adicionar</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
    </table><br>
    <%--<c:choose>
        <c:when test="${not empty produtos}">
            <c:forEach var="produto" items="${produtos}">
                <div class="text-center">
                    <c:out value="${produto.getNome()}"/>  
                    <c:out value="${produto.getQuantidade()}"/>    
                    R$ <c:out value="${produto.getPreco()}"/>
                    <a href="">Adicionar</a></td>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>--%>
    
</div>

<%@include file="../jspf/rodape.jspf" %>