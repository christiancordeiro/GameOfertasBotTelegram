package org.example.ApiOfertas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDTO {
    private String nome;
    private String precoDesconto;
    private String percentualDesconto;
    private String linkLoja;

    public String getNome() { return nome; }
    public String getPrecoDesconto() { return precoDesconto; }
    public String getLinkLoja() { return linkLoja; }
    public String getPercentualDesconto() { return percentualDesconto; }
}
