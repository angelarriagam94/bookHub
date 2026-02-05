<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.ReviewBookHub.Model.Categoria" %>
<%@ page import="com.ReviewBookHub.Model.Usuario" %>
<%@ page import="com.ReviewBookHub.Client.CategoriaClient" %>
<%@ page import="com.ReviewBookHub.Shared.Respuesta" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/FormBook.css"/>
        <script src="Js/transition-image.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js"></script>
    </head>
    <body>
        <%
            Usuario user = new Usuario();
            Respuesta respuesta = null;
            if(session.getAttribute("UsuarioLogeado") != null) {            
                respuesta = (Respuesta)request.getSession().getAttribute("RespuestaBE");    
                user = (Usuario)request.getSession().getAttribute("UsuarioLogeado");  
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>   
        <div class="caja">
            <div class="image2">
                <img id="img-foto" >
            </div>
            <section>
                <div class="container-form">
                    <form action="BookController" method="POST">
                        <div class="card">
                            <h2 class="form_title">Formulario de Registro</h2>
                        </div>
                        <div class="container-inputs">
                            <div class="form_group">
                                <input type="text" id="titulo" autocomplete="off" name="titulo" class="form_input"
                                       placeholder="" required>
                                <label for="titulo" class="form_label">Titulo</label>
                                <i class="fa-solid fa-book"></i>
                            </div>
                            <div class="form_group">
                                <input type="text" id="autor" autocomplete="off" name="autor" class="form_input"
                                       placeholder="" required>
                                <label for="autor" class="form_label">Autor</label>
                                <i class="fa-brands fa-creative-commons-by"></i>
                            </div>
                            <div class="form_group">
                                <input type="text" id="editorial" autocomplete="off" name="editorial" class="form_input"
                                       placeholder="" required>
                                <label for="editorial" class="form_label">Editorial</label>
                                <i class="fa-solid fa-book-open-reader"></i>
                            </div>
                            <div class="form_group">
                                <input type="number" id="precio" autocomplete="off" name="precio" class="form_input"
                                       placeholder=" " required>
                                <label for="precio" class="form_label">Precio</label>
                                <i class="fa-solid fa-dollar-sign"></i>
                            </div>
                            <div class="form_group">
                                <input type="text" id="edicion" autocomplete="off" name="edicion" class="form_input"
                                       placeholder=" " required>
                                <label for="edicion" class="form_label">Edición</label>
                                <i class="fa-sharp fa-solid fa-bookmark"></i>
                            </div>
                            <div class="form_group">
                                <input type="text" id="idioma" autocomplete="off" name="idioma" class="form_input"
                                       placeholder=" " required>
                                <label for="idioma" class="form_label">Idioma</label>
                                <i class="fa-solid fa-language"></i>
                            </div>
                            <% 
                                CategoriaClient client = new CategoriaClient();
                                List<Categoria> listCategoria = (List<Categoria>) client.get().getData();
                            %> 
                            <div class="form_group">
                                <select name="categoria" class="form_input">
                                    <option value="" hidden selected>Elija una categoría</option>
                                    <% 
                                        if(listCategoria != null){
                                            for(int i=0;i<listCategoria.size();i++){ %>
                                                <option value="<%=listCategoria.get(i).getId()%>"><%=listCategoria.get(i).getCategoria()%></option>

                                        <%}
                                    }%>
                                </select>
                                <i class="fa-solid fa-list"></i>
                            </div>
                            <div class="form_group">
                                <input type="file" id="foto" class="form_input" name="nombreImagen"
                                       onchange="vista_preliminar(event)">
                                <i class="fa-solid fa-image"></i>
                            </div>
                            <div class="form_group">
                                <textarea name="sintesis" id="sintesis" class="form_input" placeholder=" " required></textarea>
                                <label for="sintesis" class="form_label">Sintesis</label>
                                <i class="fa-sharp fa-solid fa-font"></i>
                            </div>
                            <button type="submit" name="action" value="registrar">
                                <i class="fa-solid fa-right-to-bracket"></i>
                                <span>Registrar</span>
                            </button>
                            <input type="hidden" name="usuarioId" value="<%= user.getId() %>">
                        </div>
                    </form>
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
        <% } %>
    </script>    
    
</html>
