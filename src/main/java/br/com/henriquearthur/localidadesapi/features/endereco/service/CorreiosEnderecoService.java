package br.com.henriquearthur.localidadesapi.features.endereco.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.endereco.correios.client.CorreiosClient;
import br.com.henriquearthur.localidadesapi.features.endereco.correios.model.CorreiosEndereco;
import br.com.henriquearthur.localidadesapi.features.endereco.dto.EnderecoDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPMessage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Slf4j
public class CorreiosEnderecoService {
    private final CorreiosClient correiosClient;
    private final ModelMapper modelMapper;

    public CorreiosEnderecoService(CorreiosClient correiosClient, ModelMapper modelMapper) {
        this.correiosClient = correiosClient;
        this.modelMapper = modelMapper;
    }

    public EnderecoDTO getEndereco(String cep) throws APIRequestException {
        log.info("Buscando endereço de CEP {} nos Correios", cep);

        String responseBody;
        SOAPMessage message;

        try {
            responseBody = correiosClient.getEndereco(createRequestBody(cep)).getBody();
        } catch (Exception e) {
            log.warn("Erro ao buscar endereço de CEP {} nos Correios", cep, e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar endereço nos Correios");
        }

        try {
            ByteArrayInputStream xmlInputStream = new ByteArrayInputStream(responseBody.getBytes());
            message = MessageFactory.newInstance().createMessage(null, xmlInputStream);
        } catch (Exception e) {
            log.warn("Erro ao decodificar resposta dos Correios para o CEP {}", cep, e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao decodificar resposta dos Correios");
        }

        CorreiosEndereco correiosEndereco = convertSOAPMessageToCorreiosEndereco(message);
        return convertCorreiosEnderecoToDTO(correiosEndereco);
    }

    private String createRequestBody(String cep) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
                + " xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\">" + "<soapenv:Header/>"
                + "<soapenv:Body>" + "<cli:consultaCEP>" + "<cep>" + cep + "</cep>" + "</cli:consultaCEP>"
                + "</soapenv:Body>" + "</soapenv:Envelope>";
    }

    private CorreiosEndereco convertSOAPMessageToCorreiosEndereco(SOAPMessage message) throws APIRequestException {
        try {
            var jaxbContext = JAXBContext.newInstance(CorreiosEndereco.class);
            var unmarshaller = jaxbContext.createUnmarshaller();

            return (CorreiosEndereco)
                    unmarshaller.unmarshal(message.getSOAPBody().getElementsByTagName("return").item(0));
        } catch (Exception e) {
            log.error("Erro ao converter SOAPMessage para CorreiosEndereco", e);
            throw new APIRequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao converter resposta dos Correios");
        }
    }

    private EnderecoDTO convertCorreiosEnderecoToDTO(CorreiosEndereco correiosEndereco) {
        return modelMapper.map(correiosEndereco, EnderecoDTO.class);
    }
}
