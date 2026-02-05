
package com.ReviewBookHub.Model;

import lombok.Data;

@Data
public class LibroGet {
    
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
