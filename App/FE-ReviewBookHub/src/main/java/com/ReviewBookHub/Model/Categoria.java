
package com.ReviewBookHub.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categoria {

    private Long id;
    private String categoria;
    
    public Categoria () {        
    }

    public Categoria(Long id) {
        this.id = id;
    }    
    
    
    
}
