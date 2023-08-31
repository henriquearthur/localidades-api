package br.com.henriquearthur.localidadesapi.features.endereco.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAB_ENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    @Id
    @Column(name = "TE_CEP")
    private String cep;

    @Column(name = "TE_LOGRADOURO")
    private String logradouro;

    @Column(name = "TE_COMPLEMENTO")
    private String complemento;

    @Column(name = "TE_BAIRRO")
    private String bairro;

    @Column(name = "TE_MUNICIPIO")
    private String municipio;

    @Column(name = "TE_UF")
    private String uf;
}
