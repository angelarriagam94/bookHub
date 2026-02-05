package com.ReviewBookHub.Service;

import com.ReviewBookHub.DTO.UsuarioDTO;
import com.ReviewBookHub.Entity.Usuario;
import com.ReviewBookHub.Repository.UsuarioRepository;
import com.ReviewBookHub.Shared.Respuesta;
import java.util.Optional;
import java.util.regex.Pattern;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta ingresarUsuario(Usuario usuarioNuevo) {
        try {

            //Limpiar espacios en blanco al inicio y al final de los atributos del usuario.
            limpiarUsuario(usuarioNuevo);
            
            //Validar que no haya campos incompletos
            if (datosUsuarioIncompletos(usuarioNuevo)) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "Datos del usuario incompletos");
            }
            
            // Validar que los nombres solo contengan letras
            Respuesta respuestaNombre = validarCampoLetras(usuarioNuevo.getNombres(), "nombre");
            if (respuestaNombre != null) {
                return respuestaNombre;
            }

            // Validar si el correo ya está en uso
            if (usuarioRepository.existsByCorreo(usuarioNuevo.getCorreo())) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "El correo ya está en uso");
            }

            // Validar si el usuario ya está en uso
            if (usuarioRepository.existsByUsuario(usuarioNuevo.getUsuario())) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "El nombre de usuario ya está en uso");
            }

            // Validar que el teléfono solo contenga números
            if (!usuarioNuevo.getTelefono().matches("\\d+")) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "El teléfono solo debe contener números");
            }

            // Validar si el teléfono no tiene exactamente 10 dígitos
            if (usuarioNuevo.getTelefono().length() != 10) {
                return new Respuesta(HttpStatus.BAD_REQUEST, "El teléfono debe contener exactamente 10 números");
            }

            // Codificar la contraseña antes de almacenarla
            usuarioNuevo.setContrasena(passwordEncoder.encode(usuarioNuevo.getContrasena()));

            // Guardar el nuevo usuario
            usuarioRepository.save(usuarioNuevo);

            return new Respuesta(HttpStatus.OK, "Usuario registrado exitosamente");

        } catch (Exception e) {
            // Manejar excepciones específicas, si es necesario
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registrar usuario: " + e.getMessage());
        }
    }

    public Respuesta obtenerUsuarioPorId(Long id) {
        try {
            // Buscar el usuario por ID en la base de datos
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            // Verificar si el usuario existe
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                // Mapear la entidad Usuario a UsuarioDTO usando ModelMapper
                UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

                // Devolver la información del usuario
                return new Respuesta(HttpStatus.OK, usuarioDTO);
            } else {
                return new Respuesta(HttpStatus.NOT_FOUND, "El usuario no existe");
            }

        } catch (Exception e) {
            return new Respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener la información del usuario: " + e.getMessage());
        }
    }

    private Respuesta validarCampoLetras(String valor, String nombreCampo) {
        // Utilizamos la constante UNICODE_CHARACTER_CLASS para permitir tildes
        Pattern pattern = Pattern.compile("[\\p{L}\\s]+", Pattern.UNICODE_CHARACTER_CLASS);

        if (!pattern.matcher(valor).matches()) {
            return new Respuesta(HttpStatus.BAD_REQUEST, "El " + nombreCampo + " solo debe contener letras");
        }
        return null; // Retornar null si la validación es exitosa
    }

    private Usuario limpiarUsuario(Usuario usuario) {
        //Limpiarmos los atributos
        usuario.setNombres(usuario.getNombres().trim());
        usuario.setCorreo(usuario.getCorreo().trim());
        usuario.setTelefono(usuario.getTelefono().trim());
        return usuario;
    }

    private boolean datosUsuarioIncompletos(Usuario usuario) {
        return usuario.getCorreo().isEmpty()
                || usuario.getContrasena().isEmpty()
                || usuario.getNombres().isEmpty()
                || usuario.getTelefono().isEmpty()
                || usuario.getUsuario().isEmpty();
    }

}
