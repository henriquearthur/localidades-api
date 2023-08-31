package br.com.henriquearthur.localidadesapi.features.estado.entity;

import br.com.henriquearthur.localidadesapi.features.municipio.entity.Municipio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TAB_ESTADO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Estado {
    @Id
    @Column(name = "TP_CODIGO_ESTADO")
    private Integer id;

    @Column(name = "TP_SIGLA")
    private String sigla;

    @Column(name = "TP_NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private RegiaoEstado regiao;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Municipio> municipios;
}
