package com.ReviewBookHub.Controller;
//iportamos librerias 
import com.ReviewBookHub.Client.LibroClient;
import com.ReviewBookHub.Client.ResenaClient;
import com.ReviewBookHub.Model.Categoria;
import com.ReviewBookHub.Model.LibroGet;
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
import java.util.ArrayList;

public class BookController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Home.jsp";
        LibroClient clientLibro = new LibroClient();
        ResenaClient clientResena = new ResenaClient();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "reseña":
                    String idParameter = request.getParameter("id");
                    if (idParameter != null) {
                        Long id = Long.valueOf(idParameter);
                        LibroGet librito = clientLibro.getId(id);
                        ArrayList<Resena> resenas = clientResena.get(id);
                        session.setAttribute("librito", librito);
                        session.setAttribute("resenas", resenas);
                        if (librito != null) {
                            URL = "Book.jsp";
                        }
                    }
                    break;

                case "registrar":
            
                    String usuarioId = request.getParameter("usuarioId");
                    String editorial = request.getParameter("editorial");
                    String titulo = request.getParameter("titulo");
                    String categoriaId = request.getParameter("categoria");
                    String autor = request.getParameter("autor");
                    String precio = request.getParameter("precio");
                    String idioma = request.getParameter("idioma");
                    String nombreImagen = request.getParameter("nombreImagen");
                    String sintesis = request.getParameter("sintesis");
                    String edicion = request.getParameter("edicion");
                    
                    Categoria categoria = new Categoria(Long.valueOf(categoriaId));
                    Usuario usuario = new Usuario(Long.valueOf(usuarioId));
                    
                    LibroPost librito = new LibroPost(
                            editorial, titulo, categoria, autor, Double.parseDouble(precio), 
                            idioma, nombreImagen, sintesis, edicion, usuario);
                    Respuesta respuesta = clientLibro.post(librito);
                    session.setAttribute("RespuestaBE", respuesta);
                    if (respuesta.getStatus().equals("OK")) {
                        URL = "Custom.jsp";
                    }else{
                        URL = "FormBook.jsp";
                    }
                    break;
        }
    }

    request.getRequestDispatcher (URL)

.forward(request, response);
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
