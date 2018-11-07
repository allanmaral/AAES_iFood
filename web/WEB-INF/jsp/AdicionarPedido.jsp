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

<style>
    .center {
        margin: auto;
        width: 50%; 
    }
</style>

<div class="container center">
    <h2>Cadastro de Pedidos</h2><br/>
    <form action="FrontController?action=AdicionarPedido" method="get">
        <div class="form-group">
        <label>Título:</label>
        <input type="text" name="txtTitulo" placeholder="Digite o título"/><br/>
        <label>Promoção:</label>
        <input type="text" name="txtPromocao" placeholder="Digite a Promoção"/><br/>
        </div>
        <input type="submit"/>
        
    </form>    
</div>

<%@include file="../jspf/rodape.jspf" %>