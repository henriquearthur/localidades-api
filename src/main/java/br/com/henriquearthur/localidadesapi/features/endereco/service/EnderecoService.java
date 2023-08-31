package br.com.henriquearthur.localidadesapi.features.endereco.service;

import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import br.com.henriquearthur.localidadesapi.features.endereco.dto.EnderecoDTO;
import br.com.henriquearthur.localidadesapi.features.endereco.entity.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnderecoService {
    private final CorreiosEnderecoService correiosEnderecoService;
    private final LocalEnderecoService localEnderecoService;

    public EnderecoService(CorreiosEnderecoService correiosEnderecoService, LocalEnderecoService localEnderecoService) {
        this.correiosEnderecoService = correiosEnderecoService;
        this.localEnderecoService = localEnderecoService;
    }

    public EnderecoDTO getEndereco(String cep) throws APIRequestException {
        Endereco endereco;

        try {
            EnderecoDTO enderecoCorreios = correiosEnderecoService.getEndereco(cep);
            endereco = localEnderecoService.saveEndereco(enderecoCorreios);
        } catch (Exception e) {
            log.warn("Erro ao buscar endereço {} na API dos Correios. Buscando endereço no banco de dados local.", cep, e);
            endereco = localEnderecoService.getEndereco(cep);
        }

        return localEnderecoService.convertEntityToDTO(endereco);
    }
}
