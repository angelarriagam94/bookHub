package com.ReviewBookHub.Service;

import com.ReviewBookHub.DTO.LibroDTO;
import com.ReviewBookHub.Entity.Libro;
import com.ReviewBookHub.Repository.CategoriaRepository;
import com.ReviewBookHub.Repository.LibroRepository;
import com.ReviewBookHub.Repository.UsuarioRepository;
import com.ReviewBookHub.Shared.Respuesta;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta ingresarLibro(Libro libroNuevo) {
        try {
            //Limpiar espacios en blanco al inicio y al final de los atributos del libro.
            limpiarLibro(libroNuevo);

            //Validar que no haya campos incompletos
            if (datosLibroIncompletos(libroNuevo)) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Datos del libro incompletos");
            }

            // Validar que exista el usuario
            if (!usuarioRepository.existsById(libroNuevo.getUsuario().getId())) {
                return new Respuesta(HttpStatus.NOT_FOUND, "El usuario no existe");
            }

            // Validar si el nombre ya está en uso
            if (!categoriaRepository.existsById(libroNuevo.getCategoria().getId())) {
                return new Respuesta(HttpStatus.NOT_FOUND, "La categoria no existe");
            }

            // Validar que el titulo no este en uso
            if (libroRepository.existsByTitulo(libroNuevo.getTitulo())) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Título ya registrado");
            }

            // Validar que la editorial solo contengan letras
            Respuesta respuestaEditorial = validarCampoLetras(libroNuevo.getEditorial(), "editorial");
            if (respuestaEditorial != null) {
                return respuestaEditorial;
            }

            // Validar que el titulo solo contengan letras
            Respuesta respuestaTitulo = validarCampoLetras(libroNuevo.getTitulo(), "titulo");
            if (respuestaTitulo != null) {
                return respuestaTitulo;
            }

            // Validar que el autor solo contengan letras
            Respuesta respuestaAutor = validarCampoLetras(libroNuevo.getAutor(), "autor");
            if (respuestaAutor != null) {
                return respuestaAutor;
            }

            // Validar que el nombre de la imagen no tenga espacios
            if (libroNuevo.getNombreImagen().contains(" ")) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "El nombre de la imagen no debe contener espacios");
            }

            // Validar que el idioma solo contengan letras
            Respuesta respuestaIdioma = validarCampoLetras(libroNuevo.getIdioma(), "idioma");
            if (respuestaIdioma != null) {
                return respuestaAutor;
            }

            // Validar que el precio no sea negativo
            if (libroNuevo.getPrecio() < 1) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Precio incorrecto");
            }

            // Guardar el nuevo libro
            libroRepository.save(libroNuevo);

            return new Respuesta(HttpStatus.OK, "Libro registrada exitosamente");

        } catch (Exception e) {
            // Manejar excepciones específicas, si es necesario
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registrar libro: " + e.getMessage());
        }
    }

    public Respuesta obtenerTodosLosLibros() {
        try {
            List<Libro> libros = libroRepository.findAll();

            if (libros.isEmpty()) {
                return new Respuesta(HttpStatus.NOT_FOUND, "No existen libros");
            }

            List<LibroDTO> librosDTO = libros.stream()
                    .map(libro -> modelMapper.map(libro, LibroDTO.class))
                    .collect(Collectors.toList());

            return new Respuesta(HttpStatus.OK, librosDTO);
        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener libros: " + e.getMessage());
        }
    }

    public Respuesta obtenerUltimoLibroIngresado() {
        try {
            // Obtener el último libro por fecha de creación
            Optional<Libro> ultimoLibroOpt = libroRepository.findFirstByOrderByFechaRegDesc();

            if (ultimoLibroOpt.isPresent()) {
                Libro ultimoLibro = ultimoLibroOpt.get();
                LibroDTO ultimoLibroDTO = modelMapper.map(ultimoLibro, LibroDTO.class);

                return new Respuesta(HttpStatus.OK, ultimoLibroDTO);
            } else {
                return new Respuesta(HttpStatus.NOT_FOUND, "No existen libros");
            }
        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el último libro: " + e.getMessage());
        }
    }

    public Respuesta obtenerLibroPorId(Long Id) {
        try {
            // Obtener el último libro por fecha de creación
            Optional<Libro> ultimoLibroOpt = libroRepository.findById(Id);

            if (ultimoLibroOpt.isPresent()) {
                Libro ultimoLibro = ultimoLibroOpt.get();
                LibroDTO ultimoLibroDTO = modelMapper.map(ultimoLibro, LibroDTO.class);

                return new Respuesta(HttpStatus.OK, ultimoLibroDTO);
            } else {
                return new Respuesta(HttpStatus.NOT_FOUND, "No existen libros");
            }
        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el último libro: " + e.getMessage());
        }
    }
    
    public Respuesta obtenerTodosLosLibrosDeUnUsuario(Long id) {
        try {
            List<Libro> libros = libroRepository.findByUsuarioId(id);

            if (libros.isEmpty()) {
                return new Respuesta(HttpStatus.NOT_FOUND, "Usuario no tiene libros");
            }

            List<LibroDTO> librosDTO = libros.stream()
                    .map(libro -> modelMapper.map(libro, LibroDTO.class))
                    .collect(Collectors.toList());

            return new Respuesta(HttpStatus.OK, librosDTO);
        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener libros: " + e.getMessage());
        }
    }

    private Respuesta validarCampoLetras(String valor, String nombreCampo) {
        // Utilizamos la constante UNICODE_CHARACTER_CLASS para permitir tildes
        Pattern pattern = Pattern.compile("[\\p{L}\\s]+", Pattern.UNICODE_CHARACTER_CLASS);

        if (!pattern.matcher(valor).matches()) {
            return new Respuesta(HttpStatus.BAD_REQUEST, "El " + nombreCampo + " solo debe contener letras");
        }
        return null; // Retornar null si la validación es exitosa
    }

    private Libro limpiarLibro(Libro libro) {
        //Limpiarmos los atributos
        libro.setEditorial(libro.getEditorial().trim());
        libro.setTitulo(libro.getTitulo().trim());
        libro.setAutor(libro.getAutor().trim());
        libro.setIdioma(libro.getIdioma().trim());
        libro.setNombreImagen(libro.getNombreImagen().trim());
        libro.setSintesis(libro.getSintesis().trim());
        libro.setEdicion(libro.getEdicion().trim());
        return libro;
    }

    private boolean datosLibroIncompletos(Libro libro) {
        return libro.getEditorial().isEmpty()
                || libro.getTitulo().isEmpty()
                || libro.getCategoria() == null
                || libro.getCategoria().getId() == null
                || libro.getAutor().isEmpty()
                || libro.getIdioma().isEmpty()
                || libro.getNombreImagen().isEmpty()
                || libro.getSintesis().isEmpty()
                || libro.getEdicion().isEmpty();
    }

}
