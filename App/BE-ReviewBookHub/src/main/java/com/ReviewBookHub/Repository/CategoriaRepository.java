
package com.ReviewBookHub.Repository;

import com.ReviewBookHub.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
    boolean existsByCategoria(String categoria);
    
    boolean existsById(Long id);
}
