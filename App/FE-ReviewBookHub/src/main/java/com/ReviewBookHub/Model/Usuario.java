package com.ReviewBookHub.Model;

import java.util.Date;
import lombok.Data;

@Data
public class Usuario {

    private Long id;
    private String nombres;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;
    private Date fechaReg;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(String nombres, String correo, String telefono, String usuario, String contrasena) {
        this.nombres = nombres;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

}
