package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Client.AuthClient;
import com.ReviewBookHub.Model.Auth;
import com.ReviewBookHub.Model.Usuario;
import com.ReviewBookHub.Shared.Respuesta;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Login.jsp";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        AuthClient client = new AuthClient();

        switch (action) {
            case "login":                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Auth credenciales = new Auth(username, password);
                Respuesta respuesta = client.login(credenciales);
                if (respuesta.getStatus().equals("OK")) {   
                    ObjectMapper objectMapper = new ObjectMapper();
                    Usuario usuario = objectMapper.convertValue(respuesta.getData(), Usuario.class);
                    session.setAttribute("UsuarioLogeado", usuario);   
                    session.setAttribute("MessageBE", respuesta);                    
                    URL = "Home.jsp";
                } else {
                    session.setAttribute("RespuestaBE", respuesta);
                }
                break;
        }

        request.getRequestDispatcher(URL).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
