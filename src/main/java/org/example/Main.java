package org.example;

import org.example.ApiOfertas.ApiDTO;
import org.example.ApiOfertas.ApiOfertas;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ApiOfertas api = new ApiOfertas();

        List<ApiDTO> teste = api.buscarOfertas();

        for(ApiDTO game : teste){
            System.out.println(game.getNome());
            System.out.println(game.getLinkLoja());
        }
    }
}