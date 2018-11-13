
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <h2>Carrinho</h2><br>
    </div>
    
    <div class="container">
        
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <c:choose>
                    <c:when test="${not empty carrinho}">
                        <div class="border-top border-bottom">
                            <br>
                            <h5>Conteudo</h5>
                            <c:forEach var="prd" items="${carrinho.getLista()}">
                                <div class="form-group row">
                                    <a class="unlink col-lg-1" href=""> 
                                        <i class="glyphicon glyphicon-check"></i>
                                    </a>
                                    <label class="control-label col-lg-7"><c:out value="${prd.getNome()}"/></label>
                                    <label class="text-danger control-label col-lg-2">R$ <fmt:formatNumber pattern="#,##0.00" value="${prd.getPrecoTotal()}"/></label>
                                    <div class="col-lg-2">
                                        <input type="number" class="form-control" name="quantidade" value='<c:out value="${prd.getQuantidade()}"/>' />
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="form-group row">
                                <form class="form-inline" action="FrontController?action=AplicarPromocao" method="post">
                                    <div class="form-group mx-sm-3 mb-2">
                                        <label for="codigoPromo" class="sr-only">Código de Promoção</label>
                                        <input type="text" class="form-control" name="codigoPromo" placeholder="Código de Promoção">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-2">Aplicar</button>
                                    
                                </form>
                            </div>

                            <div class="text-center">
                                <form class="form-horizontal" action="FrontController?action=FinalizarCompra" method="post">
                                <button type="submit" class="btn btn-primary">
                                    Finalizar Compra    
                                </button>
                                </form>
                            </div><br>
                        </div><br>
                        
                        <div class="text-right">
                            <c:choose>
                                <c:when test="${not empty carrinho.getPromocao()}">
                                    <label>Código Promocional: </label>
                                    <label><c:out value="${carrinho.getPromocao().obterCodigo()}" /></label>
                                    <label class="text-success">( -R$ <fmt:formatNumber pattern="#,##0.00" value="${carrinho.getValorDesconto()}"/>)</label><br>
                                </c:when>
                            </c:choose>
                            <label>Preço total: </label>
                            <label class="text-success">R$ <fmt:formatNumber pattern="#,##0.00" value="${carrinho.getTotal()}"/></label><br>
                        </div>
                            
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