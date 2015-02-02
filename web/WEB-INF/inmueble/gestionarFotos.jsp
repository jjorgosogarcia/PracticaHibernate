
<%@page import="com.izv.hibernate.Fotos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de fotos</title>
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
            
              <h2>Subir fotos de inmuebles</h2>
            <%
                String id = (String) request.getAttribute("id");
            %>
            <form class="subirfotos" action="controlfoto" method="post" enctype="multipart/form-data">
              <input type="file" name="archivo" required="true" />
            <input type="hidden" name="target" value="foto" />
            <input type="hidden" name="op" value="insert" />
            <input type="hidden" name="action" value="op" />
            <input type="hidden" name="id" value="<%= id%>" />
            <input type="submit" value="Subir" />
          </form>
            <%
                List<Fotos> fotos = (List<Fotos>) request.getAttribute("fotos");
                for (int x = 0; x < fotos.size(); x++) {
                    Fotos fo=fotos.get(x);
            %> 
            <img class="foto" src="<%= "fotos/"+fo.getNombre() %>" />          
            <a href="controlfoto?target=foto&op=delete&action=op&id=<%= fo.getId() %>&nombre=<%= fo.getNombre() %>&idinmueble=<%= request.getAttribute("id")%>"><img class="iconofoto" src="resources/imagenes/deletephoto.png" /></a>
  </br>
            </br>
            <%
                }
            %>   
        </div>         
    <a href="control?target=inmueble&op=select&action=view"><img class="icono" src="resources/imagenes/home.png" width="30" height="30"/></a>  
    </body>
</html>
