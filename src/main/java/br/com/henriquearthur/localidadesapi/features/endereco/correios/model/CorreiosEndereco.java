package br.com.henriquearthur.localidadesapi.features.endereco.correios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "return", namespace = "")
@Data
public class CorreiosEndereco {
    @XmlElement(name = "cep")
    private String cep;

    @XmlElement(name = "end")
    private String logradouro;

    @XmlElement(name = "complemento2")
    private String complemento;

    @XmlElement(name = "bairro")
    private String bairro;

    @XmlElement(name = "cidade")
    private String municipio;

    @XmlElement(name = "uf")
    private String uf;
}
