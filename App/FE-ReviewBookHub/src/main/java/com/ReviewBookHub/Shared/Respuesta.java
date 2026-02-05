
package com.ReviewBookHub.Shared;

import lombok.Data;

@Data
public class Respuesta<T> {

    private String status;
    private String message;
    private T data;
    
    public Respuesta() { }

    public Respuesta(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }    

    public Respuesta(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Respuesta(String status, T data) {
        this.status = status;
        this.data = data;
    }

}
