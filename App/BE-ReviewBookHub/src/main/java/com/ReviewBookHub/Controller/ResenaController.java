
package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Entity.Resena;
import com.ReviewBookHub.Service.ResenaService;
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
@RequestMapping("/api/v1/resenas")//maneja solicitudes GET a la raíz del recurso /api/libros,
public class ResenaController {
    
    @Autowired
    private ResenaService resenaService;
    
    @PostMapping
    public ResponseEntity<Respuesta> post(@RequestBody Resena resenaNueva) {
        Respuesta respuesta = resenaService.ingresarResena(resenaNueva);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }

    @GetMapping("/{id}")//mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> getId(@PathVariable Long id) {
        Respuesta respuesta = resenaService.obtenerResenasDeLibros(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }
    
}
