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
<%@include file="../jspf/cabecalho.jspf" %>

<div class="container">    

    <div class="text-center">
        <br/><h2>Estados do Pedidos</h2><br>
    </div>

    <form action="FrontController?action=TrocarEstado" method="post">

        <div class="form-group">            

            <label> Pedido</label>
            
            <select class="form-control">
                <c:forEach var="pedido" items="${listaPedidos}">
                    <option value='<c:out value="${pedido.getId()}"/>' >
                        Pedido ID: <c:out value="${pedido.getId()}"/>
                    </option>
                </c:forEach>                    
            </select>
            
            <label >Estado</label>
            <select class="form-control">
                <option value="volvo">Cancelado</option>
                <option value="saab">Em Produção</option>
                <option value="mercedes">Encaminhado</option>
                <option value="audi">Entregue</option>
            </select>           
 
       </div>           

        <div class="text-center">          
            <button type="submit" class="btn btn-primary">Trocar Estado</button>
        </div>

    </form><br/><br/>    



</div>

<%@include file="../jspf/rodape.jspf" %>