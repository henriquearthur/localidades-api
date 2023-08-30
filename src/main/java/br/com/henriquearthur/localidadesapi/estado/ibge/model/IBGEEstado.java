package br.com.henriquearthur.localidadesapi.estado.ibge.model;


import lombok.Data;

@Data
public class IBGEEstado {
    private Integer id;
    private String sigla;
    private String nome;
    private IBGERegiaoEstado regiao;
}
