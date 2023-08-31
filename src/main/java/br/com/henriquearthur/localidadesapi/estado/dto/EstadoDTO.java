package br.com.henriquearthur.localidadesapi.estado.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoDTO {
    private Integer id;
    private String sigla;
    private String nome;
    private RegiaoEstadoDTO regiao;
}
