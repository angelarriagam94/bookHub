package com.ReviewBookHub.Service;

import com.ReviewBookHub.DTO.ResenaDTO;
import com.ReviewBookHub.Entity.Libro;
import com.ReviewBookHub.Entity.Resena;
import com.ReviewBookHub.Repository.LibroRepository;
import com.ReviewBookHub.Repository.ResenaRepository;
import com.ReviewBookHub.Repository.UsuarioRepository;
import com.ReviewBookHub.Shared.Respuesta;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResenaService {

    @Autowired
    ResenaRepository resenaRepository;

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta ingresarResena(Resena resenaNueva) {
        try {
            //Limpiar espacios en blanco al inicio y al final de los atributos de la reseña.
            limpiarResena(resenaNueva);

            //Validar que no haya campos incompletos
            if (datosResenaIncompletos(resenaNueva)) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Datos de la reseña incompleta");
            }

            // Validar que exista el usuario
            if (!usuarioRepository.existsById(resenaNueva.getUsuario().getId())) {
                return new Respuesta(HttpStatus.NOT_FOUND, "El usuario no existe");
            }

            // Validar que exista el libro
            if (!libroRepository.existsById(resenaNueva.getLibro().getId())) {
                return new Respuesta(HttpStatus.NOT_FOUND, "El libro no existe");
            }

            // Guardar la reseña
            resenaRepository.save(resenaNueva);

            return new Respuesta(HttpStatus.OK, "Reseña registrada exitosamente");

        } catch (Exception e) {
            // Manejar excepciones específicas, si es necesario
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registrar libro: " + e.getMessage());
        }
    }

    public Respuesta obtenerResenasDeLibros(Long id) {
        try {

            Optional<Libro> libro = libroRepository.findById(id);

            if (!libro.isPresent()) {
                return new Respuesta(HttpStatus.NOT_FOUND, "Libro no existe");
            }

            List<Resena> resenas = resenaRepository.findByLibroId(id);

            if (resenas.isEmpty()) {
                return new Respuesta(HttpStatus.NOT_FOUND, "Libro sin reseñas");
            }

            List<ResenaDTO> resenaDTO = resenas.stream()
                    .map(resena -> {
                        ResenaDTO dto = new ResenaDTO();
                        dto.setComentario(resena.getComentario());
                        dto.setNombres(resena.getUsuario().getNombres());
                        dto.setFechaReg(resena.getFechaReg());
                        return dto;
                    })
                    .collect(Collectors.toList());

            return new Respuesta(HttpStatus.OK, resenaDTO);

        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener reseñas: " + e.getMessage());
        }
    }

    private Resena limpiarResena(Resena resena) {
        //Limpiarmos los atributos
        resena.setComentario(resena.getComentario().trim());
        return resena;
    }

    private boolean datosResenaIncompletos(Resena resena) {
        return resena.getComentario().isEmpty();
    }

}
