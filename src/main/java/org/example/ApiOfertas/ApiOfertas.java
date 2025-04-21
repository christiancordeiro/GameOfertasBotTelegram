package org.example.ApiOfertas;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class ApiOfertas {

    private static final Logger log = LoggerFactory.getLogger(ApiOfertas.class);

    public List<ApiDTO> buscarOfertas() {
        List<ApiDTO> listGames;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jogosempromocoesdev.azurewebsites.net/api/jogos/steam"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.readTree(response.body());
            JsonNode gamesNode = rootNode.get("games");
            listGames = mapper.readValue(gamesNode.toString(), mapper.getTypeFactory()
                    .constructCollectionType(List.class, ApiDTO.class));
        } catch(Exception e) {
            log.error(e.getMessage());
            return List.of();
        }

        return listGames;
    }
}
