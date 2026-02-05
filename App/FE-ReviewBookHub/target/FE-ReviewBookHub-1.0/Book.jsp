<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ReviewBookHub.Shared.Respuesta" %>
<%@ page import="com.ReviewBookHub.Model.LibroGet" %>
<%@ page import="com.ReviewBookHub.Model.Usuario" %>
<%@ page import="com.ReviewBookHub.Model.Resena" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libro</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/Book.css"/>
        <script src="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js"></script>
    </head>
    <body>

        <%
            LibroGet libro = null;
            Respuesta respuesta = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(session.getAttribute("librito") != null) {                 
                libro = (LibroGet)request.getSession().getAttribute("librito");               
                if(session.getAttribute("RespuestaReseña") != null){
                    respuesta = (Respuesta)request.getSession().getAttribute("RespuestaReseña");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>

        <div class="caja">
            <section class="acerca_de" id="acerca_de">
                <div class="columna">
                    <div class="image2">
                        <img src="Assets/Portadas/<%= libro.getNombreImagen() %>" alt="">
                        <p>
                            <a href="javascript:history.back()" class="btn">
                                <i class="fa-solid fa-arrow-left"></i>
                            </a>
                        </p>
                    </div>
                    <div class="content">
                        <h3><%= libro.getTitulo() %><span> Edición <%= libro.getEdicion() %></span></h3>
                        <p>Idioma: <%= libro.getIdioma() %></p>
                        <p>Categoria: <%= libro.getCategoria() %></p>
                        <p>Editorial: <%= libro.getEditorial() %></p>
                        <p>Autor: <%= libro.getAutor() %></p>
                        <p>Precio: $<%= libro.getPrecio() %></p>
                        <h2>Resumen</h2>
                        <p class="resumen"><%= libro.getSintesis() %></p>
                    </div>
                    <div class="container-reseña">
                        <div class="card">
                            <div class="chat-header">Reseñas</div>
                            <div class="chat-window">
                                <ul class="message-list">

                                    <% List<Resena> resenas = (List<Resena>)request.getSession().getAttribute("resenas"); 
                                    if(resenas != null){
                                        for(int i=0;i<resenas.size();i++){ %>                                    
                                            <div class="card-message">
                                                <div class="img">
                                                    <i class="fa-solid fa-circle-user"></i>
                                                </div>
                                                <div class="textBox">
                                                    <div class="textContent">
                                                        <p class="h1"><%= resenas.get(i).getNombres() %></p>
                                                        <% String formattedDate = sdf.format(resenas.get(i).getFechaReg()); %>
                                                        <span class="span"><%= formattedDate %></span>
                                                    </div>
                                                    <p class="p"><%= resenas.get(i).getComentario() %></p>
                                                </div>
                                            </div>   
                                            <% } %>  
                                        <% } %>   

                                </ul>
                            </div>
                            <%
                                Usuario user = new Usuario();
                                if(session.getAttribute("UsuarioLogeado") != null) {            
                                    user = (Usuario)request.getSession().getAttribute("UsuarioLogeado");  
                                } else {
                                    response.sendRedirect("Login.jsp");
                                }
                            %>                                        
                                        
                            <div class="chat-input">
                                <form action="ResenaController" method="post">
                                    <input type="text" class="message-input" name="comentario" placeholder="Escribe tu reseña aquí">
                                    <input type="hidden" name="usuarioId" value="<%= user.getId() %>">
                                    <input type="hidden" name="libroId" value="<%= libro.getId() %>">
                                    <button class="send-button" type="submit" name="action" value="registrarReseña">
                                        <i class="fa-solid fa-paper-plane"></i>
                                    </button>
                                </form>                                
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
        <script>
        <% if (respuesta != null) { %>
            function showNotyf(status, message) {
                const notyf = new Notyf();
                switch (status) {
                    case "OK":
                        notyf.success(message);
                        break;
                    case "NOT_FOUND":
                    case "BAD_REQUEST":
                    case "INTERNAL_SERVER_ERROR":
                        notyf.error(message);
                        break;
                }
            }
            showNotyf("<%= respuesta.getStatus() %>", "<%= respuesta.getMessage() %>");
            <% session.removeAttribute("RespuestaReseña"); %>
        <% } %>
    </script>
</html>
