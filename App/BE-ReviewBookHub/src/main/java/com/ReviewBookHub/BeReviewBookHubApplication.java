package com.ReviewBookHub;
//importamos librerias 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // agrega varias configuraciones necesarias para una aplicación Spring Boot.
public class BeReviewBookHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeReviewBookHubApplication.class, args);// Este método es lo que realmente inicia la aplicación Spring Boot. 
    }

}

//http://localhost:8080/swagger-ui/index.html   //servidor local en apache con su respectivo puerto de conexion.