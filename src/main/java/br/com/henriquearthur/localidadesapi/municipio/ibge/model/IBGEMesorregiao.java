package br.com.henriquearthur.localidadesapi.municipio.ibge.model;

import br.com.henriquearthur.localidadesapi.estado.ibge.model.IBGEEstado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IBGEMesorregiao {
    private Integer id;
    private String nome;
    @JsonProperty("UF")
    private IBGEEstado uf;
}
