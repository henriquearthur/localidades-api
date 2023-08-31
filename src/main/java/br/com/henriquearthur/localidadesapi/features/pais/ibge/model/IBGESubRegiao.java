package br.com.henriquearthur.localidadesapi.features.pais.ibge.model;

import lombok.Data;

@Data
public class IBGESubRegiao extends IBGERegiao {
    private IBGERegiao regiao;
}
