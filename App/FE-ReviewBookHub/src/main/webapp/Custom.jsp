<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.ReviewBookHub.Model.Usuario" %>
<%@ page import="com.ReviewBookHub.Model.LibroGet" %>
<%@ page import="com.ReviewBookHub.Client.LibroClient" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Custom</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/Custom.css"/>
    </head>
    <body>
        <%
            Usuario user = new Usuario();
            List<LibroGet> list = null;
            LibroClient client = new LibroClient();
            
            if(session.getAttribute("UsuarioLogeado") != null) {  
                user = (Usuario)request.getSession().getAttribute("UsuarioLogeado");                  
                list = client.getLibrosUsuario(user.getId());               
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>

        <div class="container-fluid">
            <div class="caja-regresar">
                <nav class="menu-regresar">
                    <a href="Profile.jsp">Perfil</a>
                    <a href="Home.jsp">Home</a>
                </nav>
            </div>
            <div class="caja">
                <div class="Contenedor-Padre">
                    <div class="cabecera">
                        <h1>Libros</h1>
                        <a class="btn-link" href="FormBook.jsp">
                            <i class="fa-solid fa-circle-plus"></i>
                        </a>
                    </div>
                    <div class="caja-tabla">
                        <table>
                            <thead>
                            <td>Titulo</td>
                            <td>Autor</td>
                            <td>Precio</td>
                            <td>Categoria</td>
                            <td>Reseñas</td>
                            </thead>
                            <tbody>                       
                                <%  if(list != null){ 
                                    for(int i=0;i<list.size();i++){ %> 
                                <tr class="dos">
                                    <td> <%= list.get(i).getTitulo() %> </td>
                                    <td> <%= list.get(i).getAutor() %> </td>    
                                    <td> <%= list.get(i).getPrecio() %> </td>
                                    <td> <%= list.get(i).getCategoria() %> </td>
                                    <td class="btn-td">
                                        <a class="btn-link" href="BookController?action=reseña&id=<%= list.get(i).getId() %>">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>                                
                                    </td>
                                </tr>
                                <% } %>    
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>
