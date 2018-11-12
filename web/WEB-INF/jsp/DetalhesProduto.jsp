
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

<script type="text/javascript">
    function salvaSession(id, value) {
        sessionStorage.setItem(id, Number(value));
        document.getElementById(id).value = sessionStorage.getItem(id);
        
        calculaPrecoTotal();
    }
    
    function calculaPrecoTotal() {
        var elementos = document.getElementsByClassName("quantidade");
        var precoTotal = 0.0;
        for(var i = 0; i < elementos.length; i++) {
            var id = elementos[i].id;
            var preco = Number(document.getElementById(id+'_preco').innerHTML.replace(",", "."));
            var quantidade = Number(elementos[i].value);
            precoTotal += preco * quantidade;
        }
        
        document.getElementById('preco_total').innerHTML = precoTotal;
    }
    
    window.onload = function() {
        var elementos = document.getElementsByClassName("quantidade");
        for(var i = 0; i < elementos.length; i++) {
            if(sessionStorage.getItem(elementos[i].id)) {
                elementos[i].value = sessionStorage.getItem(elementos[i].id);
            }
        }        
        calculaPrecoTotal();
    }
</script>

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
                            <div>
                                <label class="text-success">R$ </label>
                                <label id='prodId<c:out value="${produto.getId()}"/>_preco' class="text-success"><fmt:formatNumber pattern="#,##0.00" value="${produto.getPreco()}"/></label>
                            </div>
                            <br>
                        
                            <div class="col-lg-12">
                                <form class="form-horizontal" action="FrontController?action=AdicionarProduto" method="post">
                                    <input type="hidden" id="idPdt" name="idPdt" value='<c:out value="${produto.getId()}"/>' />
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
                                            <div class=" col-lg-2">
                                                <label class="text-danger control-label">R$ </label>
                                                <label id='prodId<c:out value="${prd.getId()}"/>_preco' class="text-danger control-label"><c:out value="${prd.getPreco()}"/></label>                                                
                                            </div>
                                            <div class="col-lg-2">
                                                <input id='prodId<c:out value="${prd.getId()}"/>' name='prodId<c:out value="${prd.getId()}"/>' type="number" class="form-control quantidade" value="0"  onchange="salvaSession(this.id, this.value)"/>
                                            </div>
                                        </div>
                                    </c:forEach>

                                    <div class="text-center">
                                        <div class="form-group">
                                            <label class="control-label" for="prodId<c:out value="${produto.getId()}"/>">Quantidade:</label> 
                                            <input id='prodId<c:out value="${produto.getId()}"/>' name='prodId<c:out value="${produto.getId()}"/>' type="number" class="form-control quantidade" value="1" onchange="salvaSession(this.id, this.value)"/>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary">
                                                Adicionar
                                                <span class="badge">R$</span> 
                                                <span id="preco_total" class="badge"> <c:out value="${produto.getPreco()}"/></span>
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