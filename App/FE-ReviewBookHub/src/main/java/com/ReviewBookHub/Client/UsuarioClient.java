
package com.ReviewBookHub.Client;

import com.ReviewBookHub.Model.Usuario;
import com.ReviewBookHub.Shared.Respuesta;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsuarioClient {
    
    private final String baseUrl = "http://localhost:8080/api/v1/usuarios";
    
    public Respuesta post(Usuario usuario) {
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
            String jsonCredenciales = objectMapper.writeValueAsString(usuario);

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
    
}
