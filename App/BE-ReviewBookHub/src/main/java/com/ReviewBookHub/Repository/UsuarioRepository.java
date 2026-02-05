package com.ReviewBookHub.Repository;

import com.ReviewBookHub.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCorreo(String correo);
    boolean existsByUsuario(String usuario);
    Optional<Usuario> findByUsuario(String usuario);
    
}
