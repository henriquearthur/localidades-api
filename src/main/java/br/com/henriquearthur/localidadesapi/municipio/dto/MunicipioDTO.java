package br.com.henriquearthur.localidadesapi.municipio.dto;

import br.com.henriquearthur.localidadesapi.estado.dto.EstadoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MunicipioDTO {
    private Integer id;
    private String nome;
    private EstadoDTO estado;
}
