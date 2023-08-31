package br.com.henriquearthur.localidadesapi.features.municipio.ibge.model;

import lombok.Data;

@Data
public class IBGEMunicipio {
    private Integer id;
    private String nome;
    private IBGEMicrorregiao microrregiao;
}
