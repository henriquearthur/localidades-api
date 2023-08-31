package br.com.henriquearthur.localidadesapi.features.estado.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TAB_REGIAO_ESTADO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegiaoEstado {
    @Id
    @Column(name = "TP_CODIGO_REGIAO_ESTADO")
    private Integer id;

    @Column(name = "TP_SIGLA")
    private String sigla;

    @Column(name = "TP_NOME")
    private String nome;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    private List<Estado> estados;
}
