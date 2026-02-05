package com.ReviewBookHub.Client;

import com.ReviewBookHub.Model.LibroGet;
import com.ReviewBookHub.Model.LibroPost;
import com.ReviewBookHub.Shared.Respuesta;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LibroClient {

    private final String baseUrl = "http://localhost:8080/api/v1/libros";

    private Respuesta realizarSolicitudHttp(String endpoint) throws IOException {
        HttpURLConnection conexion = null;
        try {
            URL url = new URL(baseUrl + endpoint);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Content-Type", "application/json");

            try (InputStream inputStream = conexion.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                StringBuilder responseStringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseStringBuilder.append(line);
                }

                ObjectMapper objectMapperResponse = new ObjectMapper();
                return objectMapperResponse.readValue(responseStringBuilder.toString(), Respuesta.class);
            }
        } finally {
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }
    
    public Respuesta post(LibroPost libro) {
        HttpURLConnection conexion = null;
        try {
            // Establecer la conexión
            URL url = new URL(baseUrl);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Convertir las credenciales a formato JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonCredenciales = objectMapper.writeValueAsString(libro);

            // Escribir las credenciales en el cuerpo de la solicitud
            conexion.getOutputStream().write(jsonCredenciales.getBytes("UTF-8"));

            // Leer la respuesta del servicio web
            InputStream inputStream = conexion.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStringBuilder.append(line);
            }
            reader.close();
            String jsonResponse = responseStringBuilder.toString();

            // Mapear la respuesta JSON a la clase Respuesta
            ObjectMapper objectMapperResponse = new ObjectMapper();
            Respuesta respuesta = objectMapperResponse.readValue(jsonResponse, Respuesta.class);

            System.out.println(respuesta);
            // Devolver la respuesta mapeada
            return respuesta;

        } catch (IOException ex) {
            return new Respuesta("500", "Error al procesar la respuesta del servicio");
        } finally {
            // Cerrar recursos en un bloque 'finally'
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }

    public LibroGet getUltimo() throws IOException {
        Respuesta respuesta = realizarSolicitudHttp("/ultimo");
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = respuesta.getData();
        return objectMapper.convertValue(data, LibroGet.class);
    }

    public LibroGet getId(Long id) throws IOException {
        Respuesta respuesta = realizarSolicitudHttp("/" + id);
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = respuesta.getData();
        return objectMapper.convertValue(data, LibroGet.class);
    }

    public ArrayList<LibroGet> get() throws IOException {
        Respuesta respuesta = realizarSolicitudHttp("");
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = respuesta.getData();
        return objectMapper.convertValue(data, new TypeReference<ArrayList<LibroGet>>() {
        });
    }

    public ArrayList<LibroGet> getLibrosUsuario(Long id) throws IOException {
        Respuesta respuesta = realizarSolicitudHttp("/usuario/" + id);

        if ("NOT_FOUND".equals(respuesta.getStatus())) {
            return new ArrayList<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Object data = respuesta.getData();
        return objectMapper.convertValue(data, new TypeReference<ArrayList<LibroGet>>() {
        });
    }

}
