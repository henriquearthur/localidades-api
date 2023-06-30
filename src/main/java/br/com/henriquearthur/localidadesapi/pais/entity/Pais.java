package br.com.henriquearthur.localidadesapi.pais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAB_PAIS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pais {
    @Id
    @Column(name = "TP_CODIGO_PAIS")
    private String codigo;

    @Column(name = "TP_NOME")
    private String nome;

    @Column(name = "TP_SIGLA_2_LETRAS")
    private String sigla2Letras;

    @Column(name = "TP_SIGLA_3_LETRAS")
    private String sigla3Letras;

    @Column(name = "TP_REGIAO")
    private String regiao;

    @Column(name = "TP_SUB_REGIAO")
    private String subRegiao;

    @Column(name = "TP_REGIAO_INTERMEDIARIA")
    private String regiaoIntermediaria;
}
