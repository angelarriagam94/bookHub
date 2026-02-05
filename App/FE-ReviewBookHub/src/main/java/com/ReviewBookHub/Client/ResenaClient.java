package com.ReviewBookHub.Client;

import com.ReviewBookHub.Model.Resena;
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
import java.util.List;

public class ResenaClient {

    private final String baseUrl = "http://localhost:8080/api/v1/resenas";

    public Respuesta realizarSolicitudHttp(String id) {

        HttpURLConnection conexion = null;
        try {
            // Establecer la conexión
            URL url = new URL(baseUrl + id);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Content-Type", "application/json");

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

            // Mapear la respuesta JSON a la clase Respuesta<List<Categoria>>            
            Respuesta<List<Resena>> respuesta = objectMapperResponse.readValue(
                    jsonResponse,
                    new TypeReference<Respuesta<List<Resena>>>() {
            });

            // Devolver la respuesta mapeada
            return respuesta;

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            return new Respuesta("500", "Error al procesar la respuesta del servicio");
        } finally {
            // Cerrar recursos en un bloque 'finally'
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }
    
    public Respuesta Post(Resena resena) {

        HttpURLConnection conexion = null;
        try {
            // Establecer la conexión
            URL url = new URL(baseUrl);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            
            // Convertir el objeto a formato JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonCredenciales = objectMapper.writeValueAsString(resena);
            
            // Escribir el objeto en el cuerpo de la solicitud
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

            // Devolver la respuesta mapeada
            return respuesta;

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            return new Respuesta("500", "Error al procesar la respuesta del servicio");
        } finally {
            // Cerrar recursos en un bloque 'finally'
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }


    public ArrayList<Resena> get(Long id) throws IOException {
        Respuesta respuesta = realizarSolicitudHttp("/" + id);
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = respuesta.getData();
        return objectMapper.convertValue(data, new TypeReference<ArrayList<Resena>>() {
        });
    }

}
