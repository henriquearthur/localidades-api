package br.com.henriquearthur.localidadesapi.features.estado.ibge.model;

import lombok.Data;

@Data
public class IBGERegiaoEstado {
    private Integer id;
    private String sigla;
    private String nome;
}
