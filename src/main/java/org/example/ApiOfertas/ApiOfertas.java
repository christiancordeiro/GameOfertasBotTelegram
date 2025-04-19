package org.example.ApiOfertas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ApiOfertas {

    private static final Logger log = LoggerFactory.getLogger(ApiOfertas.class);

    public List<Map<String, Object>> buscarOfertas() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jogosempromocoesdev.azurewebsites.net/api/jogos/steam"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(response.body());
            JsonNode gamesNode = rootNode.get("games");
            List<Map<String, Object>> listGames = mapper.readValue(gamesNode.toString(), new TypeReference<List<Map<String, Object>>>(){});

            return listGames;
        } catch(Exception e) {
            log.error(e.getMessage());
            return List.of();
        }

    }
}
