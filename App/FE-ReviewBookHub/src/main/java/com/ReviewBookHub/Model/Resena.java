package com.ReviewBookHub.Model;

import java.util.Date;
import lombok.Data;

@Data
public class Resena {

    private String comentario;
    private Usuario usuario;
    private String nombres;
    private LibroPost libro;
    private Date fechaReg;

    public Resena() {
    }

    public Resena(String comentario, String nombres, Date fechaReg) {
        this.comentario = comentario;
        this.nombres = nombres;
        this.fechaReg = fechaReg;
    }

    public Resena(String comentario, Usuario usuario, LibroPost libro) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.libro = libro;
    }    

}
