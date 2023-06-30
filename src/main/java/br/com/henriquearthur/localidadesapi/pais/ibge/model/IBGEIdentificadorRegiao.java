package br.com.henriquearthur.localidadesapi.pais.ibge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IBGEIdentificadorRegiao {
    @JsonProperty("M49")
    private String identificadorONU;
}
