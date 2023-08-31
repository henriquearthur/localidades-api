package br.com.henriquearthur.localidadesapi.features.pais.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for {@link br.com.henriquearthur.localidadesapi.features.pais.entity.Pais}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaisDTO implements Serializable {
    String codigo;
    String nome;
    String sigla2Letras;
    String sigla3Letras;
    String regiao;
    String subRegiao;
    String regiaoIntermediaria;
}