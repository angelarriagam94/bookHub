
package com.ReviewBookHub.DTO;

import lombok.Data;

@Data
public class LibroDTO {
    
    private Long id;

    private String editorial;

    private String titulo;
    
    private String categoria;

    private String autor;
    
    private double precio;
    
    private String idioma;

    private String nombreImagen;
    
    private String sintesis;
    
    private String edicion;
    
}
