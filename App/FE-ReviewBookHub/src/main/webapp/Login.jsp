<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ReviewBookHub.Shared.Respuesta" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/Login.css"/>
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
            <div class="container">
                <form action="AuthController" method="POST">
                    <h2 class="form_title">Inicia Sesión</h2>
                    <p class="form_paragraph">¿Aún no tienes cuenta?
                        <a href="FormUser.jsp">Regístrate</a>
                    </p>
                    <div class="form_container">
                        <div class="form_group">
                            <input type="text" id="username" name="username" class="form_input" autocomplete="off" placeholder=" " required>
                            <label for="username" class="form_label">Usuario</label>
                            <i class="fa-solid fa-user"></i>
                        </div>

                        <div class="form_group">
                            <input type="password" id="password" name="password" class="form_input" autocomplete="off" placeholder=" " required>
                            <label for="password" class="form_label">Contraseña</label>
                            <i class="fa-solid fa-lock"></i>
                        </div>

                        <button type="submit" name="action" value="login">
                            <i class="fa-solid fa-right-to-bracket"></i>
                            <span>Entrar</span> 
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
                    case "UNAUTHORIZED":
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
