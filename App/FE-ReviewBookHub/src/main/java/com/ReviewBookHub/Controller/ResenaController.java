package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Client.ResenaClient;
import com.ReviewBookHub.Model.LibroPost;
import com.ReviewBookHub.Model.Resena;
import com.ReviewBookHub.Model.Usuario;
import com.ReviewBookHub.Shared.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class ResenaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Book.jsp";
        HttpSession session = request.getSession();
        ResenaClient client = new ResenaClient();
        String comentario = request.getParameter("comentario");
        String usuarioId = request.getParameter("usuarioId");
        String libroId = request.getParameter("libroId");
        Resena resena = new Resena(comentario, new Usuario(Long.valueOf(usuarioId)), new LibroPost(Long.valueOf(libroId)));
        Respuesta respuesta = client.Post(resena);
        session.setAttribute("RespuestaReseña", respuesta);
        if (respuesta.getStatus().equals("OK")) {
            URL = "Home.jsp";
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
