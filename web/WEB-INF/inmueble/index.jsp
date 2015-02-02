
<%@page import="java.util.List"%>
<%@page import="com.izv.hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inmobiliaria</title>
        <link rel="stylesheet" type="text/css" href="resources/estilos/estilo.css"/>
    </head>
    <body>
       <div id="cabecera">   
           <table>
               <th>
            <td><img class="logo" src="resources/imagenes/logo.png" /></td>
               </th>
            <th>
                <td><h2>Inmobiliaria A-Pique</h2></td>
            </th>          
        </table> 
 
        </div>
        <div id="cuerpo">
        <h3 class="insertar"><!--Para ver el insert-->
            <a href="control?target=inmueble&op=insert&action=view"><img  src="resources/imagenes/insert.png" width="50" height="50"/>Insertar registro</a>
        </h3>
        <table class="contenido">
            <thead>
                <tr>
                    <th class="as">Localidad</th>
                    <th class="as">Direccion</th>
                    <th class="as">Tipo</th>
                    <th class="as">Precio</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Inmueble> lista = (List<Inmueble>)request.getAttribute("datos");
                    for(Inmueble inm: lista){
                %> 
                <tr class="centrar">              
                    <td><%= inm.getLocalidad()%></td>
                    <td><%= inm.getDireccion()%></td>
                    <td><%= inm.getTipo()%></td>
                    <td><%=inm.getPrecio()%>€</td>
                    <td><a href="controlfoto?target=foto&op=select&action=view&id=<%= inm.getId()%>"><img class="icono" src="resources/imagenes/photo.png" /></a></td>
                    <td><a href="control?target=inmueble&op=delete&action=op&id=<%= inm.getId()%>"><img class="icono" src="resources/imagenes/delete.png" /></a></td>
                    <td><a href="control?target=inmueble&op=edit&action=view&id=<%= inm.getId()%>"><img class="icono" src="resources/imagenes/edit.png" /></a></td>
                </tr>
                <%
                    }
                %>              
            </tbody>
        </table>
        </div>
    </body>
</html>
