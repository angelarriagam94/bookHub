
package com.ReviewBookHub.Service;

import com.ReviewBookHub.DTO.UsuarioDTO;
import com.ReviewBookHub.Entity.Auth;
import com.ReviewBookHub.Entity.Usuario;
import com.ReviewBookHub.Repository.UsuarioRepository;
import com.ReviewBookHub.Shared.Respuesta;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public Respuesta<UsuarioDTO> login(Auth credenciales) {
        try {
            
            //Validar que no haya campos incompletos
            if(credenciales.getUsuario().isEmpty() || credenciales.getContrasena().isEmpty()){
                return new Respuesta<>(HttpStatus.BAD_REQUEST, "Campos incompletos");
            }
            
            // Buscar el usuario 
            Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(credenciales.getUsuario());

            // Verificar si el usuario existe
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                // Codificar la contraseña proporcionada y comparar con la almacenada
                if (passwordEncoder.matches(credenciales.getContrasena(), usuario.getContrasena())) {
                    // Mapear la entidad Usuario a UsuarioDTO usando ModelMapper
                    UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

                    // Devolver la información del usuario
                    return new Respuesta<>(HttpStatus.OK, "Bienvenido "+usuarioDTO.getNombres(), usuarioDTO);
                } else {
                    return new Respuesta<>(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
                }

            } else {
                return new Respuesta<>(HttpStatus.NOT_FOUND, "El usuario no existe");
            }

        } catch (Exception e) {
            return new Respuesta<>(HttpStatus.INTERNAL_SERVER_ERROR, "Error al iniciar sesión");
        }
    }
    
}
