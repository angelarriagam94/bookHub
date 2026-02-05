package com.ReviewBookHub.Controller;
//importacion de librerias 
import com.ReviewBookHub.Entity.Usuario;
import com.ReviewBookHub.Service.UsuarioService;
import com.ReviewBookHub.Shared.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired  // se utiliza en Spring para inyectar automáticamente dependencias en los componentes de la aplicación. 
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Respuesta> post(@RequestBody Usuario usuarioNuevo) {
        Respuesta respuesta = usuarioService.ingresarUsuario(usuarioNuevo);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> get(@PathVariable Long id) {
        Respuesta respuesta = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }

}
