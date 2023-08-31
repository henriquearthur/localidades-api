package br.com.henriquearthur.localidadesapi.features.municipio.ibge.model;

import br.com.henriquearthur.localidadesapi.features.estado.ibge.model.IBGEEstado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IBGEMesorregiao {
    private Integer id;
    private String nome;
    @JsonProperty("UF")
    private IBGEEstado uf;
}
