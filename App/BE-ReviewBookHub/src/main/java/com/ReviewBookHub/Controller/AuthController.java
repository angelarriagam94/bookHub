
package com.ReviewBookHub.Controller;
//importaciones de librerias 
import com.ReviewBookHub.Entity.Auth;
import com.ReviewBookHub.Service.AuthService;
import com.ReviewBookHub.Shared.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")  //conexion al swagger servidor local 
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Respuesta> login(@RequestBody Auth credenciales) {
        Respuesta respuesta = authService.login(credenciales);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }  //accedemos a la base de datos y validamos que el usuario y clave esten correctos 
    
}
