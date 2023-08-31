package br.com.henriquearthur.localidadesapi.features.municipio.dto;

import br.com.henriquearthur.localidadesapi.features.estado.dto.EstadoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MunicipioDTO {
    private Integer id;
    private String nome;
    private EstadoDTO estado;
}
