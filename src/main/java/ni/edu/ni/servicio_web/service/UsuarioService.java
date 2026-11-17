package ni.edu.ni.servicio_web.service;

import ni.edu.ni.servicio_web.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    public List<Usuario> obtenerUsuarios() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al obtener los usuarios: " + response.statusCode());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode datos = mapper.readTree(response.body());

        if (!datos.isArray()) {
            throw new RuntimeException("Error al obtener los usuarios: respuesta no es un arreglo. " + response.statusCode());
        }

        List<Usuario> usuarios = new ArrayList<>();

        for (JsonNode nodoUsuario : datos) {
            Usuario usuario = new Usuario(
                    nodoUsuario.path("id").asInt(),
                    nodoUsuario.path("name").asText(),
                    nodoUsuario.path("username").asText(),
                    nodoUsuario.path("email").asText(),
                    nodoUsuario.path("phone").asText(),
                    nodoUsuario.path("address").path("city").asText(),
                    nodoUsuario.path("company").path("name").asText()
            );
            usuarios.add(usuario);
        }

        return usuarios;
    }
}