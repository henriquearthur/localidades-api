package br.com.henriquearthur.localidadesapi.municipio.ibge.model;

import lombok.Data;

@Data
public class IBGEMicrorregiao {
    private Integer id;
    private String nome;
    private IBGEMesorregiao mesorregiao;
}
