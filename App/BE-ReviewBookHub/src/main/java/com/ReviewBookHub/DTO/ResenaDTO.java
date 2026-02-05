
package com.ReviewBookHub.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResenaDTO {
    
    private String comentario; 
    
    private String nombres;
    
    private LocalDateTime fechaReg;   
    
}
