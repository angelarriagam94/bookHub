
package com.ReviewBookHub.Model;

import lombok.Data;

@Data
public class LibroPost {
    
    private Long id;
    private String editorial;
    private String titulo;
    private Categoria categoria;
    private String autor;    
    private double precio;   
    private String idioma;
    private String nombreImagen;    
    private String sintesis;
    private String edicion;
    private Usuario usuario;

    public LibroPost() {
    }

    public LibroPost(Long id) {
        this.id = id;
    }

    public LibroPost(String editorial, String titulo, Categoria categoria, String autor, double precio, String idioma, String nombreImagen, String sintesis, String edicion, Usuario usuario) {
        this.editorial = editorial;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
        this.precio = precio;
        this.idioma = idioma;
        this.nombreImagen = nombreImagen;
        this.sintesis = sintesis;
        this.edicion = edicion;
        this.usuario = usuario;
    }    
    
}
