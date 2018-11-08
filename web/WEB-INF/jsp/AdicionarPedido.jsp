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
        <br/><h2>Cadastro de Pedidos</h2><br>
    </div>

    <form action="FrontController?action=AdicionarPedido" method="post">
        <div class="form-group">            
            <label >Título:</label>
            <input class="form-control" type="text" name="txtTitulo" placeholder="Digite o Título"/><br/>
        </div>
        <div class="form-group">            
            <label>Promoção:</label>
            <input class="form-control" type="text" name="txtPromocao" placeholder="Digite a Promoção"/><br/>
        </div>           

        <div class="text-center">          
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="reset" class="btn btn-secondary">Limpar</button>
        </div>

    </form><br/><br/>    

    <div class="alert alert-info text-center">
        <strong>Informação!</strong> Utilize o menu para navegação
    </div><br>

</div>

<%@include file="../jspf/rodape.jspf" %>