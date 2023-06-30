package br.com.henriquearthur.localidadesapi.pais.ibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IBGEIdentificadorPais {
    @JsonProperty("M49")
    private String identificadorONU;

    @JsonProperty("ISO-ALPHA-2")
    private String identificador2Letras;

    @JsonProperty("ISO-ALPHA-3")
    private String identificador3Letras;
}
