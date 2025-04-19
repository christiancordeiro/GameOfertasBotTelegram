package org.example;

import org.example.ApiOfertas.ApiOfertas;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ApiOfertas api = new ApiOfertas();

        List<Map<String, Object>> teste = api.buscarOfertas();

        for(Map<String, Object> game : teste){
            System.out.println(game.get("nome"));
            System.out.println(game.get("linkLoja"));
        }
    }
}