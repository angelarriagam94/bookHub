
package com.ReviewBookHub.Repository;

import com.ReviewBookHub.Entity.Resena;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenaRepository extends JpaRepository<Resena, Long>{
 
    List<Resena> findByLibroId(Long libroId);
    
}
