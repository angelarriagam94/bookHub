package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Client.UsuarioClient;
import com.ReviewBookHub.Model.Usuario;
import com.ReviewBookHub.Shared.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class UsuarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "FormUser.jsp";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UsuarioClient client = new UsuarioClient();

        switch (action) {
            case "registrar":
                String nombres = request.getParameter("nombres");
                String correo = request.getParameter("correo");
                String telefono = request.getParameter("telefono");
                String usuario = request.getParameter("usuario");
                String contrasena = request.getParameter("contrasena");
                Usuario user = new Usuario(nombres, correo, telefono, usuario, contrasena);
                Respuesta respuesta = client.post(user);
                
                session.setAttribute("RespuestaBE", respuesta);
                if (respuesta.getStatus().equals("OK")) {
                    URL = "Login.jsp";
                }
                break;
        }
        request.getRequestDispatcher(URL).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
