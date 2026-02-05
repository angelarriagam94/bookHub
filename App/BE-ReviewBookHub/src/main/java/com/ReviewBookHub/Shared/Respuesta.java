package com.ReviewBookHub.Shared;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Respuesta<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public Respuesta(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }    

    public Respuesta(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Respuesta(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }
    
}
