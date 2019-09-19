<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../jspf/cabecalho_restaurante.jspf" %>

<div class="container">    

    <div class="text-center">
        <br/><h2>Trocar Estado dos Pedidos</h2><br>
    </div>

    <form action="FrontController?action=TrocarEstado" method="post">

        <div class="form-group">            

            <label> Pedido (ID)</label>
            <select name="pedido" class="form-control">                
                <!-- 
                    TODO:
                    Preencher select com pedidos
                -->
                <option value="" disabled selected>---------</option>                
                <c:forEach var="pedido" items="${listaPedidos}">
                    <option value='<c:out value="${pedido.getId()}"/>' >
                        Pedido ID: <c:out value="${pedido.getId()}"/>
                        (<c:out value="${pedido.getEstado().getNome()}"/>)
                    </option>
                </c:forEach>                    
            </select>

            <label >Estado</label>
            <select name="estado" class="form-control">
                <option value="" disabled selected>---------</option>
                <option value="aguardarConfirmacao">Aguardando Confirmação</option>
                <option value="colocarEmProducao">Em Produção</option>
                <option value="encaminhar">Encaminhado</option>
                <option value="entregar">Entregue</option>
                <option value="cancelar">Cancelado</option>
            </select>           

        </div>           

        <div class="text-center">          
            <button type="submit" class="btn btn-primary">Trocar Estado</button>
        </div>

    </form><br/><br/>    



</div>


<%@include file="../jspf/rodape_restaurante.jspf" %>