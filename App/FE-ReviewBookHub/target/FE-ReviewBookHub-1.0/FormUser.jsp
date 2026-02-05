<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ReviewBookHub.Shared.Respuesta" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/FormUser.css"/>
        <script src="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js"></script>
    </head>
    <body>
        <%  
            Respuesta respuesta = null;
            if(session.getAttribute("RespuestaBE") != null){                
                respuesta = (Respuesta)request.getSession().getAttribute("RespuestaBE");                
            }
        %>
        <div class="container-fluid">
            <div class="container-form">
                <form action="UsuarioController" method="POST">
                    <div class="card">
                        <i class="fa-solid fa-circle-user"></i>
                        <h2 class="form_title">Formulario de Registro</h2>
                    </div>
                    <div class="container-inputs">
                        <div class="form_group">
                            <input type="text" id="nombres" autocomplete="off" name="nombres" class="form_input"  placeholder="" required>
                            <label for="nombres" class="form_label">Full Name</label>
                            <i class="fa-solid fa-user"></i>
                        </div>
                        <div class="form_group">
                            <input type="text" id="correo" autocomplete="off" name="correo" class="form_input"  placeholder="" required>
                            <label for="correo" class="form_label">Correo</label>
                            <i class="fa-solid fa-envelope"></i>
                        </div>
                        <div class="form_group">
                            <input type="number" id="telefono" autocomplete="off" name="telefono" class="form_input"   placeholder=" " required>
                            <label for="telefono" class="form_label">Teléfono</label>
                            <i class="fa-solid fa-phone-flip"></i>
                        </div>
                        <div class="form_group">
                            <input type="text" id="usuario" autocomplete="off" name="usuario" class="form_input"  placeholder=" " required>
                            <label for="usuario" class="form_label">Usuario</label>
                            <i class="fa-sharp fa-solid fa-user"></i>
                        </div>
                        <div class="form_group">
                            <input type="password" id="contrasena"  autocomplete="off" name="contrasena" class="form_input"   placeholder=" " required>
                            <label for="contrasena" class="form_label">Contraseña</label>
                            <i class="fa-solid fa-lock"></i>
                        </div>
                        <button type="submit" name="action" value="registrar">
                            <i class="fa-solid fa-right-to-bracket"></i>
                            <span>Registrar</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>

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

    </body>
</html>
