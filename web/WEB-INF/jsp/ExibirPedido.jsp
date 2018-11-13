
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
        <h2>Pedido</h2><br>
    </div>
    
    <div class="container">
        
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <c:choose>
                    <c:when test="${not empty pedido}">
                        <div class="border-top border-bottom">
                            <br>
                            <h4>Pedido #<c:out value="${pedido.getId()}"/></h4>
                            <c:choose>
                                <c:when test="${idPedido != -1}">
                                    <label>Status do pedido: </label>
                                    <label class="text-danger"><c:out value="${pedido.getEstado().getNome()}"/></label><br>
                                    <c:choose>
                                        <c:when test="${not empty historico}">
                                            <c:forEach var="item" items="${historico}">
                                                <label class="text-success">
                                                    <c:out value="${item.getEstado().getNome()}"/> 
                                                    (<c:out value="${item.getTimestamp().getHours()}"/> : 
                                                     <c:out value="${item.getTimestamp().getMinutes()}"/> - 
                                                     <c:out value="${item.getTimestamp().getDay()}"/>/<c:out value="${item.getTimestamp().getMonth()}"/>/<c:out value="${item.getTimestamp().getYear()+1900}"/>)
                                                </label><br>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                    <br>
                                </c:when>
                            </c:choose>
         
                            <br>
                            <h5>Conteudo do Pedido</h5>
                            <c:forEach var="prd" items="${pedido.getLista()}">
                                <div class="form-group row">
                                    <a class="unlink col-lg-1" href=""> 
                                        <i class="glyphicon glyphicon-check"></i>
                                    </a>
                                    <label class="control-label col-lg-7"><c:out value="${prd.getNome()}"/></label>
                                    <label class="text-danger control-label col-lg-2">R$ <fmt:formatNumber pattern="#,##0.00" value="${prd.getPrecoTotal()}"/></label>
                                    <div class="col-lg-2">
                                        <input type="number" class="form-control" name="quantidade" value='<c:out value="${prd.getQuantidade()}"/>'  disabled/>
                                    </div>
                                </div>
                            </c:forEach>
                            
                            <c:choose>
                                <c:when test="${idPedido == -1}">
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">
                                            Finalizar Compra    
                                        </button>
                                    </div><br>
                                </c:when>
                            </c:choose>
                 
                        </div><br>
                        <div class="text-right">
                            <label>Preço total: </label>
                            <label class="text-success">R$ <fmt:formatNumber pattern="#,##0.00" value="${pedido.getTotal()}"/></label><br>
                            <c:choose>
                                <c:when test="${not empty pedido.getPromocao()}">
                                    <label>Código Promocional: </label>
                                    <label><c:out value="${pedido.getPromocao().obterCodigo()}" /></label>
                                    <label class="text-success">( -R$ <fmt:formatNumber pattern="#,##0.00" value="${pedido.getValorDesconto()}"/>)</label><br>
                                </c:when>
                            </c:choose>
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