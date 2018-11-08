
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.ufjf.dcc078.Modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="br.ufjf.dcc078.Modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../jspf/cabecalho.jspf" %>

<style>
    a.unlink { color: inherit; }
    a.unlink:hover { color: inherit; text-decoration: none; }
</style>

<div class="container" style="margin-top:30px">
    <div class="text-center">
        <h2>Detalhes</h2><br>
    </div>
    
    <div class="container">
        
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <c:choose>
                    <c:when test="${not empty produto}">
                        <div class="border-top border-bottom">
                            <br>
                            <h4><c:out value="${produto.getNome()}"/></h4>
                            <p><c:out value="${produto.getDescricao()}"/></p>    
                            <label class="text-success">R$ <c:out value="${produto.getPreco()}"/></label><br>
                        
                            <div class="col-lg-12">
                                <form class="form-horizontal" action="FrontController?action=AdicionarProduto" method="post">
                                    <c:forEach var="prd" items="${produto.getComponentes()}">
                                        <div class="form-group row">
                                            <c:choose>
                                                <c:when test="${prd.temSubProduto()}">
                                                    <a class="unlink col-lg-1" href='FrontController?action=DetalhesProduto&idPdt=<c:out value="${prd.getId()}"/>'> 
                                                        <i class="glyphicon glyphicon-check">+</i>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="col-lg-1" href='FrontController?action=DetalhesProduto&idPdt=<c:out value="${prd.getId()}"/>'> 
                                                        <i class="glyphicon glyphicon-check"></i>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            
                                            <label class="control-label col-lg-7"><c:out value="${prd.getNome()}"/></label>
                                            <label class="text-danger control-label col-lg-2">R$ <c:out value="${prd.getPreco()}"/></label>
                                            <div class="col-lg-2">
                                                <input type="number" class="form-control" name="quantidade" value="0" />
                                            </div>
                                        </div>
                                    </c:forEach>

                                    <div class="text-center">
                                        <div class="form-group">
                                            <label class="control-label" for="quantidade">Quantidade:</label> 
                                            <input type="number" class="form-control" name="quantidade" value="1" />
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">
                                                Adicionar
                                                <span class="badge">R$ <c:out value="${produto.getPreco()}"/></span>
                                            </button>
                                        </div>
                                    </div><br>
                                </form>
                            </div>
                        </div><br>
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