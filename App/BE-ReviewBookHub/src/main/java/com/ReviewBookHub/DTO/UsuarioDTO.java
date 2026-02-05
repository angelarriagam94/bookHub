
package com.ReviewBookHub.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UsuarioDTO {
    
    private Long id;
    private String nombres;
    private String correo;
    private String telefono;
    private String usuario;
    private LocalDateTime fechaReg;

}
