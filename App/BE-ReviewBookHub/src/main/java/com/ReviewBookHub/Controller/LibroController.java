package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Entity.Libro;
import com.ReviewBookHub.Service.LibroService;
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
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired// se utiliza en Spring para inyectar automáticamente dependencias en los componentes de la aplicación. 
    private LibroService libroService;

    @PostMapping // solicitudes HTTP POST a un método específico en el controlador.
    public ResponseEntity<Respuesta> post(@RequestBody Libro libroNuevo) {
        Respuesta respuesta = libroService.ingresarLibro(libroNuevo);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }

    @GetMapping("/ultimo")  //mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> obtenerUltimoLibroIngresado() {
        Respuesta respuesta = libroService.obtenerUltimoLibroIngresado();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }
    
    @GetMapping()//mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> get() {
        Respuesta respuesta = libroService.obtenerTodosLosLibros();
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }
    
    @GetMapping("/{id}")//mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> getId(@PathVariable Long id) {
        Respuesta respuesta = libroService.obtenerLibroPorId(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }
    
    @GetMapping("/usuario/{id}")//mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> getUsuarioId(@PathVariable Long id) {
        Respuesta respuesta = libroService.obtenerTodosLosLibrosDeUnUsuario(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(respuesta);
    }

}
