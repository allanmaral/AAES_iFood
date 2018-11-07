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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adicionar Pedido</title>
    </head>
    <body>
        <h1>Adicionar Pedidos</h1>
        <form action="/FrontController?action=AdicionarPedido" method="post">
            <label>Entre com o título:</label>
            <input type="text" name="txtTitulo"/><br/>
            <label>Entre com a promoção:</label>
            <input type="text" name="txtPromocao"/><br/>
            <input type="submit"/>
        </form>    
    </body>
</html>
