
package com.ReviewBookHub.Controller;

import com.ReviewBookHub.Entity.Categoria;
import com.ReviewBookHub.Service.CategoriaService;
import com.ReviewBookHub.Shared.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
       
    @Autowired // se utiliza en Spring para inyectar automáticamente dependencias en los componentes de la aplicación. 
    private CategoriaService categoriaService;

    @PostMapping // solicitudes HTTP POST a un método específico en el controlador.
    public ResponseEntity<Respuesta> ingresarCategoria(@RequestBody Categoria categoriaNueva) {
        Respuesta respuesta = categoriaService.ingresarCategoria(categoriaNueva);
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }

    @GetMapping   //mapea solicitudes HTTP GET a un método en el controlador.
    public ResponseEntity<Respuesta> obtenerTodasLasCategorias() {
        Respuesta respuesta = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }
    
}
