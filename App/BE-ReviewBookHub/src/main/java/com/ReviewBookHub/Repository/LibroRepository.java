
package com.ReviewBookHub.Repository;

import com.ReviewBookHub.Entity.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    
    boolean existsByTitulo(String titulo);
    List<Libro> findByUsuarioId(Long usuarioId);
    Optional<Libro> findFirstByOrderByFechaRegDesc();
    
}
