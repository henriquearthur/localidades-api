package br.com.henriquearthur.localidadesapi.municipio.entity;

import br.com.henriquearthur.localidadesapi.estado.entity.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAB_MUNICIPIO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Municipio {
    @Id
    @Column(name = "TP_CODIGO_MUNICIPIO")
    private Integer id;

    @Column(name = "TP_NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estado;
}
