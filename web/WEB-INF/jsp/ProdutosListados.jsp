
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.ufjf.dcc078.Dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="br.ufjf.dcc078.Dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../jspf/cabecalho.jspf" %>

<style>
    a.unlink { color: inherit; }
    a.unlink:hover { color: inherit; text-decoration: none; }
</style>

<div class="container" style="margin-top:30px">
    <div class="text-center">
        <h2>Produtos</h2><br>
    </div>
    
    <div class="container">
        
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <c:choose>
                    <c:when test="${not empty produtos}">
                        <c:forEach var="produto" items="${produtos}">
                            <a class="unlink" href="">
                                <div class="border-top">
                                    <br>
                                    <h4><c:out value="${produto.getNome()}"/></h4>
                                    <p>Uma descrição para o item vem aqui</p>    
                                    <label class="text-success">R$ <c:out value="${produto.getPreco()}"/></label><br>
                                </div><br>
                            </a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </div>
</div>

<%@include file="../jspf/rodape.jspf" %>