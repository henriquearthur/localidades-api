package br.com.henriquearthur.localidadesapi.features.endereco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {
    String cep;
    String logradouro;
    String complemento;
    String bairro;
    String municipio;
    String uf;
}
