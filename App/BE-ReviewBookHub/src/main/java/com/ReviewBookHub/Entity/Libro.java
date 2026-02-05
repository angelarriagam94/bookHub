package com.ReviewBookHub.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;
    
    @Column(length = 255, nullable = false)
    private String editorial;

    @Column(length = 255, unique = true, nullable = false)
    private String titulo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;

    @Column(length = 255, nullable = false)
    private String autor;

    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private double precio;

    @Column(length = 50, nullable = false)
    private String idioma;

    @Column(length = 100, nullable = false)
    private String nombreImagen;

    @Column(length = 500, nullable = false)
    private String sintesis;
    
    @Column(length = 255, nullable = false)
    private String edicion;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean estado;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime fechaReg;
    
    @OneToMany(mappedBy = "libro")
    private Set<Resena> resenas = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        estado = true;
        fechaReg = LocalDateTime.now();
    }

}
