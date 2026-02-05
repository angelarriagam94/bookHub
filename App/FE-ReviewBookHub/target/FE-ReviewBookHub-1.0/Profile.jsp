<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ReviewBookHub.Model.Usuario" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="icon" type="image/x-icon" href="Assets/Icon/favicon.ico">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
        <link rel="stylesheet" href="Css/Style.css"/>
        <link rel="stylesheet" href="Css/Profile.css"/>
    </head>
    <body>
        <%
            Usuario user = new Usuario();             
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(session.getAttribute("UsuarioLogeado") != null) {  
                user = (Usuario)request.getSession().getAttribute("UsuarioLogeado");                              
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>       
        <div class="container-fluid">
            <div>
                <div class="card-client">
                    <div class="user-picture">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <p class="name-client"> <%= user.getNombres() %>
                        <span>Nombres
                        </span>
                    </p>
                    <div class="social-media">
                        <div>
                            <i class="fa-solid fa-envelope"></i>
                            <span class="tooltip-social"><%= user.getCorreo() %></span>
                        </div>
                        <div>
                            <i class="fa-solid fa-phone"></i>
                            <span class="tooltip-social"><%= user.getTelefono() %></span>
                        </div>
                        <div>
                            <i class="fa-solid fa-user"></i>
                            <span class="tooltip-social"><%= user.getUsuario() %></span>
                        </div>
                        <div>
                            <i class="fa-solid fa-calendar-days"></i>
                            <% String formattedDate = sdf.format(user.getFechaReg()); %>
                            <span class="tooltip-social"><%= formattedDate %></span>
                        </div>
                    </div>
                </div>
                <div class="container-btn">
                    <a href="javascript:history.back()" class="btn">
                        <i class="fa-solid fa-arrow-left"></i>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
