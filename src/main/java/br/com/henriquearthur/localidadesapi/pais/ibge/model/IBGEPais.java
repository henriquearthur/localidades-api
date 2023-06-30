package br.com.henriquearthur.localidadesapi.pais.ibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IBGEPais {
    private IBGEIdentificadorPais id;
    private String nome;

    @JsonProperty("regiao-intermediaria")
    private IBGERegiao regiaoIntermediaria;

    @JsonProperty("sub-regiao")
    private IBGESubRegiao subregiao;
}
