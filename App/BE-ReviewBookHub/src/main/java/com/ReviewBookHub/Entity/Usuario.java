package com.ReviewBookHub.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombres;

    @Column(nullable = false, unique = true, length = 50)
    private String correo;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean estado;

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime fechaReg;

    @OneToMany(mappedBy = "usuario")
    private Set<Libro> libros = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Resena> resenas = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        estado = true;
        fechaReg = LocalDateTime.now();
    }
}
