package br.com.henriquearthur.localidadesapi.features.endereco.service;

import br.com.henriquearthur.localidadesapi.features.endereco.correios.client.CorreiosClient;
import br.com.henriquearthur.localidadesapi.features.endereco.dto.EnderecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CorreiosEnderecoService {
    private final CorreiosClient correiosClient;

    public CorreiosEnderecoService(CorreiosClient correiosClient) {
        this.correiosClient = correiosClient;
    }

    public EnderecoDTO getEndereco(String cep) {
        log.info("Buscando endereço de CEP {} nos Correios", cep);

        String responseBody = correiosClient.getEndereco(createRequestBody(cep)).getBody();

        return new EnderecoDTO();
    }

    private String createRequestBody(String cep) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
                + " xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\">" + "<soapenv:Header/>"
                + "<soapenv:Body>" + "<cli:consultaCEP>" + "<cep>" + cep + "</cep>" + "</cli:consultaCEP>"
                + "</soapenv:Body>" + "</soapenv:Envelope>";
    }
}
