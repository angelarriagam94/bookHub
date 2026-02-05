package com.ReviewBookHub.Service;

import com.ReviewBookHub.DTO.CategoriaDTO;
import com.ReviewBookHub.Entity.Categoria;
import com.ReviewBookHub.Repository.CategoriaRepository;
import com.ReviewBookHub.Shared.Respuesta;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta ingresarCategoria(Categoria categoriaNueva) {
        try {

            //Limpiar espacios en blanco al inicio y al final de los atributos de la categoría.
            limpiarCategoria(categoriaNueva);

            //Validar que no haya campos incompletos
            if (categoriaNueva.getCategoria().isEmpty()) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Datos de incompletos");
            }

            // Validar que la categoria solo contengan letras
            Respuesta respuestaEditorial = validarCampoLetras(categoriaNueva.getCategoria(), "categoria");
            if (respuestaEditorial != null) {
                return respuestaEditorial;
            }

            // Validar si el nombre ya está en uso
            if (categoriaRepository.existsByCategoria(categoriaNueva.getCategoria())) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "La categoria ya está en uso");
            }

            // Guardar la nueva categoria
            categoriaRepository.save(categoriaNueva);

            return new Respuesta(HttpStatus.OK, "Categoria registrada exitosamente");

        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registrar categoria: " + e.getMessage());
        }
    }

    public Respuesta obtenerTodasLasCategorias() {
        try {
            
            //Obetener categorias
            List<Categoria> categorias = categoriaRepository.findAll();

            //Validar que la lista no este vacía
            if (categorias.isEmpty()) {
                return new Respuesta(HttpStatus.NOT_FOUND, "No existen categorias");
            }

            //Mapear la categoria con el DTO
            List<CategoriaDTO> categoriasDTO = categorias.stream()
                    .map(categoria -> modelMapper.map(categoria, CategoriaDTO.class))
                    .collect(Collectors.toList());

            return new Respuesta(HttpStatus.OK, categoriasDTO);
        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener categorias: " + e.getMessage());
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

    private Categoria limpiarCategoria(Categoria categoria) {
        //Limpiarmos los atributos
        categoria.setCategoria(categoria.getCategoria().trim());
        return categoria;
    }

}
