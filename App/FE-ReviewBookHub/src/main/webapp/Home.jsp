<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.ReviewBookHub.Shared.Respuesta" %>
<%@ page import="com.ReviewBookHub.Client.LibroClient" %>
<%@ page import="com.ReviewBookHub.Model.LibroGet" %>
<%@ page import="com.ReviewBookHub.Model.Usuario" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/Home.css"/>
    </head>
    <body>

        <%
            Respuesta respuesta = null;
            Usuario user = new Usuario();
            LibroGet ultimoLibro = null;
            LibroClient client = new LibroClient();
            
            if(session.getAttribute("MessageBE") != null && session.getAttribute("UsuarioLogeado") != null) {            
                respuesta = (Respuesta)request.getSession().getAttribute("MessageBE");
                user = (Usuario)request.getSession().getAttribute("UsuarioLogeado");                  
                ultimoLibro = client.getUltimo();               
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>

        <header>
            <div>
                <span>Bienvenido</span><br>
                <span><%= user.getNombres() %></span>
            </div>
            <nav>
                <a href="Custom.jsp">
                    <i class="fa-solid fa-gear"></i>
                </a>
                <a href="Login.jsp">
                    <i class="fa-solid fa-power-off"></i>
                </a>
            </nav>
        </header>
        <div>
            <section class="inicio" >
                <div class="content">
                    <h3>El más vendido<span> <%= ultimoLibro.getTitulo() %></span></h3>
                    <p><%= ultimoLibro.getSintesis() %></p>
                    <a href="BookController?action=reseña&id=<%= ultimoLibro.getId() %>" class="btn btn-inicio">Reseña</a>
                </div>

                <div class="image">
                    <a href="BookController?action=reseña&id=<%= ultimoLibro.getId() %>">
                        <img src="Assets/Portadas/<%= ultimoLibro.getNombreImagen() %>" alt="img">
                    </a>
                </div>                     
            </section>

            <section class="opiniones">
                <h1 class="heading">Libros</h1><br>
                <div class="caja-contenedor">
                    <%
                        List<LibroGet> lista = client.get();
                        for(int i=0;i<lista.size();i++){
                    %>
                    <div class="caja">
                        <div class="usuario">
                            <a href="BookController?action=reseña&id=<%= lista.get(i).getId() %>">
                                <img src="Assets/Portadas/<%= lista.get(i).getNombreImagen() %>" alt="img-card">
                            </a>
                            <h3><%=lista.get(i).getTitulo()%></h3>
                            <div class="autor">
                                <p><%=lista.get(i).getAutor()%></p>
                            </div>
                            <div class="comentarios">
                                <p><%=lista.get(i).getSintesis()%></p>
                            </div>
                            <div>
                                <a href="BookController?action=reseña&id=<%= lista.get(i).getId() %>" class="btn btn-comment">
                                    <i class="fa-solid fa-comment"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </section>      

            <footer>
                <div class="caja-contenedor">
                    <div class="caja1">
                        <h3>Materia</h3>
                        <p>Universidad de Guayaquil</p>
                        <p>Facultad de Ciencias Matemáticas y Física</p>
                        <p>Ingeniería de Software</p>
                        <p>Verificacion y Validacion</p>
                    </div>
                    <div class="caja1">
                        <h3>información</h3>
                        <div class="info">
                            <i class="fa-solid fa-phone-flip"></i>
                            <p>
                                +593 98 877 6633<br>
                                +593 99 235 4566<br>
                                +593 98 886 7374<br>
                                +593 99 086 2366
                            </p>
                        </div>
                        <div class="info">
                            <i class="fa-solid fa-envelope"></i>
                            <p>                                
                                angel.arriaga@ug.edu.ec<br>
                                belki.piedra@ug.edu.ec<br>
                                jhonas.lopez@ug.edu.ec<br>
                                
                                
                            </p>
                        </div>

                        <div class="info">
                            <i class="fa-solid fa-location-dot"></i>
                            <p>Guayaquil, Ecuador</p>
                        </div>
                    </div>
                </div>
                <h1 class="creditos">
                    &copy, copyright @ 2025 Proyecto Final de la materia verificacion y validacion de software
                </h1>
            </footer>

        </div>
    </body>
</html>
