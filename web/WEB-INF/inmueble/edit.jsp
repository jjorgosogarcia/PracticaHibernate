

<%@page import="com.izv.hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Inmueble inm = (Inmueble)request.getAttribute("inmueble");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar inmueble</title>
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
            <form action="control" method="POST">
                
       <table class="contenidoDatos">
            <thead>
                <tr>
                    <td>Localidad:</td>
                    <th>  <input class="cajas" type="text" name="campo1" value="<%=inm.getLocalidad()%>"</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Direccion:</td>
                    <td><input class="cajas" type="text" name="campo2" value="<%=inm.getDireccion()%>"/></td>
                </tr>
                <tr>
                    <td>Tipo:</td>
                    <td> <input class="cajas" type="text" name="campo3" value="<%=inm.getTipo()%>"/></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td> <input class="cajas" type="number" name="campo4" value="<%=inm.getPrecio()%>"/></td>
                </tr>                              
            </tbody>   
      </table>           
                <input type="hidden" name="target" value="inmueble" />
                <input type="hidden" name="op" value="edit" />
                <input type="hidden" name="action" value="op" />
                <input type="hidden" name="id" value="<%=inm.getId()%>" />
                <input type="submit" class="boton" value="modificar" />
            </form>
         </div>
       <a href="control?target=inmueble&op=select&action=view"><img class="icono" src="resources/imagenes/home.png" width="30" height="30"/></a>        
   </body>
</html>
